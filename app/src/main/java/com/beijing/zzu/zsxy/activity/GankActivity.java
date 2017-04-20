package com.beijing.zzu.zsxy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.beijing.zzu.zsxy.Constants;
import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.fragment.GankItemFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiayongkai on 2017/4/17.
 */

public class GankActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("知识点");
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.icon_back_normal);
        ab.setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(GankItemFactory.getGankItmeFragment(Constants.GANK_TYPE_ALL), Constants.GANK_TYPE_ALL);
        adapter.addFragment(GankItemFactory.getGankItmeFragment(Constants.GANK_TYPE_ANDROID), Constants.GANK_TYPE_ANDROID);
        adapter.addFragment(GankItemFactory.getGankItmeFragment(Constants.GANK_TYPE_IOS), Constants.GANK_TYPE_IOS);
        adapter.addFragment(GankItemFactory.getGankItmeFragment(Constants.GANK_TYPE_WEB), Constants.GANK_TYPE_WEB);
        adapter.addFragment(GankItemFactory.getGankItmeFragment(Constants.GANK_TYPE_TUOZHAN), Constants.GANK_TYPE_TUOZHAN);
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
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
            return mFragmentTitles.get(position);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
