package com.beijing.zzu.zsxy.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.adapter.TabViewPagerAdapter;
import com.beijing.zzu.zsxy.fragment.LoginFragment;
import com.beijing.zzu.zsxy.fragment.RegistFragment;
import com.beijing.zzu.zsxy.presenter.LoginPresenter;
import com.beijing.zzu.zsxy.view.BaseView;
import com.beijing.zzu.zsxy.view.LoginView;

import butterknife.BindView;

/**
 * 登录注册界面
 * Created by jiayongkai on 2017/4/11.
 */

public class LoginActivity extends BaseMvpAcitivity{

    @BindView(R.id.login_tabs)
    TabLayout loginTab;
    @BindView(R.id.login_viewpager)
    ViewPager loginPager;

    @Override
    protected void updateViews() {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new LoginFragment(), "登录");
        adapter.addFrag(new RegistFragment(), "注册");
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
    protected void initViews() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initPresenter() {

    }


}
