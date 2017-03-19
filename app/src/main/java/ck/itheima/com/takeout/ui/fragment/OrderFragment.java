package ck.itheima.com.takeout.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 类名:    HomeFragment
 * 创建者:  ckqu
 * 创建时间:2017/3/19 0019 下午 9:59
 * 包名:    ck.itheima.com.takeout
 * 更新者:  $Author$ $Date$
 * 描述:    TODO
 */

public class OrderFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getContext());
                tv.setGravity(Gravity.CENTER);
                tv.setTextColor(Color.BLUE);
                tv.setText(this.getClass()
                        .getSimpleName());
                return tv;
    }
}
