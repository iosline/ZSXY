package com.beijing.zzu.zsxy.presenter;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.widget.Toast;

import com.beijing.zzu.zsxy.BaseApplication;
import com.beijing.zzu.zsxy.Constants;
import com.beijing.zzu.zsxy.model.User;
import com.beijing.zzu.zsxy.utils.CommonUtils;
import com.beijing.zzu.zsxy.utils.PreferencesUtils;
import com.beijing.zzu.zsxy.view.RegistView;


/**
 * Created by jiayongkai on 2017/4/12.
 */

public class RegistPresenter extends BasePresenter<RegistView>{

    MyCountTimer timer;

    public void sendSms(){
        String phoneNum=mView.getPhoneNum();
        if (!TextUtils.isEmpty(phoneNum)){
           if (CommonUtils.isMobile(phoneNum)){
               timer = new MyCountTimer(60000, 1000);
               timer.start();
//               BmobSMS.requestSMSCode(phoneNum, "掌上校园", new QueryListener<Integer>() {
//                   @Override
//                   public void done(Integer integer, BmobException e) {
//                      if (e == null){
//                          CommonUtils.showToast("短信发送成功");
//                      }else {
//                          CommonUtils.showToast("短信发送失败"+integer);
//                      }
//                   }
//               });
           }else {
               mView.onPhoneNumError();
           }
        }else {
            mView.onPhoneNumError();
        }
    }

    public void toNextPage(){
//        final String phoneNum=mView.getPhoneNum();
//        String smsCode=mView.getSmsCode();
//        final String password=mView.getPassword();
//
//        if (TextUtils.isEmpty(phoneNum) || !CommonUtils.isMobile(phoneNum)){
//            mView.onPhoneNumError();
//            return;
//        }
//        if (TextUtils.isEmpty(smsCode)){
//            mView.onSmsCodeError();
//            return;
//        }
//        if (TextUtils.isEmpty(password)){
//            mView.onPasswordEmpty();
//            return;
//        }
//
//        User user=new User();
//        user.setMobilePhoneNumber(phoneNum);
//        user.setPassword(password);
//
//        user.signOrLogin(smsCode, new SaveListener<Object>() {
//            @Override
//            public void done(Object o, BmobException e) {
//                if (e == null){
//                    PreferencesUtils.putString(Constants.PHONE_NUM,phoneNum);
//                    PreferencesUtils.putString(Constants.PASSWORD,password);
//                    mView.onReigstSuccess();
//                }else {
//                    mView.onSmsCodeError();
//                }
//            }
//        });

        mView.onReigstSuccess();
    }

    //事件定时器
    class MyCountTimer extends CountDownTimer {

        public MyCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            mView.setLastTime(millisUntilFinished);
        }
        @Override
        public void onFinish() {
            mView.resetSendText();
        }
    }

}
