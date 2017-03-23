package ck.itheima.com.takeout.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ck.itheima.com.takeout.R;
import ck.itheima.com.takeout.utils.SMSUtil;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 类名:    LoginActivity
 * 创建者:  ckqu
 * 创建时间:2017/3/21 0021 上午 11:55
 * 包名:    ck.itheima.com.takeout.ui.activity
 * 更新者:  $Author$ $Date$
 * 描述:    TODO
 */
public class LoginActivity extends AppCompatActivity {
    private static final int TIME_MINUS = 1000;
    private static final int TIME_IS_OUT = 1001;
    @InjectView(R.id.iv_user_back)
    ImageView mIvUserBack;
    @InjectView(R.id.iv_user_password_login)
    TextView mIvUserPasswordLogin;
    @InjectView(R.id.et_user_phone)
    EditText mEtUserPhone;
    @InjectView(R.id.tv_user_code)
    TextView mTvUserCode;
    @InjectView(R.id.et_user_code)
    EditText mEtUserCode;
    @InjectView(R.id.login)
    TextView mLogin;
    private String mPhone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        SMSSDK.initSDK(this, "1c619bbff0ba8", "53a72e5dd7acaf9c3c609e6eb1d023ae");

        SMSSDK.registerEventHandler(mEventHandler);
    }

    EventHandler mEventHandler = new EventHandler() {

        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    Logger.e("提交验证码成功");
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    Logger.e("获取验证码成功");
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mEventHandler != null) {
            SMSSDK.unregisterEventHandler(mEventHandler);
        }
    }

    @OnClick({R.id.iv_user_back, R.id.tv_user_code, R.id.login})
    public void onClick(View view) {
        mPhone = mEtUserPhone.getText().toString().trim();

        switch (view.getId()) {
            case R.id.iv_user_back:
                finish();
                break;
            case R.id.tv_user_code:
                if (SMSUtil.judgePhoneNums(this, mPhone)) {

                    SMSSDK.getVerificationCode("86", mPhone);

                    mTvUserCode.setText("剩余时间(" + time + ")秒");
                    mTvUserCode.setEnabled(false);
                    new Thread(new CutDownTask()){}.start();
                }

                break;
            case R.id.login:

                String code = mEtUserCode.getText().toString().trim();
                if (SMSUtil.judgePhoneNums(this, mPhone)) {
                    SMSSDK.submitVerificationCode("86", mPhone, code);
                }

                break;
        }
    }
    private int time = 60;

    private class CutDownTask implements Runnable {

        @Override
        public void run() {
            for (; time > 0 ; time --) {
                SystemClock.sleep(999);
                mHandler.sendEmptyMessage(TIME_MINUS);
            }
            mHandler.sendEmptyMessage(TIME_IS_OUT);
        }
    }
      private Handler mHandler = new Handler(){
          @Override
          public void handleMessage(Message msg) {
              super.handleMessage(msg);
              switch (msg.what){
                  case TIME_MINUS:
                      mTvUserCode.setText("剩余时间(" + time + ")秒");
                      break;
                  case TIME_IS_OUT:
                      mTvUserCode.setText("重新发送");
                      mTvUserCode.setEnabled(true);
                      time = 60;
                      break;
              }
          }
      };
}