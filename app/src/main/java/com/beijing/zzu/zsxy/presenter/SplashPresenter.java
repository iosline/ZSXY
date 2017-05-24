package com.beijing.zzu.zsxy.presenter;

import android.content.Context;
import android.view.animation.Animation;

import com.beijing.zzu.zsxy.interactor.SplashInteractor;
import com.beijing.zzu.zsxy.interactor.interactorImpl.SplashInteractorImpl;
import com.beijing.zzu.zsxy.view.SplashView;

/**
 * Created by jiayongkai on 2017/5/24.
 */

public class SplashPresenter extends BasePresenter<SplashView>{

    private SplashInteractor splashInteractor;

    public SplashPresenter(){
        splashInteractor=new SplashInteractorImpl();
    }


    public void initData(Context context){
       mView.initializeViews(splashInteractor.getVersionName(context),splashInteractor.getCopyright(context),splashInteractor.getBackgroundImageResID());

        Animation animation = splashInteractor.getBackgroundImageAnimation(context);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mView.navigateToHomePage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mView.animateBackgroundImage(animation);
    }
}
