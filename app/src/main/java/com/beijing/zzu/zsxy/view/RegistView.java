package com.beijing.zzu.zsxy.view;

/**
 * Created by jiayongkai on 2017/4/12.
 */

public interface RegistView extends BaseView{

    String getPhoneNum();

    String getSmsCode();

    String getPassword();

    void onPhoneNumError();

    void onPasswordEmpty();

    void onSmsCodeError();

    //重置发送验证码按钮 剩余时间
    void setLastTime(long millisUntilFinished);

    void resetSendText();

    void onReigstSuccess();
}
