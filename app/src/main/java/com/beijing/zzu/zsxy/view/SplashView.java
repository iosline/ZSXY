package com.beijing.zzu.zsxy.view;

import android.view.animation.Animation;

/**
 * Created by jiayongkai on 2017/5/24.
 */

public interface SplashView extends BaseView{

    void animateBackgroundImage(Animation animation);

    void initializeViews(String versionName,String copyright,int backgroundResId);

    void navigateToHomePage();
}
