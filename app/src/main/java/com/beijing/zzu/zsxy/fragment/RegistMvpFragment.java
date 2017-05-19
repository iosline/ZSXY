package com.beijing.zzu.zsxy.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.beijing.zzu.zsxy.BaseApplication;
import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.activity.PrefectUserInfoAcitivity;
import com.beijing.zzu.zsxy.presenter.RegistPresenter;
import com.beijing.zzu.zsxy.view.RegistView;
import com.beijing.zzu.zsxy.widget.EditTextWithDel;
import com.beijing.zzu.zsxy.widget.PaperButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册
 * Created by jiayongkai on 2017/4/12.
 */

public class RegistMvpFragment extends BaseMvpFragment<RegistView,RegistPresenter> implements RegistView{

    @BindView(R.id.next)
    PaperButton nextBt;
    @BindView(R.id.userpassword)
    EditTextWithDel userpassword;
    @BindView(R.id.send_smscode)
    PaperButton sendsmscode;
    @BindView(R.id.userphone)
    EditTextWithDel userphone;
    @BindView(R.id.smscode)
    EditTextWithDel smscode;
    @BindView(R.id.fg_regist)
    LinearLayout fg_regist;
    @BindView(R.id.rela_rephone)
    RelativeLayout rela_rephone;
    @BindView(R.id.rela_recode)
    RelativeLayout rela_recode;
    @BindView(R.id.rela_repass)
    RelativeLayout rela_repass;
    @BindView(R.id.usericon)
    ImageView phoneIv;
    @BindView(R.id.keyicon)
    ImageView keyIv;
    @BindView(R.id.codeicon)
    ImageView passIv;

    private Context context;

    @OnClick(R.id.send_smscode)
    void sendSms(View view){
        if (sendsmscode.isClickable()){
            presenter.sendSms();
        }

    }

    @OnClick(R.id.next)
    void next(View view){
        presenter.toNextPage();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {
        presenter=new RegistPresenter();
    }

    @Override
    protected void initViews() {
        context= BaseApplication.getContext();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_regist;
    }

    @Override
    protected void fetchData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetError() {

    }

    @Override
    public String getPhoneNum() {
        return userphone.getText().toString();
    }

    @Override
    public String getSmsCode() {
        return smscode.getText().toString();
    }

    @Override
    public String getPassword() {
        return userpassword.getText().toString();
    }


    @Override
    public void onPhoneNumError() {
        userphone.setError("手机号码不正确");
    }

    @Override
    public void onPasswordEmpty() {
        userpassword.setError("密码不能为空");
    }

    @Override
    public void onSmsCodeError() {
        smscode.setError("验证码不正确");
    }

    @Override
    public void setLastTime(long millisUntilFinished) {
        sendsmscode.setText((millisUntilFinished / 1000) +"秒后重发");
        sendsmscode.setClickable(false);
    }

    @Override
    public void resetSendText() {
        sendsmscode.setText("重新发送");
        sendsmscode.setClickable(true);
    }

    @Override
    public void onReigstSuccess() {
        //去完善个人信息
        startActivity(new Intent(BaseApplication.getContext(), PrefectUserInfoAcitivity.class));
    }
}
