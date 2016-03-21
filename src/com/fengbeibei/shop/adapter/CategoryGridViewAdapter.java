package com.fengbeibei.shop.adapter;

import java.util.ArrayList;
import java.util.List;

import com.fengbeibei.shop.R;
import com.fengbeibei.shop.activity.SearchActivity;
import com.fengbeibei.shop.bean.Category;
import com.fengbeibei.shop.common.AnimateFirstDisplayListener;
import com.fengbeibei.shop.common.SystemHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryGridViewAdapter extends ArrayAdapter<Category>{
	private int mLayout;
	private Context mContext;
	private ImageLoader mImageLoader = ImageLoader.getInstance();
	private DisplayImageOptions mOptions = SystemHelper.getDisplayImageOptions();
	private ImageLoadingListener mAnimateFirstListener = new AnimateFirstDisplayListener();
	class ViewHolder{
		TextView gcName;
		ImageView gcImage;
	}
	public CategoryGridViewAdapter(Context context, int resource,
			List<Category> objects) {
		super(context, resource, objects);
		mContext = context;
		mLayout = resource;
		// TODO Auto-generated constructor stub
	}


	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Category category = getItem(position);
		ViewHolder holder;
		if ( convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(mLayout, null);
			holder = new ViewHolder();
			holder.gcName = (TextView)convertView.findViewById(R.id.gcName);
			holder.gcImage = (ImageView)convertView.findViewById(R.id.gcImage);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.gcName.setText(category.getGcName());
		mImageLoader.displayImage(category.getGcImage(), holder.gcImage, mOptions,  mAnimateFirstListener );
		setOnClickListener(convertView,category.getGcId());
		return convertView;
	}

	public void setOnClickListener(View view,final String data){
		view.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContext,SearchActivity.class);
				intent.putExtra("gc_id", data);
				mContext.startActivity(intent);
			}
			
		});
	}

}
