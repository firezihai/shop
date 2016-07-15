package com.fengbeibei.shop.adapter;

import java.util.ArrayList;

import com.fengbeibei.shop.R;
import com.fengbeibei.shop.adapter.Home3GridViewAdapter.ViewHolder;
import com.fengbeibei.shop.bean.HomeGoods;
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
import android.widget.TextView;

public class HomeGoodsGridViewAdapter extends BaseAdapter{
	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<HomeGoods>  mHomeGoods;
	private ImageLoader mImageLoader = ImageLoader.getInstance();
	private DisplayImageOptions mOptions = SystemHelper.getDisplayImageOptions();
	private ImageLoadingListener mAnimateFirstListener = new AnimateFirstDisplayListener();
	/*控件集合*/
	static class ViewHolder{
		TextView goodsName;
		TextView goodsPrice;
		ImageView goodsImage;
		
	}
	
	public HomeGoodsGridViewAdapter(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
		mInflater  = LayoutInflater.from(mContext);
	}
	public void setHomeGoods(ArrayList<HomeGoods> homeGoods){
		mHomeGoods = homeGoods;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mHomeGoods.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mHomeGoods.get(position);
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
		if (convertView != null ){
			convertView = mInflater.inflate(R.layout.home_goods_grid_item, parent);
			holder = new ViewHolder();
			holder.goodsName = (TextView) convertView.findViewById(R.id.goodsName);
			holder.goodsPrice = (TextView) convertView.findViewById(R.id.goodsPrice);
			holder.goodsName = (TextView) convertView.findViewById(R.id.goodsImage);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		HomeGoods homeGoods = mHomeGoods.get(position);
		holder.goodsName.setText(homeGoods.getGoodsName());
		holder.goodsPrice.setText(homeGoods.getGoodsPromotionPrice());
		mImageLoader.displayImage(homeGoods.getGoodsImage(), holder.goodsImage, mOptions, mAnimateFirstListener);
		setOnClick(convertView,homeGoods.getGoodsId());
		return convertView;
	}
	private void setOnClick(View view,final String goods_id){
		view.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				IntentHelper.goodsDetail(mContext, goods_id);
			}
			
		});
	}
}
