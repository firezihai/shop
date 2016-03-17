package com.fengbeibei.shop.ui;

import java.util.ArrayList;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fengbeibei.shop.R;
import com.fengbeibei.shop.activity.GoodsDetailActivity;
import com.fengbeibei.shop.activity.SubjectWebActivity;
import com.fengbeibei.shop.adapter.AdViewPagerAdapter;
import com.fengbeibei.shop.adapter.Home3GridViewAdapter;
import com.fengbeibei.shop.adapter.HomeGoodsGridViewAdapter;
import com.fengbeibei.shop.adapter.HomeGoodsListGridViewAdapter;
import com.fengbeibei.shop.bean.AdList;
import com.fengbeibei.shop.bean.Home2Data;
import com.fengbeibei.shop.bean.Home3Data;
import com.fengbeibei.shop.bean.HomeGoods;
import com.fengbeibei.shop.bean.HomeGoodsList;
import com.fengbeibei.shop.common.AnimateFirstDisplayListener;
import com.fengbeibei.shop.common.Constants;
import com.fengbeibei.shop.common.HttpClientHelper;
import com.fengbeibei.shop.common.HttpClientHelper.CallBack;
import com.fengbeibei.shop.common.SystemHelper;
import com.fengbeibei.shop.pulltorefresh.library.PullToRefreshBase;
import com.fengbeibei.shop.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.fengbeibei.shop.pulltorefresh.library.PullToRefreshBase.Mode;
import com.fengbeibei.shop.pulltorefresh.library.PullToRefreshScrollView;
import com.fengbeibei.shop.widget.MyGridView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class HomeFragment extends Fragment{
	private PullToRefreshScrollView mPullToRefresh;
	/*主布局*/
	private View homeLayout;
	private ScrollView mScrollView;
	
	/**
	 * 记录按下时的位置
	 */
	private float downLocation;
	private Button mScanBtn;
	private Button mMessageBtn;
	/*轮播广告*/
	private ViewPager mAdViewPager;
	private ArrayList<ImageView> mAdData = new ArrayList<ImageView>();
	private LinearLayout mAdPoint;
	private ArrayList<ImageView> mAdPointData = new ArrayList<ImageView>();
	private  final int SHOW_NEXT = 0011;
	private boolean mShowNext = true;
	private int mCurrentIndex = 0;
	/*菜单*/
	private Button mMenuCategoryBtn;
	private Button mMenuUcenterBtn;
	private Button mMenuOrderBtn;
	private Button mMenuCollectBtn;
	private LinearLayout mHomeData;
	
	/*ImageLoader*/
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = SystemHelper.getDisplayImageOptions(); 
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View homeLayout = inflater.inflate(R.layout.home,parent, false);
		mPullToRefresh = (PullToRefreshScrollView)homeLayout.findViewById(R.id.homePullToRefresh);
		mPullToRefresh.setMode(Mode.BOTH);
		mPullToRefresh.setOnRefreshListener(new OnRefreshListener2<ScrollView>(){

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ScrollView> refreshView) {
				// TODO Auto-generated method stub
				initData();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ScrollView> refreshView) {
				// TODO Auto-generated method stub
				
				initGoodsList();
			}
			
			
		});
		imageLoader.init(ImageLoaderConfiguration.createDefault(HomeFragment.this.getActivity()));
		mScrollView =mPullToRefresh.getRefreshableView();
		initView(homeLayout);
		initData();
		return homeLayout;
	}
	
	public void OnViewClick(View view,final String type ,final String data){
		view.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					 downLocation = event.getX();
				}else if (event.getAction() == MotionEvent.ACTION_MOVE){
					
				} else if (event.getAction() == MotionEvent.ACTION_UP){
					if (downLocation == event.getX()) {
						if(type.equals("keyword")){
							
						} else if (type.equals("url")){
							Intent intent  = new Intent(getActivity(),SubjectWebActivity.class);
							startActivity(intent);
						} else if (type.equals("goods")){
							Intent intent  = new Intent(getActivity(),GoodsDetailActivity.class);
							startActivity(intent);
						}
					}
				}
				return true;
			}
			
		});;
	}
	/**
	 * 初始化所有控件
	 * @param homeLayout
	 */
	public void initView(View homeLayout){
		mAdViewPager = (ViewPager)homeLayout.findViewById(R.id.adViewPager);
		mAdPoint = (LinearLayout) homeLayout.findViewById(R.id.adPoint);
		mHomeData = (LinearLayout)homeLayout.findViewById(R.id.homeData);
	}
	public void initData(){
		HttpClientHelper.asynGet(Constants.HOME_URL, new CallBack(){

			@Override
			public void onFinish(Message response) {
				// TODO Auto-generated method stub
				mPullToRefresh.onRefreshComplete();//加载完成下拉控件取消显示
				if(response.what == HttpStatus.SC_OK){
					
					mHomeData.removeAllViews();
					try{
						String json = (String)response.obj;
						JSONArray arr = new JSONArray(json);
						int size = null == arr ? 0 : arr.length();
						for(int i =0 ; i<size ; i++){
							JSONObject  obj = arr.getJSONObject(i);
							JSONObject jsonObj = new JSONObject(obj.toString());
							if(!jsonObj.isNull("home1")){
							
							}else if (!jsonObj.isNull("home2")){
								String home2Json = jsonObj.getString("home2");
								initHome2(home2Json);
							}else if(!jsonObj.isNull("home3")){
								String home3Json = jsonObj.getString("home3");
								initHome3(home3Json);
							}else if( !jsonObj.isNull("adv_list")){
								initHeadAd(jsonObj.getString("adv_list"));
				
							} else if( !jsonObj.isNull("goods")){
								initHomeGoods( jsonObj.getString("goods") );
							}
						}
						View homGoodsListHead = getActivity().getLayoutInflater().inflate(R.layout.home_goods_list_head, null);
						mHomeData.addView(homGoodsListHead);
					} catch(JSONException e){
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
	public void initGoodsList(){
		
		HttpClientHelper.asynGet(Constants.HOME_GOODS_URL, new CallBack(){

			@Override
			public void onFinish(Message response) {
				mPullToRefresh.onRefreshComplete();//加载完成下拉控件取消显示
				// TODO Auto-generated method stub
				if (response.what == HttpStatus.SC_OK){
					String json = (String)response.obj;
					try{
						JSONObject obj = new JSONObject(json);
						String goodsListJson = obj.getString("list");
						ArrayList<HomeGoodsList> goodsList = HomeGoodsList.newInstance(goodsListJson);
						View homGoodsView = getActivity().getLayoutInflater().inflate(R.layout.home_goods_list, null);
						MyGridView goodsGridView = (MyGridView) homGoodsView.findViewById(R.id.homeGoodsListGridView);
						HomeGoodsListGridViewAdapter goodsGridViewAdapter = new HomeGoodsListGridViewAdapter(HomeFragment.this.getContext());
						goodsGridViewAdapter.setHomeGoodsData(goodsList);
						goodsGridView.setAdapter(goodsGridViewAdapter);
						mHomeData.addView(homGoodsView);
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
	public void initHome2(String json){
		Home2Data home2Data = Home2Data.newInstance(json);
		View home2View = getActivity().getLayoutInflater().inflate(R.layout.home_item2, null);
		TextView home2Title = (TextView)home2View.findViewById(R.id.home2Title);
		
		ImageView imageView1 = (ImageView) home2View.findViewById(R.id.home2Image1);
		ImageView imageView2 = (ImageView) home2View.findViewById(R.id.home2Image2);
		ImageView imageView3 = (ImageView) home2View.findViewById(R.id.home2Image3);
		imageLoader.displayImage(home2Data.getSquareImage(), imageView1, options, animateFirstListener);
		imageLoader.displayImage(home2Data.getRectangle1Image(), imageView2, options, animateFirstListener);
		imageLoader.displayImage(home2Data.getRectangle2Image(), imageView3, options, animateFirstListener);
		if(!home2Data.getTitle().equals("") && !home2Data.getTitle().equals("null") && home2Data.getTitle() != null){
			home2Title.setVisibility(View.VISIBLE);
			home2Title.setText(home2Data.getTitle());
		}else{
			home2Title.setVisibility(View.GONE);
		}
		mHomeData.addView(home2View);
	}
	
	public void initHome3(String json){

		Home3Data home3Data = Home3Data.newInstance(json);
		ArrayList<Home3Data> home3DataItemList = Home3Data.newInstanceList(home3Data.getItem());
		
		View home3View = getActivity().getLayoutInflater().inflate(R.layout.home_item3, null);
		TextView home3Title = (TextView) home3View.findViewById(R.id.home3Title);
		
		MyGridView home3GridView = (MyGridView) home3View.findViewById(R.id.home3GridView);
		home3GridView.setFocusable(false);
		Home3GridViewAdapter home3GridViewAdapter= new Home3GridViewAdapter(HomeFragment.this.getContext());
		home3GridViewAdapter.setHome3Data(home3DataItemList);
		home3GridView.setAdapter(home3GridViewAdapter);
		if (!home3Data.getTitle().equals("") && !home3Data.getTitle().equals("null") && home3Data.getTitle() != null){
			home3Title.setText(home3Data.getTitle());
			home3Title.setVisibility(View.VISIBLE);
		}else{
			home3Title.setVisibility(View.GONE);
		}
		
		mHomeData.addView(home3View);
	}
	
	public void initHomeGoods(String json){
		try{
			JSONObject itemObj = new JSONObject(json);
			String item = itemObj.getString("item");
			String title = itemObj.getString("title");
			if ( !item.equals("[]")){
				ArrayList<HomeGoods> homeGoods = HomeGoods.newInstance(json);
				View homeGoodsView = getActivity().getLayoutInflater().inflate(R.layout.home_goods, null);
				MyGridView homeGoodsGridView = (MyGridView)homeGoodsView.findViewById(R.id.homeGoodsGridView);
				HomeGoodsGridViewAdapter homeGoodsGridViewAdapter = new HomeGoodsGridViewAdapter(HomeFragment.this.getContext());
				homeGoodsGridViewAdapter.setHomeGoods(homeGoods);
				homeGoodsGridView.setFocusable(false);
				homeGoodsGridView.setAdapter(homeGoodsGridViewAdapter);
				homeGoodsGridViewAdapter.notifyDataSetChanged();
				TextView homeGoodsTitle = (TextView)homeGoodsView.findViewById(R.id.homeGoodsTitle);
				if(!title.equals("") && !title.equals("null") && title != null){			
					homeGoodsTitle.setText(title);
					homeGoodsTitle.setVisibility(View.VISIBLE);
				}else{
					homeGoodsTitle.setVisibility(View.GONE);
				}
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		
	}
	/**
	 * 顶部广告
	 * @param adList  广告信息数组
	 */
	public void initHeadAd(String json){
		ArrayList<AdList> adList = null;
		try{
			JSONObject itemObj = new JSONObject(json);
			String item = itemObj.getString("item");
			if(!item.equals("[]")){
				 adList =AdList.newInstance(item);
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		if(adList.size() > 0 && adList != null){
			mHandler.removeMessages(SHOW_NEXT);
			mAdViewPager.removeAllViews();
			mAdPoint.removeAllViews();
			mAdData.clear();
			mAdPointData.clear();
			for(int i =0 ; i<adList.size() ; i++){
				AdList ad = adList.get(i);
				ImageView imageView = new ImageView(HomeFragment.this.getActivity());
				imageView.setScaleType(ScaleType.FIT_XY);
				imageView.setImageResource(R.drawable.dic_av_item_pic_bg);
				imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
				imageLoader.displayImage(ad.getImage(), imageView, options,animateFirstListener);
				mAdData.add(imageView);
				ImageView imageViewPoint = new ImageView(HomeFragment.this.getActivity());
				LinearLayout.LayoutParams pointParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,1);
				imageViewPoint.setLayoutParams(pointParams);
				imageViewPoint.setImageResource(R.drawable.home_point);
				
				mAdPointData.add(imageViewPoint);
				mAdPoint.addView(imageViewPoint);
			}
			AdViewPagerAdapter adViewPagerAdapter = new AdViewPagerAdapter(mAdData);
			mAdViewPager.setAdapter(adViewPagerAdapter); 
			mAdViewPager.addOnPageChangeListener(new OnPageChangeListener(){
	
				@Override
				public void onPageScrollStateChanged(int arg0) {
					// TODO Auto-generated method stub
					
				}
	
				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					// TODO Auto-generated method stub
					
				}
	
				@Override
				public void onPageSelected(int arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
			if(mAdData.size()> 0){
				selectPoint(0);
				mHandler.sendEmptyMessageDelayed(SHOW_NEXT, 3000);
			}
		}
	}
	
	Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if(msg.what == SHOW_NEXT){
				if(mShowNext){
					showNext();
				}else{
					showPrev();
				}
				mHandler.sendEmptyMessageDelayed(SHOW_NEXT, 3000);
			}
			//switch(msg.what)
			super.handleMessage(msg);
		}
	};
	/**
	 * 下一张图片
	 */
	private void showNext(){
		mCurrentIndex++;
		if(mCurrentIndex > mAdData.size() -1){
			unSelectPoint(mAdData.size() -1);
			mCurrentIndex = 0;
			selectPoint(mCurrentIndex);
		}else{
			unSelectPoint(mCurrentIndex-1);
			selectPoint(mCurrentIndex);
		}
		mAdViewPager.setCurrentItem(mCurrentIndex);
	}
	/**
	 * 上一张图片
	 */
	private void showPrev(){
		mCurrentIndex--;
		if(mCurrentIndex < -1){
			unSelectPoint(mCurrentIndex+1);
			mCurrentIndex = mAdData.size() -1;
			selectPoint(mCurrentIndex);
		}else{
			unSelectPoint(mCurrentIndex+1);
			selectPoint(mCurrentIndex);
		}
		mAdViewPager.setCurrentItem(mCurrentIndex);
	}
	/**
	 * 激活已选中图片对应点
	 */
	private void selectPoint(int index){
		ImageView img = mAdPointData.get(index);
		img.setSelected(true);
	}
	/**
	 * 禁用未选中图片对应点
	 * @param index
	 */
	private void unSelectPoint(int index){
		ImageView img = mAdPointData.get(index);
		img.setSelected(false);
	}
}
