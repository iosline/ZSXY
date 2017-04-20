package com.beijing.zzu.zsxy.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.adapter.GankItemAdapter;
import com.beijing.zzu.zsxy.adapter.OnItemClickListeners;
import com.beijing.zzu.zsxy.adapter.OnLoadMoreListener;
import com.beijing.zzu.zsxy.adapter.ViewHolder;
import com.beijing.zzu.zsxy.model.GankItem;
import com.beijing.zzu.zsxy.presenter.GankItemPresenter;
import com.beijing.zzu.zsxy.utils.CommonUtils;
import com.beijing.zzu.zsxy.view.GankItemView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jiayongkai on 2017/4/18.
 */

public class GankItemFragment extends BaseFragment<GankItemView,GankItemPresenter> implements GankItemView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.type_item_recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.type_item_fab)
    FloatingActionButton mFab;

    @BindView(R.id.type_item_swipfreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private String type;
    private List<GankItem> gankItemList=new ArrayList<>();
    private GankItemAdapter gankItemAdapter;

    private int PAGE_COUNT=1;
    private int mTempPageCount=2;
    private boolean isLoadMore;

    private int mLastVisibleItemPosition;

    @Override
    protected void initData() {
        type=getArguments().getString("type");

    }

    @Override
    protected void initPresenter() {
         presenter=new GankItemPresenter();
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

        gankItemAdapter=new GankItemAdapter(getActivity(),gankItemList,true);
        gankItemAdapter.setLoadingView(R.layout.load_loading_layout);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        gankItemAdapter.setOnItemClickListener(new OnItemClickListeners<GankItem>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, GankItem data, int position) {

            }
        });

        gankItemAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
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

        mRecyclerView.setAdapter(gankItemAdapter);


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mLastVisibleItemPosition < layoutManager.findLastVisibleItemPosition() && mLastVisibleItemPosition == 12) {
                    mFab.show();
                }

                if (mLastVisibleItemPosition > layoutManager.findLastVisibleItemPosition() && mFab.isShown()) {
                    mFab.hide();
                }

                mLastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @OnClick(R.id.type_item_fab)
    void onClick(View view) {
        mRecyclerView.smoothScrollToPosition(0);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void fetchData() {
        presenter.getGankItemData(type,PAGE_COUNT+"");
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetError() {
        if (isLoadMore){
            gankItemAdapter.setLoadFailedView(R.layout.load_failed_layout);
        }else {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        isLoadMore = false;
        PAGE_COUNT = 1;
        fetchData();
    }

    @Override
    public void onSuccess(List<GankItem> datas) {
        if (isLoadMore){
            if (datas.size() == 0){
                gankItemAdapter.setLoadEndView(R.layout.load_end_layout);
            }else {
                gankItemAdapter.setLoadMoreData(datas);
                mTempPageCount++;
            }
        }else {
            mSwipeRefreshLayout.setRefreshing(false);
            gankItemAdapter.setNewData(datas);
        }
    }
}
