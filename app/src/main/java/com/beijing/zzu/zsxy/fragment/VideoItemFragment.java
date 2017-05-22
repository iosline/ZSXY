package com.beijing.zzu.zsxy.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.adapter.OnItemClickListeners;
import com.beijing.zzu.zsxy.adapter.OnLoadMoreListener;
import com.beijing.zzu.zsxy.adapter.VideoListAdapter;
import com.beijing.zzu.zsxy.adapter.ViewHolder;
import com.beijing.zzu.zsxy.model.VideoItemData;
import com.beijing.zzu.zsxy.presenter.VideoPresenter;
import com.beijing.zzu.zsxy.view.VideoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jiayongkai on 2017/5/22.
 */

public class VideoItemFragment extends BaseMvpFragment<VideoView,VideoPresenter> implements VideoView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.type_item_recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.type_item_swipfreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private List<VideoItemData> videoList=new ArrayList<>();
    private VideoListAdapter videoListAdapter;

    private int PAGE_COUNT=1;
    private int mTempPageCount=2;
    private boolean isLoadMore;

    private String cateid;

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
    public void onSuccess(List<VideoItemData> datas) {
        if (isLoadMore){
            if (datas.size() == 0){
                videoListAdapter.setLoadEndView(R.layout.load_end_layout);
            }else {
                videoListAdapter.setLoadMoreData(datas);
                mTempPageCount++;
            }
        }else {
            mSwipeRefreshLayout.setRefreshing(false);
            videoListAdapter.setNewData(datas);
        }

    }

    @Override
    protected void initPresenter() {
        presenter=new VideoPresenter();
    }

    @Override
    protected void initData() {
        cateid=getArguments().getString("cateid");
    }

    @Override
    protected void initViews() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //实现首次自动显示加载提示
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        videoListAdapter=new VideoListAdapter(mActivity,videoList,true);
        videoListAdapter.setLoadingView(R.layout.load_loading_layout);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        videoListAdapter.setOnItemClickListener(new OnItemClickListeners<VideoItemData>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, VideoItemData data, int position) {
            }
        });

        videoListAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                if (PAGE_COUNT == mTempPageCount && !isReload){
                    return;
                }
                isLoadMore=true;
                PAGE_COUNT=mTempPageCount;
                fetchData();
            }
        });

        mRecyclerView.setAdapter(videoListAdapter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_type_item_layout;
    }

    @Override
    protected void fetchData() {
        presenter.getVideoListData(cateid,PAGE_COUNT);
    }

    @Override
    public void onRefresh() {
        isLoadMore = false;
        PAGE_COUNT = 1;
        fetchData();
    }

    public static VideoItemFragment newInstance(String cateid){
        VideoItemFragment fragment=new VideoItemFragment();
        Bundle args=new Bundle();
        args.putString("cateid",cateid);
        fragment.setArguments(args);
        return fragment;
    }
}
