package ck.itheima.com.takeout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;
import butterknife.InjectView;
import ck.itheima.com.takeout.ui.fragment.HomeFragment;
import ck.itheima.com.takeout.ui.fragment.MoreFragment;
import ck.itheima.com.takeout.ui.fragment.OrderFragment;
import ck.itheima.com.takeout.ui.fragment.UserFragment;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.main_fragment_container)
    FrameLayout mMainFragmentContainer;
    @InjectView(R.id.main_bottome_switcher_container)
    LinearLayout mMainBottomeSwitcherContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initFragment();
        initBottom();
        changUi(0);
    }


    private void initBottom() {
        int childCount = mMainBottomeSwitcherContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = mMainBottomeSwitcherContainer.getChildAt(i);
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = mMainBottomeSwitcherContainer.indexOfChild(child);
                    changUi(index);
                }
            });

        }
    }

    private void changUi(int index) {
        int count = mMainBottomeSwitcherContainer.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = mMainBottomeSwitcherContainer.getChildAt(i);
            if (index == i) {
                setEnabled(child, false);
            } else {
                setEnabled(child, true);
            }
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container,
                mFragments.get
                (index))
                .commit();
    }

    private void setEnabled(View child, boolean b) {
        child.setEnabled(b);
        if (child instanceof ViewGroup) {
            int count = ((ViewGroup) child).getChildCount();
            for (int i = 0; i < count; i++) {
                View item = ((ViewGroup) child).getChildAt(i);
                item.setEnabled(b);
            }
        }
    }

    private List<Fragment> mFragments = new ArrayList<>();

    private void initFragment() {
        mFragments.add(new HomeFragment());
        mFragments.add(new OrderFragment());
        mFragments.add(new UserFragment());
        mFragments.add(new MoreFragment());

    }
}
