package com.fengbeibei.shop.adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AdViewPagerAdapter extends PagerAdapter {
	private ArrayList<ImageView> mAdData;
	
	public AdViewPagerAdapter(ArrayList<ImageView> views){
		this.mAdData = views;
	}
	
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager) container).removeView(mAdData.get(position));
	}


	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getItemPosition(object);
	}


	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		((ViewPager) container).addView(mAdData.get(position));
		return mAdData.get(position);
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mAdData.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return (arg0 == arg1);
	}

}
