package ck.itheima.com.takeout.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ck.itheima.com.takeout.R;
import ck.itheima.com.takeout.model.net.Seller;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * 类名:    HomeAdapter
 * 创建者:  ckqu
 * 创建时间:2017/3/19 0019 下午 10:53
 * 包名:    ck.itheima.com.takeout.ui
 * 更新者:  $Author$ $Date$
 * 描述:    TODO
 */

public class HomeAdapter extends RecyclerView.Adapter {

    public static final int TYPE_TITLE = 0;
    public static final int TYPE_DIVISION = 1;
    public static final int TYPE_SELLER = 2;


    private int GROUP_SIZE = 8;

    private Context mContext;
    private List<Seller> mNearbySellers = new ArrayList<>();
    private List<Seller> mOtherSellers = new ArrayList<>();
    private View mTitleView;
    private View mSellerView;
    private View mDivisionView;

    public void setList(List<Seller> nearby, List<Seller> other) {
        this.mNearbySellers = nearby;
        this.mOtherSellers = other;
        notifyDataSetChanged();
    }

    public HomeAdapter(Context context) {
        mContext = context;

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TITLE;
        }
        //        else if( position == mNearbySellers.size() + 1){
        //            //第一个分割线
        //            return TYPE_DIVISION;
        //        }
        else if (position > mNearbySellers.size() && (position - 1 - mNearbySellers.size()) %
                (GROUP_SIZE + 1) == 0) {
            //
            return TYPE_DIVISION;
        } else {
            return TYPE_SELLER;
        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
                mTitleView = View.inflate(mContext, R.layout.item_title, null);
                TitleHolder titleHolder = new TitleHolder(mTitleView);
                return titleHolder;
            case TYPE_SELLER:
                mSellerView = View.inflate(mContext, R.layout.item_seller, null);
                SellerHolder sellerHolder = new SellerHolder(mSellerView);
                return sellerHolder;
            case TYPE_DIVISION:
                mDivisionView = View.inflate(mContext, R.layout.item_division, null);
                DivitionHolder divitionHolder = new DivitionHolder(mDivisionView);
                return divitionHolder;
            default:
                Toast.makeText(mContext, "竟然有第四种holder", Toast.LENGTH_SHORT).show();
                return null;
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_TITLE:
                TitleHolder titleHolder = (TitleHolder) holder;
                titleHolder.setData("我是大哥");
                Logger.d("我是大哥哥");
                break;
            case TYPE_DIVISION:
                DivitionHolder divisionHolder = (DivitionHolder) holder;
                divisionHolder.setData("-----------分割线------------");
                break;
            case TYPE_SELLER:
                SellerHolder sellerHolder = (SellerHolder) holder;
                //                sellerHolder.setData("我是商家：" + position);
                //TODO:区分附近和其他商家，把position转成集合的index
                int index;
                if (position < mNearbySellers.size() + 1) {
                    //都属于附近商家
                    index = position - 1;
                    sellerHolder.setData(mNearbySellers.get(index));
                } else {
                    //TODO:  头布局  （附近0 - 附近9） 分割线 （其他0-其他9） 分割线 （其他10-其他19） 分割线 （其他20-其他24）
                    index = position - 1 - mNearbySellers.size() - 1; //去除头部、附近商家、第一个分割线
                    index -= index / (GROUP_SIZE + 1); //去除分割线总数
                    sellerHolder.setData(mOtherSellers.get(index));
                }
                break;
            default:

                break;
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mNearbySellers.size() == 0 && mOtherSellers.size() == 0) {
            return 0;
        }
        if (mNearbySellers.size() > 0) {
            count += 1; //头
            count += mNearbySellers.size();
            count += 1;
        } else {
            count += 1;
        }
        if (mOtherSellers.size() > 0) {
            count += mOtherSellers.size();
            count += mOtherSellers.size() / GROUP_SIZE;
            if (mOtherSellers.size() % GROUP_SIZE == 0) {
                count -= 1;
            }
        }
        return count;
    }

    class DivitionHolder extends RecyclerView.ViewHolder {

        DivitionHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(String str) {


        }
    }

    class SellerHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.seller_logo)
        ImageView mSellerLogo;
        @InjectView(R.id.tvCount)
        TextView mTvCount;
        @InjectView(R.id.tv_title)
        TextView mTvTitle;
        @InjectView(R.id.ratingBar)
        RatingBar mRatingBar;
        @InjectView(R.id.tv_home_sale)
        TextView mTvHomeSale;
        @InjectView(R.id.tv_home_send_price)
        TextView mTvHomeSendPrice;
        @InjectView(R.id.tv_home_distance)
        TextView mTvHomeDistance;

        SellerHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(Seller seller) {
            mTvTitle.setText(seller.getName());
            String url = seller.getIcon();
            Logger.d(url);
            Picasso.with(mContext).load(url).transform(new CropCircleTransformation()).into(mSellerLogo);

            mRatingBar.setRating(Float.parseFloat(seller.getScore()));
            mTvHomeSale.setText("月售" + seller.getSale() + "单");
            mTvHomeSendPrice.setText("￥" + seller.getSendPrice() + "起送/配送费￥" + seller.getDeliveryFee());
            mTvHomeDistance.setText(seller.getDistance());

        }
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.slider)
        SliderLayout mSlider;

        TitleHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(String str) {
            testData(mContext);

        }

        private void testData(Context context) {//添加轮播图
            HashMap<String, String> url_maps = new HashMap<>();
            url_maps.put("Hannibal", "http://static2.hypable" +
                    ".com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
            url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
            url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
            url_maps.put("Game of Thrones", "http://images.boomsbeat" +
                    ".com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
            for (String desc : url_maps.keySet()) {
                TextSliderView textSliderView = new TextSliderView(mTitleView.getContext());
                textSliderView.description(desc)
                        .image(url_maps.get(desc));
                mSlider.addSlider(textSliderView);

            }
        }
    }
}