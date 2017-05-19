package com.beijing.zzu.zsxy.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.adapter.TypePagerAdapter;
import com.beijing.zzu.zsxy.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jiayongkai on 2017/5/5.
 */

public class TypeFragment extends BaseFragment {

    private static final String TYPE = "type";

    private String mType;
    private List<BaseMvpFragment> mFragments;
    private List<String> mTitles;

    private TypePagerAdapter mTypeAdapter;

    @BindView(R.id.type_tablayout)
    TabLayout mTabLayout;

    @BindView(R.id.type_viewpager)
    ViewPager mViewPager;

    @Override
    protected void initData() {
        if (getArguments() == null) {
            return;
        }

        mFragments = new ArrayList<>();
        mType = getArguments().getString(TYPE);

        if (ResourceUtil.resToStr(mActivity,R.string.gank).equals(mType)){
            mTitles=ResourceUtil.stringArrayToList(mActivity,R.array.gank);
            for (String title: mTitles) {
//                mFragments.add();
            }
        }else if (ResourceUtil.resToStr(mActivity,R.string.girl).equals(mType)){
            mTitles=ResourceUtil.stringArrayToList(mActivity,R.array.girl);
            List<String> subtypes=ResourceUtil.stringArrayToList(mActivity,R.array.girl_cid);
            for(String subtype:subtypes){
//                mFragments.add()
            }
        }

    }

    @Override
    protected void fetchData() {

    }

    @Override
    protected void initViews() {
         mTypeAdapter=new TypePagerAdapter(getChildFragmentManager());
         mTypeAdapter.setData(mFragments,mTitles);
        mViewPager.setAdapter(mTypeAdapter);
        mViewPager.setOffscreenPageLimit(mTitles.size()-1);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public static TypeFragment newInstance(String type){
        TypeFragment fragment=new TypeFragment();
        Bundle arguments=new Bundle();
        arguments.putString(TYPE,type);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_type_layout;
    }
}
