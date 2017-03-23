package ck.itheima.com.takeout.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import ck.itheima.com.takeout.model.net.ResponseInfo;
import ck.itheima.com.takeout.model.net.Seller;
import ck.itheima.com.takeout.ui.fragment.HomeFragment;
import retrofit2.Call;

/**
 * 类名:    HomeFragmentPresenter
 * 创建者:  ckqu
 * 创建时间:2017/3/20 0020 下午 7:56
 * 包名:    ck.itheima.com.takeout.presenter
 * 更新者:  $Author$ $Date$
 * 描述:    TODO
 */

public class HomeFragmentPresenter extends BasePresenter {

    private HomeFragment mHomeFragment;

    public HomeFragmentPresenter(HomeFragment homeFragment) {//传来一个home引用
        mHomeFragment = homeFragment;
    }

    public void loadHomeInfo(){
        Call<ResponseInfo> homeCall = mService.loadHome();
        homeCall.enqueue(mCallback);

    }

    @Override
    protected void parserJson(String json) {
        Gson gson = new Gson();
        try {
            JSONObject jsonObject = new JSONObject(json);
            String nearby = jsonObject.getString("nearbySellerList");
            //匿名子类
            List<Seller> nearbySellers = gson.fromJson(nearby, new TypeToken<List<Seller>>(){}.getType());
            String other = jsonObject.getString("otherSellerList");
            //匿名子类
            List<Seller> otherSellers = gson.fromJson(other, new TypeToken<List<Seller>>(){}.getType());

            mHomeFragment.mHomeAdapter.setList(nearbySellers, otherSellers);//给Adapter传递数据
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
