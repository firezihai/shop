package com.fengbeibei.shop.activity;

import java.util.ArrayList;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fengbeibei.shop.R;
import com.fengbeibei.shop.adapter.OrderListAdapter;
import com.fengbeibei.shop.bean.Order;
import com.fengbeibei.shop.bean.OrderList;
import com.fengbeibei.shop.common.Constants;
import com.fengbeibei.shop.common.HttpClientHelper;
import com.fengbeibei.shop.common.HttpClientHelper.CallBack;
import com.fengbeibei.shop.common.MyApplication;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

public class OrderListActivity extends Activity{
	private ListView mOrderListView;
	private Dialog mDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_list);
		mOrderListView = (ListView) findViewById(R.id.orderListView);
		mDialog = MyApplication.createLoadingDialog(OrderListActivity.this,"正在加载中");
		mDialog.show();
		loadData();
	}
	
	public void loadData(){
		String key = MyApplication.getInstance().getLoginKey();
		String url = Constants.ORDER_LIST_URL + "&key="+key;
		
		HttpClientHelper.asynGet(url ,new CallBack(){

			@Override
			public void onFinish(Message response) {
				// TODO Auto-generated method stub
				ArrayList<Order> orderList  = new ArrayList<Order>();
				mDialog.hide();
				if (response.what == HttpStatus.SC_OK){
					String json = (String)response.obj;
					try{
						JSONObject objJson = new JSONObject(json);
						JSONArray arr = new JSONArray(objJson.getString("order_group_list"));
						int length = arr.length();
						for (int i =0 ; i<length;i ++) {
							JSONObject obj = arr.getJSONObject(i);
							ArrayList<Order> order = Order.newInstance(obj.getString("order_list"));
							orderList.addAll(order);
							
							
						}
						OrderListAdapter orderListAdapter = new OrderListAdapter(OrderListActivity.this,R.layout.order_list_item);
						orderListAdapter.setData(orderList);
						mOrderListView.setAdapter(orderListAdapter);
					} catch (JSONException e){
						e.printStackTrace();
					}
				//	ArrayList<OrderList> orderListData = OrderList.newInstance(json);
					//OrderListAdapter orderListAdapter = new OrderListAdapter(OrderListActivity.this,R.layout.order_list_item);
				//	orderListAdapter.setData(orderListData);
				//	mOrderListView.setAdapter(orderListAdapter);
				}
			}

			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
}
