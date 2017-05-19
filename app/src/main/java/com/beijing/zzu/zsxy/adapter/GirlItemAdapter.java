package com.beijing.zzu.zsxy.adapter;

import android.content.Context;

import com.beijing.zzu.zsxy.BaseApplication;
import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.model.GirlItemData;
import com.beijing.zzu.zsxy.utils.ImageLoader;
import com.beijing.zzu.zsxy.widget.ScaleImageView;

import java.util.List;

/**
 * Created by jiayongkai on 2017/5/16.
 */

public class GirlItemAdapter extends BaseAdapter<GirlItemData>{

    public GirlItemAdapter(Context context, List<GirlItemData> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, GirlItemData data) {
       ScaleImageView imageview=holder.getView(R.id.girl_item_iv);
        imageview.setInitSize(data.getWidth(),data.getHeight());
        ImageLoader.load(BaseApplication.getContext(),data.getUrl(),imageview);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_girl_layout;
    }
}
