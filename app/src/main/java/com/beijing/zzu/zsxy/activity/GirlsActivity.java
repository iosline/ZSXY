package com.beijing.zzu.zsxy.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.fragment.GankItemMvpFragment;
import com.beijing.zzu.zsxy.fragment.GirlItemFragment;
import com.beijing.zzu.zsxy.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GirlsActivity extends BaseAcitivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tabs)
    TabLayout mTablayout;

    @BindView(R.id.viewpager)
    ViewPager mPager;

    List<Fragment> mFragments=new ArrayList<>();
    List<String> titles;
    List<String> subtypes;

    @Override
    protected void initData() {
         titles=ResourceUtil.stringArrayToList(this,R.array.girl);
         subtypes=ResourceUtil.stringArrayToList(this,R.array.girl_cid);


    }

     class Adapter extends FragmentPagerAdapter {

        public Adapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }


    }

    @Override
    protected void initView() {
        mToolbar.setTitle(ResourceUtil.resToStr(this,R.string.girl));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for (int i = 0; i < subtypes.size(); i++) {
            mFragments.add(GirlItemFragment.newInstance(subtypes.get(i)));
        }

        mPager.setAdapter(new Adapter(getSupportFragmentManager()));
        mTablayout.setupWithViewPager(mPager);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_girls;
    }
}
