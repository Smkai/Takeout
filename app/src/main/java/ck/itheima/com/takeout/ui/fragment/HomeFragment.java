package ck.itheima.com.takeout.ui.fragment;
import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ck.itheima.com.takeout.R;
import ck.itheima.com.takeout.presenter.HomeFragmentPresenter;
import ck.itheima.com.takeout.ui.adapter.HomeAdapter;

/**
 * 类名:    HomeFragment
 * 创建者:  ckqu
 * 创建时间:2017/3/19 0019 下午 9:59
 * 包名:    ck.itheima.com.takeout
 * 更新者:  $Author$ $Date$
 * 描述:    TODO
 */

public class HomeFragment extends Fragment {


    @InjectView(R.id.home_tv_address)
    TextView mHomeTvAddress;
    @InjectView(R.id.ll_title_search)
    LinearLayout mLlTitleSearch;
    @InjectView(R.id.ll_title_container)
    LinearLayout mLlTitleContainer;
    @InjectView(R.id.rv_home)
    RecyclerView mRvHome;
    public HomeAdapter mHomeAdapter;
    private HomeFragmentPresenter mHomeFragmentPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View homeView = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.inject(this, homeView);

        mHomeFragmentPresenter = new HomeFragmentPresenter(this);//初始化

        mHomeAdapter = new HomeAdapter(getContext());
        mRvHome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRvHome.setAdapter(mHomeAdapter);

//        mNearby.clear();
//        mOther.clear();

        return homeView;
    }

    int sumY;
    float distance = 200f;
    ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();
    int startColor = 0x553190E8;
    int endColor = 0xff3190E8;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fillData();


        mRvHome.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dy： d指的是delta，差值
                //                Log.e("home", "dy:" + dy);
                //当前滚动了多少
                sumY += dy;
                //                float percent = sumY /distance;
                //#ffddaa
                int bgColor;
                if (sumY <= 0) {
                    bgColor = startColor;
                } else if (sumY > distance) {
                    bgColor = endColor;
                } else {
                    bgColor = (int) mArgbEvaluator.evaluate(sumY / distance, startColor, endColor);
                }
                mLlTitleContainer.setBackgroundColor(bgColor);
            }

        });
    }

//    private List<Seller> mNearby = new ArrayList<>();
//    private List<Seller> mOther = new ArrayList<>();

    public void fillData() {
     mHomeFragmentPresenter.loadHomeInfo();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
