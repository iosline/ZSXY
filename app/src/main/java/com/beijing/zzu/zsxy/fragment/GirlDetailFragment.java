package com.beijing.zzu.zsxy.fragment;

import android.os.Bundle;
import android.widget.ImageView;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.utils.ImageLoader;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by jiayongkai on 2017/5/19.
 */

public class GirlDetailFragment extends BaseFragment{

    private static final String URL = "url";

    private String mUrl;

    @BindView(R.id.girl_detail_iv)
    PhotoView imageView;

    @Override
    protected void initData() {
       if (getArguments() == null){
           return;
       }

       mUrl=getArguments().getString(URL);
    }

    @Override
    protected void initViews() {
        ImageLoader.load(getActivity(),mUrl,imageView);
        PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);
        attacher.update();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_girl_detail;
    }

    @Override
    protected void fetchData() {

    }

    public static GirlDetailFragment newInstance(String url){
        GirlDetailFragment fragment = new GirlDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putString(URL, url);
        fragment.setArguments(arguments);
        return fragment;
    }
}
