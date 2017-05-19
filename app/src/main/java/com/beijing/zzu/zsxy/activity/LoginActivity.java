package com.beijing.zzu.zsxy.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.adapter.TabViewPagerAdapter;
import com.beijing.zzu.zsxy.fragment.LoginMvpFragment;
import com.beijing.zzu.zsxy.fragment.RegistMvpFragment;

import butterknife.BindView;

/**
 * 登录注册界面
 * Created by jiayongkai on 2017/4/11.
 */

public class LoginActivity extends BaseAcitivity{

    @BindView(R.id.login_tabs)
    TabLayout loginTab;
    @BindView(R.id.login_viewpager)
    ViewPager loginPager;



    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new LoginMvpFragment(), "登录");
        adapter.addFrag(new RegistMvpFragment(), "注册");
        loginPager.setAdapter(adapter);

        loginTab.setupWithViewPager(loginPager);
        loginTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                final 	int f=tab.getPosition();
                loginPager.setCurrentItem(f);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }




}
