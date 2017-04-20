package com.beijing.zzu.zsxy.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.model.GankItem;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by jiayongkai on 2017/4/19.
 */

public class GankItemAdapter extends BaseAdapter<GankItem> {


    public GankItemAdapter(Context context,List<GankItem> gankItems, boolean isOpenLoadMore){
        super(context,gankItems, isOpenLoadMore);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_gank_layout;
    }


    @Override
    protected void convert(ViewHolder viewHolder, GankItem gankItem) {
        viewHolder.setText(R.id.gank_item_desc, gankItem.getDesc());
        String who = TextUtils.isEmpty(gankItem.getWho()) ? "null" : gankItem.getWho();
        viewHolder.setText(R.id.gank_item_who, who);

        viewHolder.setText(R.id.gank_item_publishedat, gankItem.getPublishedAt().substring(0, 10));

        ImageView image = viewHolder.getView(R.id.gank_item_icon);
        if (gankItem.getImages() != null && gankItem.getImages().size() > 0) {
            Glide.with(mContext).load(gankItem.getImages().get(0) + "?imageView2/1/w/100/h/100").into(image);
        } else {
            String url = gankItem.getUrl();
            int iconId;
            if (url.contains("github")) {
                iconId = R.mipmap.github;
            } else if (url.contains("jianshu")) {
                iconId = R.mipmap.jianshu;
            } else if (url.contains("csdn")) {
                iconId = R.mipmap.csdn;
            } else if (url.contains("miaopai")) {
                iconId = R.mipmap.miaopai;
            } else if (url.contains("acfun")) {
                iconId = R.mipmap.acfun;
            } else if (url.contains("bilibili")) {
                iconId = R.mipmap.bilibili;
            } else if (url.contains("youku")) {
                iconId = R.mipmap.youku;
            } else if (url.contains("weibo")) {
                iconId = R.mipmap.weibo;
            } else if (url.contains("weixin")) {
                iconId = R.mipmap.weixin;
            } else {
                iconId = R.mipmap.web;
            }
            Glide.with(mContext).load(iconId).into(image);
        }
    }

}