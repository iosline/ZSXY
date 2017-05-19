package com.beijing.zzu.zsxy.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by jiayongkai on 2017/5/16.
 */

public class ScaleImageView extends ImageView{

    private int initWidth;
    private int initHeight;

    public ScaleImageView(Context context) {
        this(context,null);
    }

    public ScaleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setInitSize(int initWidth,int initHeight){
        this.initWidth=initWidth;
        this.initHeight=initHeight;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (initWidth > 0 && initHeight > 0){
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);

            float scale=(float) initHeight/(float) initWidth;
            if (width >0){
                height= (int) ((float)width*scale);
            }
            setMeasuredDimension(width,height);
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
