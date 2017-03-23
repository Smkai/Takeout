package ck.itheima.com.takeout.presenter;

import android.widget.Toast;

import ck.itheima.com.takeout.model.net.ResponseInfo;
import ck.itheima.com.takeout.model.net.TateoutService;
import ck.itheima.com.takeout.utils.Constant;
import ck.itheima.com.takeout.utils.TakeoutApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 类名:    BasePresenter
 * 创建者:  ckqu
 * 创建时间:2017/3/20 0020 下午 7:56
 * 包名:    ck.itheima.com.takeout.presenter
 * 更新者:  $Author$ $Date$
 * 描述:    TODO
 */

public abstract class BasePresenter {

    protected Retrofit mRetrofit;
    protected  TateoutService mService;

    public BasePresenter() {
        mRetrofit = new Retrofit.Builder()
                            .baseUrl(Constant.HOST)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


        mService = mRetrofit.create(TateoutService.class);
    }

    Callback<ResponseInfo> mCallback = new Callback<ResponseInfo>() {
        @Override
        public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
            ResponseInfo responseInfo = response.body();
            if (responseInfo.getCode().equals("0")){
                parserJson(responseInfo.getData());
            }else if (responseInfo.getCode().equals("-1")){
                //数据为空
                Toast.makeText(TakeoutApp.sInstace, "数据为空", Toast.LENGTH_SHORT).show();

            }else if (responseInfo.getCode().equals("-2")){
                Toast.makeText(TakeoutApp.sInstace, "服务内部错误500", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(TakeoutApp.sInstace, "其他错误", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseInfo> call, Throwable t) {
            //没连接成功服务器
            Toast.makeText(TakeoutApp.sInstace, "压根没连上服务器", Toast.LENGTH_SHORT).show();
        }
    };

    protected abstract void parserJson(String json);// 子类自行去解析数据

}
