package com.beijing.zzu.zsxy.view;

import com.beijing.zzu.zsxy.model.User;

/**
 * Created by jiayongkai on 2017/4/12.
 */

public interface PrefectUserInfoView extends BaseView{

    String getNickName();

    String getUpdateProfileUrl();

    int getUserSex();

    void onGetUserInfoSuccess(User user);

    void onGetUserInfoFailure();

    void onUploadProfileSuccess(String imgPath,String imgUrl);

    void onUploadProfileFailure();
}
