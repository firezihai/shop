package com.fengbeibei.shop.ui;

import java.util.ArrayList;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fengbeibei.shop.R;
import com.fengbeibei.shop.adapter.AdViewPagerAdapter;
import com.fengbeibei.shop.bean.AdList;
import com.fengbeibei.shop.common.AnimateFirstDisplayListener;
import com.fengbeibei.shop.common.Constants;
import com.fengbeibei.shop.common.HttpClientHelper;
import com.fengbeibei.shop.common.HttpClientHelper.CallBack;
import com.fengbeibei.shop.common.SystemHelper;
import com.fengbeibei.shop.pulltorefresh.library.PullToRefreshBase;
import com.fengbeibei.shop.pulltorefresh.library.PullToRefreshBase.Mode;
import com.fengbeibei.shop.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.fengbeibei.shop.pulltorefresh.library.PullToRefreshScrollView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.ImageView.ScaleType;

public class HomeFragment extends Fragment{
	private PullToRefreshScrollView mPullToRefresh;
	private ScrollView mScrollView;
	private Button mScanBtn;
	private Button mMessageBtn;
	
	private ViewPager mAdViewPager;
	private ArrayList<ImageView> mAdData = new ArrayList<ImageView>();
	private LinearLayout mAdPoint;
	private ArrayList<ImageView> mAdPointData = new ArrayList<ImageView>();
	private Button mMenuCategoryBtn;
	private Button mMenuUcenterBtn;
	private Button mMenuOrderBtn;
	private Button mMenuCollectBtn;
	private LinearLayout mHomeData;
	
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
		mPullToRefresh.setOnRefreshListener(new OnRefreshListener<ScrollView>(){
			@Override
			public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
				// TODO Auto-generated method stub
				initData();
			}
			
		});
		mScrollView =mPullToRefresh.getRefreshableView();
		initView(homeLayout);
		initData();
		return homeLayout;
	}
	public void initView(View homeLayout){
		mAdViewPager = (ViewPager)homeLayout.findViewById(R.id.adViewPager);
		mAdPoint = (LinearLayout) homeLayout.findViewById(R.id.adPoint);
		
	}
	public void initData(){
		HttpClientHelper.asynGet(Constants.APP_URL, new CallBack(){

			@Override
			public void onFinish(Message response) {
				// TODO Auto-generated method stub
				mPullToRefresh.onRefreshComplete();//加载完成下拉控件取消显示
				if(response.what == HttpStatus.SC_OK){
					mHomeData.removeAllViews();
					try{
						String json = (String)response.obj;
						Log.d("json",json);
						JSONArray arr = new JSONArray(json);
						int size = null == arr ? 0 : arr.length();
						for(int i =0 ; i<size ; i++){
							JSONObject  obj = arr.getJSONObject(i);
							JSONObject jsonObj = new JSONObject(obj.toString());
							if(!jsonObj.has("home1")){
								
							}else if(!jsonObj.has("adv")){
								String advertJson = jsonObj.getString("adv_list");
								
								JSONObject itemObj = new JSONObject(advertJson);
								String item = itemObj.getString("item");
								if(!item.equals("[]")){
									ArrayList<AdList> adList =AdList.newInstance(item);
									if(adList.size() > 0 && adList != null){
										initHeadAd(adList);
									}
								}
							}
						}
						
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
	
	public void initHeadAd(ArrayList<AdList> adList){
		mAdViewPager.removeAllViews();
		mAdPoint.removeAllViews();
		mAdData.clear();
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
			
			//mAdPointData.add(mAdPoint);
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
	}
	
}
