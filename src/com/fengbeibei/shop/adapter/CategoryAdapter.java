package com.fengbeibei.shop.adapter;

import java.util.ArrayList;

import com.fengbeibei.shop.R;
import com.fengbeibei.shop.R.color;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter{
	private int selectedPosition = -1;
	private ArrayList<Category> mCategoryData;
	private LayoutInflater mInflater;
	private Context mContext;
	private ImageLoader mImageLoader = ImageLoader.getInstance();
	private DisplayImageOptions mOptions = SystemHelper.getDisplayImageOptions();
	private ImageLoadingListener mAnimateFirstListener = new AnimateFirstDisplayListener();
	class ViewHolder {
		TextView gcName;
		ImageView gcImage;
		LinearLayout gcItem;
	}
	
	public CategoryAdapter(Context context) {
		super();
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
	}
	
	public void setCategoryData(ArrayList<Category> categoryData){
		mCategoryData = categoryData;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCategoryData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mCategoryData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.parent_category_item, arg2,false);
			holder = new ViewHolder();
			holder.gcItem = (LinearLayout) convertView.findViewById(R.id.gcItem);
			holder.gcName = (TextView)convertView.findViewById(R.id.gcName);
			holder.gcImage = (ImageView)convertView.findViewById(R.id.gcImage);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		Category categoryData = mCategoryData.get(position);
		holder.gcName.setText(categoryData.getGcName());
		mImageLoader.displayImage(categoryData.getGcImage(),holder.gcImage,mOptions, mAnimateFirstListener);
	  if (selectedPosition == position) {  
			holder.gcName.setTextColor( mContext.getResources().getColor(R.color.orange));
        } else {   
        	holder.gcName.setTextColor( mContext.getResources().getColor(R.color.black));
        }  
		return convertView;
	}
	
	public void setSelectedPosition(int position) {  
        selectedPosition = position;  
    } 
	
	public int getSelectedPosition(){
		return  selectedPosition;
	}
}
