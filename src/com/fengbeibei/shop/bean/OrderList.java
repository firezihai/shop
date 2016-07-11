package com.fengbeibei.shop.bean;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderList {
	private static final class Attr{
		public static final String PAY_SN = "pay_sn";
		public static final String ORDER_LIST = "order_list";
		public static final String ADD_TIME = "add_time";
	}
	private String mPaySn;
	private ArrayList<Order> mOrderList;
	private String mAddTime;
	
	public OrderList(String paySn, ArrayList<Order> orderList, String addTime) {
		super();
		mPaySn = paySn;
		mOrderList = orderList;
		mAddTime = addTime;
	}
	public static ArrayList<OrderList> newInstance(String json){
		ArrayList<OrderList> orderList = new ArrayList<OrderList>();
		try{
			JSONArray arr = new JSONArray(json);
			int leng = arr.length();
			for (int i = 0 ; i < leng ; i++) {
				JSONObject obj = arr.getJSONObject(i);
			//	String paySn = obj.getString(Attr.PAY_SN);
				ArrayList<Order> order = Order.newInstance(obj.getString(Attr.ORDER_LIST));
		//		String addTime = obj.getString(Attr.ADD_TIME);
			//	orderList.add(new OrderList(paySn,order,addTime));
			}

		} catch (JSONException e){
			e.printStackTrace();
		}
		return orderList;
	}
	public String getPaySn() {
		return mPaySn;
	}
	public void setPaySn(String paySn) {
		mPaySn = paySn;
	}
	public  ArrayList<Order> getOrderList() {
		return mOrderList;
	}
	public void setOrderList( ArrayList<Order> orderList) {
		mOrderList = orderList;
	}
	public String getAddTime() {
		return mAddTime;
	}
	public void setAddTime(String addTime) {
		mAddTime = addTime;
	}
	@Override
	public String toString() {
		return "orderList [mPaySn=" + mPaySn + ", mOrderList=" + mOrderList
				+ ", mAddTime=" + mAddTime + "]";
	}
	
}
