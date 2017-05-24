package com.beijing.zzu.zsxy.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.fragment.BaseFragment;
import com.beijing.zzu.zsxy.fragment.TypeFragment;
import com.beijing.zzu.zsxy.utils.CommonUtils;
import com.beijing.zzu.zsxy.utils.ResourceUtil;
import com.beijing.zzu.zsxy.utils.SnackBarUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jiayongkai on 2017/4/17.
 */

public class MainActivity extends BaseAcitivity{

    private String mCurrentType;
    private boolean isBackPressed;
    private Map<String,BaseFragment> mTypeFragments;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void initData() {
        mTypeFragments=new HashMap<>();
    }

    @Override
    protected void initView() {

        initStatusBar();

        initDrawer();

        initNavigationView();

        doReplace(ResourceUtil.resToStr(mContext,R.string.gank));

    }

    private void doReplace(String type) {
        if (!type.equals(mCurrentType)){
            repalceFragment(TypeFragment.newInstance(type),type,mCurrentType);
            mCurrentType=type;
        }
    }

    private void repalceFragment(BaseFragment fragment, String type, String lastType) {
        toolbar.setTitle(type);
        if (mTypeFragments.get(type) == null){
            mTypeFragments.put(type,fragment);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_container,fragment,type).commit();
        }

        if (mTypeFragments.get(lastType) != null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(mTypeFragments.get(lastType))
                    .show(mTypeFragments.get(type))
                    .commit();
        }
    }

    private void initNavigationView() {
        View navHeaderview=navigationView.getHeaderView(0);
        CircleImageView profile_image= (CircleImageView) navHeaderview.findViewById(R.id.profile_image);
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_news:
                        doReplace(ResourceUtil.resToStr(mContext,R.string.gank));
                        break;
                    case R.id.nav_picture:
                        doReplace(ResourceUtil.resToStr(mContext,R.string.girl));
                        break;
                    case R.id.nav_video:
                        doReplace(ResourceUtil.resToStr(mContext,R.string.video));
                        break;
                    case R.id.nav_music:
                        break;
                    case R.id.nav_wether:
                        break;
                }
                //隐藏NavigationView
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void initDrawer() {
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
//        setSupportActionBar(toolbar);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        mDrawerLayout.addDrawerListener(toggle);
//        //设置左上角显示三道横线
//        toggle.syncState();
//        toolbar.setTitle(R.string.app_name);
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            //将侧边栏顶部延伸至status bar
            mDrawerLayout.setFitsSystemWindows(true);

            //将主页面顶部延伸至 status bar
            mDrawerLayout.setClipToPadding(false);
        }
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.menu_1:
               CommonUtils.showToast("menu——1");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (isBackPressed) {
                super.onBackPressed();
                return;
            }

            isBackPressed = true;

            SnackBarUtil.show(mDrawerLayout, R.string.back_pressed_tip);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isBackPressed = false;
                }
            }, 2000);
        }
    }
}
