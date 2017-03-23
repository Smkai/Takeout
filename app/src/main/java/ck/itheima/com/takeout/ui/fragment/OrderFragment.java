package ck.itheima.com.takeout.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ck.itheima.com.takeout.R;
import ck.itheima.com.takeout.presenter.OrderFragmentPresenter;
import ck.itheima.com.takeout.ui.adapter.OrderAdapter;

/**
 * 类名:    HomeFragment
 * 创建者:  ckqu
 * 创建时间:2017/3/19 0019 下午 9:59
 * 包名:    ck.itheima.com.takeout
 * 更新者:  $Author$ $Date$
 * 描述:    TODO
 */

public class OrderFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.rv_order_list)
    RecyclerView mRvOrderList;
    @InjectView(R.id.srl_order)
    SwipeRefreshLayout mSrlOrder;
    private OrderAdapter mOrderAdapter;
    private OrderFragmentPresenter mOrderFragmentPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        View OrderView = inflater.inflate(R.layout.fragment_order, null);
        ButterKnife.inject(this, OrderView);

        mOrderFragmentPresenter = new OrderFragmentPresenter(this);
        mOrderAdapter = new OrderAdapter(getContext());

        mRvOrderList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        mRvOrderList.setAdapter(mOrderAdapter);
        mSrlOrder.setOnRefreshListener(this);
        return OrderView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onRefresh() {

    }
}
