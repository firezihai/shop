package com.fengbeibei.shop.adapter;

import java.util.ArrayList;

import com.fengbeibei.shop.R;
import com.fengbeibei.shop.bean.Home3Data;
import com.fengbeibei.shop.common.AnimateFirstDisplayListener;
import com.fengbeibei.shop.common.IntentHelper;
import com.fengbeibei.shop.common.SystemHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class Home3GridViewAdapter extends BaseAdapter{
	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<Home3Data> mHome3Data;
	private ImageLoader mImageLoader = ImageLoader.getInstance();
	private DisplayImageOptions mOptions = SystemHelper.getDisplayImageOptions();
	private ImageLoadingListener mAnimateFirstListener = new AnimateFirstDisplayListener();
	public Home3GridViewAdapter(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
		mInflater   = LayoutInflater.from(mContext);
	}
	public void setHome3Data(ArrayList<Home3Data> home3Data){
		mHome3Data = home3Data;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mHome3Data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mHome3Data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null){
			convertView = mInflater.inflate(R.layout.home_item3_grid_item, null);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView.findViewById(R.id.home3Image);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder)convertView.getTag();
		}
		Home3Data home3Data= mHome3Data.get(position);
		mImageLoader.displayImage(home3Data.getImage(), holder.image, mOptions, mAnimateFirstListener);
		setOnClick(convertView,home3Data.getType(),home3Data.getData());
		return convertView;
	}
	
	class ViewHolder{
		ImageView image;
	}
	private void setOnClick(View view,final String type,final String data){
		view.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				IntentHelper.filter(mContext, type, data);
			}
			
		});
	}
}
