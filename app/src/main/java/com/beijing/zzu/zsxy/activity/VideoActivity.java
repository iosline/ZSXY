package com.beijing.zzu.zsxy.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.fragment.VideoItemFragment;
import com.beijing.zzu.zsxy.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by jiayongkai on 2017/5/20.
 */

public class VideoActivity extends BaseAcitivity{


    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    List<Fragment>  fragments=new ArrayList<>();
    List<String> titles;
    List<String> subtypes;

    @Override
    protected void initData() {
        titles= ResourceUtil.stringArrayToList(this,R.array.video);
        subtypes=ResourceUtil.stringArrayToList(this,R.array.video_cid);
    }

    @Override
    protected void initView() {
        toolbar.setTitle(ResourceUtil.resToStr(this,R.string.video));

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(VideoItemFragment.newInstance(subtypes.get(i)));
        }
        viewPager.setAdapter(new Adapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }


    class Adapter extends FragmentPagerAdapter{

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_girls;
    }
}
