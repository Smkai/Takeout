package ck.itheima.com.takeout.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ck.itheima.com.takeout.R;
import ck.itheima.com.takeout.ui.activity.LoginActivity;

/**
 * 类名:    HomeFragment
 * 创建者:  ckqu
 * 创建时间:2017/3/19 0019 下午 9:59
 * 包名:    ck.itheima.com.takeout
 * 更新者:  $Author$ $Date$
 * 描述:    TODO
 */

public class UserFragment extends Fragment {
    @InjectView(R.id.tv_user_setting)
    ImageView mTvUserSetting;
    @InjectView(R.id.iv_user_notice)
    ImageView mIvUserNotice;
    @InjectView(R.id.login)
    ImageView mLogin;
    @InjectView(R.id.username)
    TextView mUsername;
    @InjectView(R.id.phone)
    TextView mPhone;
    @InjectView(R.id.ll_userinfo)
    LinearLayout mLlUserinfo;
    @InjectView(R.id.iv_address_manager)
    ImageView mIvAddressManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        View userView = inflater.inflate(R.layout.fragment_user, null);
        ButterKnife.inject(this, userView);
        return userView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.login)
    public void onClick() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }
}
