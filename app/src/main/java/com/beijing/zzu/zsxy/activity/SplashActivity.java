package com.beijing.zzu.zsxy.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.presenter.SplashPresenter;
import com.beijing.zzu.zsxy.view.SplashView;

import butterknife.BindView;

/**
 * Created by jiayongkai on 2017/5/24.
 */

public class SplashActivity extends BaseMvpAcitivity<SplashView,SplashPresenter> implements SplashView{


    @BindView(R.id.splash_image)
    ImageView mSplashImage;

    @BindView(R.id.splash_version_name)
    TextView mVersionName;

    @BindView(R.id.splash_copyright)
    TextView mCopyright;

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetError() {

    }

    @Override
    protected void fetchData() {

    }

    @Override
    protected void initPresenter() {
       presenter=new SplashPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.initData(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    public void animateBackgroundImage(Animation animation) {
        mSplashImage.startAnimation(animation);
    }

    @Override
    public void initializeViews(String versionName, String copyright, int backgroundResId) {
        mCopyright.setText(copyright);
        mVersionName.setText(versionName);
        mSplashImage.setImageResource(backgroundResId);
    }

    @Override
    public void navigateToHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
