package com.fengbeibei.shop.activity;

import java.util.ArrayList;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fengbeibei.shop.R;
import com.fengbeibei.shop.common.AnimateFirstDisplayListener;
import com.fengbeibei.shop.common.Constants;
import com.fengbeibei.shop.common.HttpClientHelper;
import com.fengbeibei.shop.common.SystemHelper;
import com.fengbeibei.shop.common.HttpClientHelper.CallBack;
import com.fengbeibei.shop.utils.ScreenUtil;
import com.fengbeibei.shop.widget.indicator.CirclePageIndicator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class GoodsDetailActivity extends Activity{
	
	/*所有相关控件*/
	private TextView mGoodsName;
	private TextView mGoodsPrice;
	private TextView mGoodsMarketPrice;
	private TextView mSaleCount;
	private TextView mGoodsSerial;
	private ViewPager mImageViewPager;
	private CirclePageIndicator mPointViewPager;
	
	/*商品轮播相关*/
	private ArrayList<ImageView> goodsImageData = new ArrayList<ImageView>();
	private boolean mIsMoving = false;
	private boolean mIsScroll = false;
	private int mCurrentIndex = 0;
	private Handler mHandler;
	private final int SHOW_NEXT = 0x0011;
	
	
	protected ImageLoader mImageLoader = ImageLoader.getInstance();
	private DisplayImageOptions mOptions = SystemHelper.getDisplayImageOptions();
	private ImageLoadingListener mAnimateListener = new AnimateFirstDisplayListener();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_detail);
		initView();
		Intent intent = getIntent();
		String  goods_id = intent.getStringExtra("goods_id");
		String request_url = Constants.GOODS_DETAIL_URL+"&goods_id="+goods_id;
		HttpClientHelper.asynGet(request_url, new CallBack(){

			@Override
			public void onFinish(Message response) {
				// TODO Auto-generated method stub
				if(response.what == HttpStatus.SC_OK){
					try{
						String json = (String)response.obj;

						JSONObject obj = new JSONObject(json);
						String goods_info = obj.getString("goods_info");
						String goods_commend_list = obj.getString("goods_commend_list");
						String goods_image = obj.getString("goods_image");
						String gift_array = obj.getString("gift_array");
						String mansong_info = obj.getString("mansong_info");
						String spec_image = obj.getString("spec_image");
						String spec_list = obj.getString("spec_list");
						String store_info= obj.getString("store_info");
						if(!goods_image.equals("") && !goods_image.equals("null")){
							
							String[] goods_image_arr = goods_image.split(",");
							if(goods_image_arr.length>0){
								GoodsImage(goods_image_arr);
							}
						}
						
						
					} catch (JSONException e){
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private void initView(){
		mGoodsName = (TextView) findViewById(R.id.goodsImage);
		mGoodsPrice = (TextView) findViewById(R.id.goodsPrice);
		mGoodsMarketPrice = (TextView) findViewById(R.id.goodsMarketPrice);
		mSaleCount = (TextView) findViewById(R.id.saleCount);
		mGoodsSerial = (TextView) findViewById(R.id.goodsSerial);
		mImageViewPager = (ViewPager) findViewById(R.id.goodsImageViewPager);
		mPointViewPager = (CirclePageIndicator) findViewById(R.id.goodsImagePoint);
	}
	
	private void GoodsImage(String[] goodsImage){
		int screenWidth = ScreenUtil.getScreenWidth(GoodsDetailActivity.this);
		mImageViewPager.removeAllViews();
		goodsImageData.clear();
		for(int i=0 ;i<goodsImage.length;i++){
			String url = goodsImage[i];
			ImageView imageView = new ImageView(GoodsDetailActivity.this);
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setLayoutParams(new LinearLayout.LayoutParams(screenWidth,screenWidth));
			mImageLoader.displayImage(url, imageView, mOptions, mAnimateListener);
			goodsImageData.add(imageView);
		}
		GoodsImageAdapter adapter = new GoodsImageAdapter(goodsImageData);
		mImageViewPager.setAdapter(adapter);
		
		mPointViewPager.setViewPager(mImageViewPager);
		mPointViewPager.setCurrentItem(0);
		mImageViewPager.setOnTouchListener(new RegOnTouchListener());
		mImageViewPager.addOnPageChangeListener(new OnPageChangeListener(){

			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				//ViewPager.SCROLL_STATE_IDLE　暂停状态
				mIsMoving = state != ViewPager.SCROLL_STATE_IDLE;
				mIsScroll = state != ViewPager.SCROLL_STATE_IDLE;
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// TODO Auto-generated method stub
				mIsMoving = position != mCurrentIndex;
			}

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				mIsMoving = false;
				mCurrentIndex = position;
				mIsScroll = false;
			}
			
		});
		mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch(msg.what){
					case SHOW_NEXT:
						showNext();
						mImageViewPager.setCurrentItem(mCurrentIndex);
						mHandler.sendEmptyMessageDelayed(SHOW_NEXT, 3800);
						break;
					default:
						break;
				}
				super.handleMessage(msg);
			}
			
		};
	}
	
	private void showNext(){
		if(!mIsMoving && !mIsScroll){
			mCurrentIndex = (mCurrentIndex +1) % goodsImageData.size();
		}
	}
	
	private class GoodsImageAdapter extends PagerAdapter{
		private ArrayList<ImageView> mData;
		
		
		/**
		 * @param data
		 */
		public GoodsImageAdapter(ArrayList<ImageView> data) {
			super();
			this.mData = data;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return this.mData.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return (arg0 == arg1);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			super.destroyItem(container, position, object);
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return super.getItemPosition(object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			((ViewPager) container).addView(mData.get(position));
			return mData.get(position);
		}
		
	}
	private class RegOnTouchListener implements View.OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch(event.getAction()){
				case MotionEvent.ACTION_UP:
					mIsMoving = false;
					break;
				case MotionEvent.ACTION_CANCEL:
					mIsMoving = false;
					break;
				case MotionEvent.ACTION_MOVE:
					mIsMoving = true;
					break;
			}
			return false;
		}
		
	}
}
