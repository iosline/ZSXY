package com.beijing.zzu.zsxy.presenter;

import android.text.TextUtils;

import com.beijing.zzu.zsxy.Constants;
import com.beijing.zzu.zsxy.model.User;
import com.beijing.zzu.zsxy.utils.CommonUtils;
import com.beijing.zzu.zsxy.utils.PreferencesUtils;
import com.beijing.zzu.zsxy.view.LoginView;


/**
 * Created by jiayongkai on 2017/4/11.
 */

public class LoginPresenter extends BasePresenter<LoginView>{

    public void login(){
        String phoneNum=mView.getPhoneNum();
        String password=mView.getPassword();

        if (TextUtils.isEmpty(phoneNum) || !CommonUtils.isMobile(phoneNum)){
            mView.onPhoneNumError();
            return;
        }

        if (TextUtils.isEmpty(password)){
            mView.onPasswordError();
            return;
        }


    }
}
