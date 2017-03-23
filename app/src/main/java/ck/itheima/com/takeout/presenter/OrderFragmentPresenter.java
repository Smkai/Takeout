package ck.itheima.com.takeout.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import ck.itheima.com.takeout.model.net.Order;
import ck.itheima.com.takeout.model.net.ResponseInfo;
import ck.itheima.com.takeout.ui.fragment.OrderFragment;
import retrofit2.Call;

/**
 * 类名:    OrderFragmentPresenter
 * 创建者:  ckqu
 * 创建时间:2017/3/21 0021 上午 10:51
 * 包名:    ck.itheima.com.takeout.presenter
 * 更新者:  $Author$ $Date$
 * 描述:    TODO
 */

public class OrderFragmentPresenter extends BasePresenter {
    private OrderFragment mOrderFragment;

    public OrderFragmentPresenter(OrderFragment orderFragment) {
        mOrderFragment = orderFragment;
    }

    public void getOrderList(int userId) {
        Call<ResponseInfo> orderList = mService.getOrderList(userId);
        orderList.enqueue(mCallback);
    }


    @Override
    protected void parserJson(String json) {
        Gson gson = new Gson();
        List<Order> orderlist = gson.fromJson(json, new TypeToken<List<Order>>() {
        }.getType());



    }
}
