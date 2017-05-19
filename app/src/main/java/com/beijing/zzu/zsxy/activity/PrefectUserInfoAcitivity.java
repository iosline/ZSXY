package com.beijing.zzu.zsxy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.model.User;
import com.beijing.zzu.zsxy.presenter.PrefectUserInfoPresenter;
import com.beijing.zzu.zsxy.utils.CommonUtils;
import com.beijing.zzu.zsxy.view.PrefectUserInfoView;
import com.bumptech.glide.Glide;
import com.jay.ui.PhotoPickerActivity;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 完善个人信息页面
 * Created by jiayongkai on 2017/4/12.
 */

public class PrefectUserInfoAcitivity extends BaseMvpAcitivity<PrefectUserInfoView, PrefectUserInfoPresenter> implements PrefectUserInfoView {

    @BindView(R.id.change_profile)
    LinearLayout changeProfile;
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.edit_username)
    EditText editName;
    @BindView(R.id.radioGroup)
    RadioGroup sexRadioGroup;
    @BindView(R.id.radioMale)
    RadioButton radioMale;
    @BindView(R.id.radioFemale)
    RadioButton radioFemale;
    @BindView(R.id.save)
    TextView save;





    private static int REQUEST_CODE = 100;

    private int default_sex=0;   //0 男  1  女
    private String updateProfileUrl=null;


    @OnClick({R.id.change_profile,R.id.save})
    void changeProfile(View view) {
        switch (view.getId()){
            case R.id.change_profile:
                Intent intent = new Intent(PrefectUserInfoAcitivity.this, PhotoPickerActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            break;
            case R.id.save:
//                presenter.saveUserInfo();
//                startActivity(new Intent(PrefectUserInfoAcitivity.this, MainActivity.class));
            break;
        }

    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_prefect_userinfo;
    }

    @Override
    protected void fetchData() {
        presenter.getUserInfo();

        sexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int id=radioGroup.getCheckedRadioButtonId();
                if (id == radioMale.getId()){
                    radioMale.setChecked(true);
                    default_sex = 0;
                }else {
                    radioFemale.setChecked(true);
                    default_sex = 1;
                }

                Log.d("sex==",default_sex+"");
            }
        });
    }

    @Override
    protected void initPresenter() {
        presenter = new PrefectUserInfoPresenter();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String result = data.getStringExtra(PhotoPickerActivity.SELECT_RESULTS);
            if (!TextUtils.isEmpty(result)){
                presenter.uploadProfile(result);
            }

        }
    }

    @Override
    public String getNickName() {
        return editName.getText().toString();
    }

    @Override
    public String getUpdateProfileUrl() {
        return updateProfileUrl;
    }

    @Override
    public int getUserSex() {
        return default_sex;
    }

    @Override
    public void onGetUserInfoSuccess(User user) {
        default_sex=user.getSex();
        if (default_sex == 0){
            radioMale.setChecked(true);
        }else {
            radioFemale.setChecked(true);
        }

        editName.setText(user.getNickname());
        Glide.with(PrefectUserInfoAcitivity.this).load(user.getPhoto()).into(profileImage);
    }

    @Override
    public void onGetUserInfoFailure() {

    }

    @Override
    public void onUploadProfileSuccess(final String imgPath, String imgUrl) {
        updateProfileUrl=imgUrl;
        Glide.with(PrefectUserInfoAcitivity.this).load(imgPath).into(profileImage);
        CommonUtils.showToast("头像上传成功");

    }

    @Override
    public void onUploadProfileFailure() {
        CommonUtils.showToast("头像上传失败,请重新上传");
    }
}
