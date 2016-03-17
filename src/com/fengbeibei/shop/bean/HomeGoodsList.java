package com.fengbeibei.shop.bean;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeGoodsList {
	private static final class Attr{
		//public static final String GOODS_COMMONID = "goods_commonid";
		public static final String GOODS_ID  = "goods_id";
		public static final String GOODS_NAME  = "goods_name";
		public static final String GOODS_IMAGE  = "goods_image";
		public static final String GOODS_MARKETPRICE  = "goods_marketprice";
		public static final String GOODS_PRICE  = "goods_price";
	}
	private String mGoodsId;
	private String mGoodsName;
	private String mGoodsImage;
	private String mGoodsMarketPrice;
	private String mGoodsPrice;
	
	
	public HomeGoodsList() {
		super();
	}

	/**
	 * 
	 * @param goodsId
	 * @param goodsName
	 * @param goodsImage
	 * @param goodsMarketPrice
	 * @param goodsPrice
	 */
	public HomeGoodsList(String goodsId, String goodsName, String goodsImage,
			String goodsMarketPrice, String goodsPrice) {
		super();
		mGoodsId = goodsId;
		mGoodsName = goodsName;
		mGoodsImage = goodsImage;
		mGoodsMarketPrice = goodsMarketPrice;
		mGoodsPrice = goodsPrice;
	}
	
	public static  ArrayList<HomeGoodsList> newInstance(String json){
		ArrayList<HomeGoodsList> homeGoodsList = new ArrayList<HomeGoodsList>();
		try{
			JSONArray arr = new JSONArray(json);
			int size = arr == null ? 0 : arr.length();
			for ( int i = 0 ; i < size ; i++){
				JSONObject obj = arr.getJSONObject(i);
				String goodsId = obj.optString(Attr.GOODS_ID);
				String goodsName = obj.optString(Attr.GOODS_NAME);
				String goodsImage = obj.optString(Attr.GOODS_IMAGE);
				String goodsMarketPrice = obj.optString(Attr.GOODS_MARKETPRICE);
				String goodsPrice = obj.optString(Attr.GOODS_PRICE);
				homeGoodsList.add(new HomeGoodsList(goodsId,goodsName,goodsImage,goodsMarketPrice,goodsPrice));
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		return homeGoodsList;
	}

	public String getGoodsId() {
		return mGoodsId;
	}

	public void setGoodsId(String goodsId) {
		mGoodsId = goodsId;
	}

	public String getGoodsName() {
		return mGoodsName;
	}

	public void setGoodsName(String goodsName) {
		mGoodsName = goodsName;
	}

	public String getGoodsImage() {
		return mGoodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		mGoodsImage = goodsImage;
	}

	public String getGoodsMarketPrice() {
		return mGoodsMarketPrice;
	}

	public void setGoodsMarketPrice(String goodsMarketPrice) {
		mGoodsMarketPrice = goodsMarketPrice;
	}

	public String getGoodsPrice() {
		return mGoodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		mGoodsPrice = goodsPrice;
	}
	
	
}
