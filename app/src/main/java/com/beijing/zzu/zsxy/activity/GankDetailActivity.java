package com.beijing.zzu.zsxy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.model.GankItem;
import com.beijing.zzu.zsxy.utils.CopyUtil;
import com.beijing.zzu.zsxy.utils.NetStatusUtil;
import com.beijing.zzu.zsxy.utils.PreferencesUtils;
import com.beijing.zzu.zsxy.utils.SnackBarUtil;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;

public class GankDetailActivity extends BaseAcitivity {

    private GankItem gankItem;

    @BindView(R.id.gank_detail_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.gank_detail_webview)
    WebView mWebView;

    @BindView(R.id.gank_detail_progress)
    ProgressBar mProgressBar;

    @BindView(R.id.gank_detail_loading)
    AVLoadingIndicatorView mLoading;


    @Override
    protected void initData() {
        gankItem= (GankItem) getIntent().getSerializableExtra("gank_item");
    }

    @Override
    protected void initView() {
       initToolbar();
        initWebView();
    }

    private void initWebView() {
        final WebSettings webSetting = mWebView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setLoadsImagesAutomatically(true);
        webSetting.setBlockNetworkImage(true);

        //有网 就默认的加载网络数据  没有 就加载缓存
        if (NetStatusUtil.isNetworkAvailable(this)){
            webSetting.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            webSetting.setCacheMode(
                    WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100){
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mLoading.hide();
                webSetting.setBlockNetworkImage(false);

                //默认跳转到上次阅读的位置
                int position = PreferencesUtils.getInt(gankItem.getUrl());
                mWebView.scrollTo(0,position);
            }
        });

        mWebView.loadUrl(gankItem.getUrl());
    }


    private void initToolbar() {
        String desc=gankItem.getDesc();
        desc=desc.length() > 10 ? desc.substring(0,10)+"...":desc;
        mToolbar.setTitle(desc);

        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_gank_detail;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gank_detail_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_copy:
                CopyUtil.copy(mContext,gankItem.getUrl());
                SnackBarUtil.show(mWebView,"复制链接成功");
                break;
            case R.id.menu_share:

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPause() {
        super.onPause();
        PreferencesUtils.putInt(gankItem.getUrl(),mWebView.getScrollY());
    }
}
