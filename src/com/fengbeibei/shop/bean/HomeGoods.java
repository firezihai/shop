package com.fengbeibei.shop.bean;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeGoods {
	public static class Attr{
		public static final String GOODS_ID = "goods_id";
		public static final String GOODS_NAME = "goods_name";
		public static final String GOODS_PROMOTION_PRICE = "goods_promotion_price";
		public static final String GOODS_IMAGE = "goods_image";
	}
	private String mGoodsId;
	private String mGoodsName;
	private String mGoodsPromotionPrice;
	private String mGoodsImage;
	/**
	 * @param goodsId
	 * @param goodsName
	 * @param goodsPromotionPrice
	 * @param goodsImage
	 */
	public HomeGoods(String goodsId, String goodsName,
			String goodsPromotionPrice, String goodsImage) {
		super();
		mGoodsId = goodsId;
		mGoodsName = goodsName;
		mGoodsPromotionPrice = goodsPromotionPrice;
		mGoodsImage = goodsImage;
	}
	
	public static ArrayList<HomeGoods> newInstance(String json){
		ArrayList<HomeGoods> homeGoods = new ArrayList<HomeGoods>();
		try{
			JSONArray arr = new JSONArray(json);
			int size = arr == null ? 0 : arr.length();
			for ( int i = 0 ; i < size ; i++){
				JSONObject obj = arr.getJSONObject(i);
				String goodsId  = obj.getString(Attr.GOODS_ID);
				String goodsName = obj.getString(Attr.GOODS_NAME);
				String goodsPromotionPrice = obj.getString(Attr.GOODS_PROMOTION_PRICE);
				String goodsImage = obj.getString(Attr.GOODS_IMAGE);
				homeGoods.add(new HomeGoods(goodsId,goodsName,goodsPromotionPrice,goodsImage));
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		return homeGoods;
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

	public String getGoodsPromotionPrice() {
		return mGoodsPromotionPrice;
	}

	public void setGoodsPromotionPrice(String goodsPromotionPrice) {
		mGoodsPromotionPrice = goodsPromotionPrice;
	}

	public String getGoodsImage() {
		return mGoodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		mGoodsImage = goodsImage;
	}

	@Override
	public String toString() {
		return "HomeGoods [mGoodsId=" + mGoodsId + ", mGoodsName=" + mGoodsName
				+ ", mGoodsPromotionPrice=" + mGoodsPromotionPrice
				+ ", mGoodsImage=" + mGoodsImage + "]";
	}
	
	
}
