package ck.itheima.com.takeout.model.net;

import ck.itheima.com.takeout.utils.Constant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 类名:    TateoutService
 * 创建者:  ckqu
 * 创建时间:2017/3/20 0020 下午 8:12
 * 包名:    ck.itheima.com.takeout.model.net
 * 更新者:  $Author$ $Date$
 * 描述:    TODO
 */

public interface TateoutService {

    @GET(Constant.HOME)

    Call<ResponseInfo> loadHome();

    @GET(Constant.ORDER)
    Call<ResponseInfo> getOrderList(@Query("user")int  userId);

    //登录功能
    @GET(Constant.LOGIN)
    Call<ResponseInfo> loginByPhone(@Query("phone") String phone, @Query("type") int type);

}
