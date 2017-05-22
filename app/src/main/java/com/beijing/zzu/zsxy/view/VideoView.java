package com.beijing.zzu.zsxy.view;

import com.beijing.zzu.zsxy.model.VideoItemData;

import java.util.List;

/**
 * Created by jiayongkai on 2017/5/20.
 */

public interface VideoView extends BaseView{

    void onSuccess(List<VideoItemData> datas);

}
