package ck.itheima.com.takeout.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ck.itheima.com.takeout.model.net.Order;

/**
 * 类名:    OrderAdapter
 * 创建者:  ckqu
 * 创建时间:2017/3/21 0021 上午 11:06
 * 包名:    ck.itheima.com.takeout.ui.adapter
 * 更新者:  $Author$ $Date$
 * 描述:    TODO
 */

public class OrderAdapter extends RecyclerView.Adapter {

    private Context mContext;



    private List<Order> mOrderList = new ArrayList<>();

    public OrderAdapter(Context context) {
        mContext = context;
    }

    public void setOrderList(List<Order> orderList) {
        mOrderList = orderList;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
