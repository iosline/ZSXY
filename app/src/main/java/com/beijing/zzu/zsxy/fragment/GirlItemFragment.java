package com.beijing.zzu.zsxy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.activity.GirlDetailActivity;
import com.beijing.zzu.zsxy.adapter.GirlItemAdapter;
import com.beijing.zzu.zsxy.adapter.OnItemClickListeners;
import com.beijing.zzu.zsxy.adapter.OnLoadMoreListener;
import com.beijing.zzu.zsxy.adapter.ViewHolder;
import com.beijing.zzu.zsxy.model.GirlItemData;
import com.beijing.zzu.zsxy.presenter.GirlItemPresenter;
import com.beijing.zzu.zsxy.service.DataService;
import com.beijing.zzu.zsxy.view.GirlItemView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jiayongkai on 2017/5/8.
 */

public class GirlItemFragment extends BaseMvpFragment<GirlItemView,GirlItemPresenter> implements GirlItemView, SwipeRefreshLayout.OnRefreshListener {
    protected static final String SUB_TYPE = "subtype";
    private int PAGE_COUNT=1;
    private String mSubtype;
    private int mTempPageCount=2;
    private boolean isLoadMore;

    @BindView(R.id.type_item_recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.type_item_swipfreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private GirlItemAdapter girlItemAdapter;

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetError() {
      if (isLoadMore){
          girlItemAdapter.setLoadFailedView(R.layout.load_failed_layout);
      }else {
          mSwipeRefreshLayout.setRefreshing(false);
      }
    }

    @Override
    public void onSuccess(List<GirlItemData> datas) {
        DataService.startService(getActivity(),datas,mSubtype);
    }

    @Override
    protected void initData() {
         if (getArguments() == null){
             return;
         }

         mSubtype=getArguments().getString(SUB_TYPE);
    }

    @Override
    protected void initPresenter() {
        presenter=new GirlItemPresenter();
    }

    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //实现首次自动显示加载提示
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        girlItemAdapter=new GirlItemAdapter(getActivity(),new ArrayList<GirlItemData>(),true);
        girlItemAdapter.setLoadingView(R.layout.load_loading_layout);
        girlItemAdapter.setOnItemClickListener(new OnItemClickListeners<GirlItemData>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, GirlItemData data, int position) {
                Intent intent = new Intent(getActivity(), GirlDetailActivity.class);
                intent.putExtra("girl_item_data", data);
                startActivity(intent);
            }
        });

        girlItemAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                if (PAGE_COUNT == mTempPageCount && !isReload) {
                    return;
                }
                isLoadMore = true;
                PAGE_COUNT = mTempPageCount;
                fetchData();
            }
        });

        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//可防止Item切换
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(girlItemAdapter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_type_item_layout;
    }

    @Override
    protected void fetchData() {
       presenter.getGirlItemData(mSubtype,PAGE_COUNT);
    }

    public static GirlItemFragment newInstance(String subtype){
        GirlItemFragment fragment=new GirlItemFragment();
        Bundle arguments = new Bundle();
        arguments.putString(SUB_TYPE, subtype);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onRefresh() {
        isLoadMore = false;
        PAGE_COUNT = 1;
        fetchData();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dataEvent(List<GirlItemData> datas){

        if (!datas.get(0).getSubtype().equals(mSubtype)) {
            return;
        }

        if (isLoadMore) {
            if (datas.size() == 0) {
                girlItemAdapter.setLoadEndView(R.layout.load_end_layout);
            } else {
                mTempPageCount++;
                girlItemAdapter.setLoadMoreData(datas);
            }
        } else {
            girlItemAdapter.setNewData(datas);
            if (mSwipeRefreshLayout != null){
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    }
}
