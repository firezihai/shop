package com.fengbeibei.shop.adapter;

import java.util.ArrayList;

import com.fengbeibei.shop.R;
import com.fengbeibei.shop.bean.Order;
import com.fengbeibei.shop.bean.Order.Goods;
import com.fengbeibei.shop.common.AnimateFirstDisplayListener;
import com.fengbeibei.shop.common.SystemHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrderListAdapter extends BaseAdapter{
	private ArrayList<Order> mOrderList;
	private Context mContext;
	private int mLayoutResourceId;
	private ImageLoader mImageLoader = ImageLoader.getInstance();
	private DisplayImageOptions mOptions = SystemHelper.getDisplayImageOptions();
	private ImageLoadingListener mAnimateFirstListener = new AnimateFirstDisplayListener();
	public OrderListAdapter(Context context,int layoutResourceId) {
		super();
		mContext = context;
		mLayoutResourceId = layoutResourceId;
	}
	public void setData(ArrayList<Order> data){
		mOrderList = data;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mOrderList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mOrderList.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Order order = mOrderList.get(position);
		ViewHolder holder;
		if (convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(mLayoutResourceId, null);
			holder = new ViewHolder();
			holder.goodsList = (LinearLayout)convertView.findViewById(R.id.goodsList);
			holder.storeName = (TextView)convertView.findViewById(R.id.storeName);
			holder.orderAmount = (TextView) convertView.findViewById(R.id.orderAmount);
			holder.stateDesc = (TextView) convertView.findViewById(R.id.orderState);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		String str = String.format("共有1件商品 合计￥ %s (含运费￥%s )", order.getOrderAmount(),order.getShippingFee());
		holder.storeName.setText(order.getStoreName());
		holder.orderAmount.setText(str);
		holder.stateDesc.setText(order.getStateDesc());

		ArrayList<Goods> goodsList = order.getGoods();
		int size = goodsList.size();
		holder.goodsList.removeAllViews();
		if (size > 0){
			for(Goods goods : goodsList){
				View goodsView = LayoutInflater.from(mContext).inflate(R.layout.order_goods_item, null);
				TextView goodsName = (TextView)goodsView.findViewById(R.id.goodsName);
				TextView goodsSpec = (TextView)goodsView.findViewById(R.id.goodsSpec);
				TextView goodsNum = (TextView)goodsView.findViewById(R.id.goodsNum);
				TextView goodsPayPrice = (TextView) goodsView.findViewById(R.id.goodsPayPrice);
				ImageView goodsImage = (ImageView) goodsView.findViewById(R.id.goodsImage);
				goodsName.setText(goods.getGoodsName());
				goodsSpec.setText(goods.getGoodsName());
				goodsPayPrice.setText(goods.getGoodsPayPrice());
				goodsNum.setText(goods.getGoodsNum());
				mImageLoader.displayImage(goods.getGoodsImage(), goodsImage,mOptions,mAnimateFirstListener);
				holder.goodsList.addView(goodsView);
			}
		}
		return convertView;
	}
	class ViewHolder{
		TextView storeName;
		LinearLayout goodsList;
		TextView orderAmount;
		TextView stateDesc;
	}
}
