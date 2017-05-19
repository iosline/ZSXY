package com.beijing.zzu.zsxy.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beijing.zzu.zsxy.Constants;
import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.presenter.LoginPresenter;
import com.beijing.zzu.zsxy.utils.CommonUtils;
import com.beijing.zzu.zsxy.utils.PreferencesUtils;
import com.beijing.zzu.zsxy.view.LoginView;
import com.beijing.zzu.zsxy.widget.EditTextWithDel;
import com.beijing.zzu.zsxy.widget.PaperButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 * Created by jiayongkai on 2017/4/12.
 */

public class LoginMvpFragment extends BaseMvpFragment<LoginView,LoginPresenter> implements LoginView{

    @BindView(R.id.userph)
    EditTextWithDel userphone;
    @BindView(R.id.userpass)
    EditTextWithDel userpass;
    @BindView(R.id.bt_login)
    PaperButton bt_login;
    @BindView(R.id.login_progress)
    ProgressBar login_progress;
    @BindView(R.id.tv_forgetcode)
    TextView tv_forgetcode;
    @BindView(R.id.loginusericon)
    ImageView loginusericon;
    @BindView(R.id.codeicon)
    ImageView codeicon;
    @BindView(R.id.rela_name)
    RelativeLayout rela_name;
    @BindView(R.id.rela_pass)
    RelativeLayout rela_pass;

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

        presenter=new LoginPresenter();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    protected void fetchData() {
        String phoneNum=PreferencesUtils.getString(Constants.PHONE_NUM,null);
        if (!TextUtils.isEmpty(phoneNum)){
            userphone.setText(phoneNum);
            userphone.setSelection(phoneNum.length());
        }

        userpass.setText(PreferencesUtils.getString(Constants.PASSWORD,null));
    }

    @OnClick(R.id.bt_login)
    void login(View view){
        presenter.login();
    }

    @Override
    public String getPhoneNum() {
        return userphone.getText().toString();
    }

    @Override
    public String getPassword() {
        return userpass.getText().toString();
    }

    @Override
    public void onPhoneNumError() {
        userphone.setError("请输入正确的手机号码");
    }

    @Override
    public void onPasswordError() {
        userpass.setError("密码不能为空");
    }

    @Override
    public void loginSuccess() {
        CommonUtils.showToast("登录成功");
    }

    @Override
    public void loginFailure() {
        CommonUtils.showToast("手机号或密码不正确");
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
}
