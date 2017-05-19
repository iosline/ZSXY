package com.beijing.zzu.zsxy.view;

import com.beijing.zzu.zsxy.model.GirlItemData;

import java.util.List;

/**
 * Created by jiayongkai on 2017/5/8.
 */

public interface GirlItemView extends BaseView{

    void onSuccess(List<GirlItemData> datas);

}
