package com.beijing.zzu.zsxy.adapter;

/**
 * Created by jiayongkai on 2017/4/19.
 */

public interface OnItemClickListeners<T> {
    void onItemClick(ViewHolder viewHolder,T data,int position);
}
