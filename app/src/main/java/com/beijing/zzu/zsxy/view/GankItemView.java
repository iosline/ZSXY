package com.beijing.zzu.zsxy.view;

import com.beijing.zzu.zsxy.model.GankItem;

import java.util.List;

/**
 * Created by jiayongkai on 2017/4/18.
 */

public interface GankItemView extends BaseView{

    void onSuccess(List<GankItem> data);
}
