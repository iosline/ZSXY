package com.beijing.zzu.zsxy.view;

/**
 * Created by jiayongkai on 2017/4/11.
 */

public interface LoginView extends BaseView{


    String getPhoneNum();

    String getPassword();

    void onPhoneNumError();

    void onPasswordError();

    void loginSuccess();

    void loginFailure();

}
