package com.fengbeibei.shop.bean;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;



public class Order {
	public String orderId;
	public String orderSn;
	public String storeName;
	public String orderState;
	public String stateDesc;
	public String goodsAmount;
	public String ifCancel;
	public String ifDeliver;
	public String ifLock;
	public String ifReceipt;
	public String orderAmount;
	public String paymentName;
	public String shippingFee;
	public ArrayList<Goods> goods;

	public Order(String orderId, String orderSn, String storeName,
			String orderState, String stateDesc, String goodsAmount,
			String ifCancel, String ifDeliver, String ifLock, String ifReceipt,
			String orderAmount, String paymentName, String shippingFee,
			ArrayList<Goods> goods) {
		super();
		this.orderId = orderId;
		this.orderSn = orderSn;
		this.storeName = storeName;
		this.orderState = orderState;
		this.stateDesc = stateDesc;
		this.goodsAmount = goodsAmount;
		this.ifCancel = ifCancel;
		this.ifDeliver = ifDeliver;
		this.ifLock = ifLock;
		this.ifReceipt = ifReceipt;
		this.orderAmount = orderAmount;
		this.paymentName = paymentName;
		this.shippingFee = shippingFee;
		this.goods = goods;
	}
	public static ArrayList<Order> newInstance(String json){
		ArrayList<Order> orderList = new ArrayList<Order>();
		try{
			JSONArray arr = new JSONArray(json);
			int size = arr.length();
			for(int i=0; i < size ; i++){
				JSONObject obj = arr.getJSONObject(i);
				String orderId = obj.getString(Attr.ORDER_ID);
				String orderSn = obj.getString(Attr.ORDER_SN);
				String storeName = obj.getString(Attr.STORE_NAME);
				String orderState = obj.getString(Attr.ORDER_STATE);
				String stateDesc = obj.getString(Attr.STATE_DESC);
				String goodsAmount = obj.getString(Attr.GOODS_AMOUNT);
				String ifCancel = obj.getString(Attr.IF_CANCEL);
				String ifDeliver = obj.getString(Attr.IF_DELIVER);
				String ifLock = obj.getString(Attr.IF_LOCK);
				String ifReceipt = obj.getString(Attr.IF_RECEIPT);
				String orderAmount = obj.getString(Attr.ORDER_AMOUNT);
				String paymentName = obj.getString(Attr.PAYMENT_NAME);
				String shippingFee = obj.getString(Attr.SHIPPING_FEE);
				ArrayList<Goods> goodsList = Goods.newIntance(obj.getString(Attr.EXTEND_ORDER_GOODS));
				orderList.add(new Order(orderId,orderSn,storeName,orderState,stateDesc,goodsAmount,ifCancel,ifDeliver,ifLock,ifReceipt,orderAmount,paymentName,shippingFee,goodsList));
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		return orderList;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getStateDesc() {
		return stateDesc;
	}
	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}
	public String getGoodsAmount() {
		return goodsAmount;
	}
	public void setGoodsAmount(String goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	public String getIfCancel() {
		return ifCancel;
	}
	public void setIfCancel(String ifCancel) {
		this.ifCancel = ifCancel;
	}
	public String getIfDeliver() {
		return ifDeliver;
	}
	public void setIfDeliver(String ifDeliver) {
		this.ifDeliver = ifDeliver;
	}
	public String getIfLock() {
		return ifLock;
	}
	public void setIfLock(String ifLock) {
		this.ifLock = ifLock;
	}
	public String getIfReceipt() {
		return ifReceipt;
	}
	public void setIfReceipt(String ifReceipt) {
		this.ifReceipt = ifReceipt;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	public String getShippingFee() {
		return shippingFee;
	}
	public void setShippingFee(String shippingFree) {
		this.shippingFee = shippingFree;
	}
	public ArrayList<Goods> getGoods() {
		return goods;
	}
	public void setGoods(ArrayList<Goods> goods) {
		this.goods = goods;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderSn=" + orderSn
				+ ", storeName=" + storeName + ", orderState=" + orderState
				+ ", stateDesc=" + stateDesc + ", goodsAmount=" + goodsAmount
				+ ", ifCancel=" + ifCancel + ", ifDeliver=" + ifDeliver
				+ ", ifLock=" + ifLock + ", ifReceipt=" + ifReceipt
				+ ", orderAmount=" + orderAmount + ", paymentName="
				+ paymentName + ", shippingFree=" + shippingFee + "]";
	}
	
	private static final class Attr{
		public static final String ORDER_ID = "order_id";
		public static final String ORDER_SN = "order_sn";
		public static final String STORE_NAME = "store_name";
		public static final String ORDER_STATE = "order_state";
		public static final String STATE_DESC = "state_desc";
		public static final String GOODS_AMOUNT = "goods_amount";
		public static final String IF_CANCEL = "if_cancel";
		public static final String IF_DELIVER = "if_deliver";
		public static final String IF_LOCK = "if_lock";
		public static final String IF_RECEIPT = "if_receive";
		public static final String ORDER_AMOUNT = "order_amount";
		public static final String PAYMENT_NAME = "payment_name";
		public static final String SHIPPING_FEE = "shipping_fee";
		public static final String EXTEND_ORDER_GOODS  = "extend_order_goods";
	}
	public static class Goods{
		public String goodsId;
		public String goodsName;
		public String goodsImage;
		public String goodsNum;
		public String goodsPayPrice;
		public String goodsPrice;
		
		
		public Goods(String goodsId, String goodsName, String goodsImage,
				String goodsNum, String goodsPayPrice, String goodsPrice) {
			super();
			this.goodsId = goodsId;
			this.goodsName = goodsName;
			this.goodsImage = goodsImage;
			this.goodsNum = goodsNum;
			this.goodsPayPrice = goodsPayPrice;
			this.goodsPrice = goodsPrice;
		}
		
		public static ArrayList<Goods> newIntance(String json){
			ArrayList<Goods> goodsList = new ArrayList<Goods>();
			try{
				JSONArray arr = new JSONArray(json);
				int length = arr.length();
				for(int i=0; i< length ; i++){
					JSONObject obj = arr.getJSONObject(i);
					String goodsId = obj.getString("goods_id");
					String goodsName = obj.getString("goods_name");
					String goodsImage = obj.getString("goods_image_url");
					String goodsNum = obj.getString("goods_num");
					String goodsPayPrice = obj.getString("goods_pay_price");
					String goodsPrice = obj.getString("goods_price");
					goodsList.add(new Goods(goodsId,goodsName,goodsImage,goodsNum,goodsPayPrice,goodsPrice));
				}
			} catch (JSONException e){
				e.printStackTrace();
			}
			return goodsList;
		}
		public String getGoodsId() {
			return goodsId;
		}
		public void setGoodsId(String goodsId) {
			this.goodsId = goodsId;
		}
		public String getGoodsName() {
			return goodsName;
		}
		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}
		public String getGoodsImage() {
			return goodsImage;
		}
		public void setGoodsImage(String goodsImage) {
			this.goodsImage = goodsImage;
		}
		public String getGoodsNum() {
			return goodsNum;
		}
		public void setGoodsNum(String goodsNum) {
			this.goodsNum = goodsNum;
		}
		public String getGoodsPayPrice() {
			return goodsPayPrice;
		}
		public void setGoodsPayPrice(String goodsPayPrice) {
			this.goodsPayPrice = goodsPayPrice;
		}
		public String getGoodsPrice() {
			return goodsPrice;
		}
		public void setGoodsPrice(String goodsPrice) {
			this.goodsPrice = goodsPrice;
		}
		@Override
		public String toString() {
			return "Goods [goodsId=" + goodsId + ", goodsName=" + goodsName
					+ ", goodsImage=" + goodsImage + ", goodsNum=" + goodsNum
					+ ", goodsPayPrice=" + goodsPayPrice + ", goodsPrice="
					+ goodsPrice + "]";
		}
		
	}
}
