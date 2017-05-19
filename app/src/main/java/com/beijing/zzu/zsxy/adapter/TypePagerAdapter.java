package com.beijing.zzu.zsxy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.beijing.zzu.zsxy.fragment.BaseMvpFragment;

import java.util.List;

/**
 * Created by jiayongkai on 2017/5/5.
 */

public class TypePagerAdapter extends FragmentPagerAdapter{

    private List<BaseMvpFragment> fragments;
    private List<String> titles;

    public TypePagerAdapter(FragmentManager fm) {
        super(fm);
    }



    public void setData(List<BaseMvpFragment> fragments,List<String> titles){
        this.titles=titles;
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

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
