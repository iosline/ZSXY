package com.beijing.zzu.zsxy.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.fragment.BaseFragment;
import com.beijing.zzu.zsxy.fragment.GirlDetailFragment;
import com.beijing.zzu.zsxy.model.GirlItemData;
import com.beijing.zzu.zsxy.presenter.GirlDetailPresenter;
import com.beijing.zzu.zsxy.view.GirlDetailView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jiayongkai on 2017/5/17.
 */

public class GirlDetailActivity extends BaseMvpAcitivity<GirlDetailView,GirlDetailPresenter> implements GirlDetailView{

    private GirlItemData girlItemData;

    @BindView(R.id.girl_detail_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.girl_detail_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.girl_detail_loading)
    AVLoadingIndicatorView mLoading;


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
    protected void initData() {
           girlItemData=getIntent().getParcelableExtra("girl_item_data");
    }

    @Override
    protected void initView() {
         initToolbar();
    }

    private void initToolbar() {
        String title = girlItemData.getTitle();
        mToolbar.setTitle(title);

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
        return R.layout.activity_girl_detail;
    }

    @Override
    protected void fetchData() {
        presenter.getGirlDetailData(girlItemData.getId());
    }

    @Override
    protected void initPresenter() {
       presenter=new GirlDetailPresenter();
    }

    @Override
    public void onSuccess(List<String> data) {
        List<BaseFragment> fragments=new ArrayList<>();
        for (String url:data){
            fragments.add(GirlDetailFragment.newInstance(url));
        }

        GirlDetailAdapter adapter = new GirlDetailAdapter(getSupportFragmentManager());
        adapter.setData(fragments);
        mViewPager.setOffscreenPageLimit(data.size());
        mViewPager.setAdapter(adapter);

        mLoading.hide();
    }


    class GirlDetailAdapter extends FragmentPagerAdapter{

        private List<BaseFragment> fragments;

        public GirlDetailAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setData(List<BaseFragment> fragments){
            this.fragments=fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
