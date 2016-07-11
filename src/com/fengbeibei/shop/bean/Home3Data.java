package com.fengbeibei.shop.bean;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Home3Data {
	private static final class Attr{
		public static final String ITEM = "item";
		public static final String TITLE = "title";
	}
	
	private static final class ItemAttr{
		public static final String IMAGE = "image";
		public static final String TYPE = "type";
		public static final String DATA = "data";
	}
	
	private String mItem;
	private String mTitle;
	private String mImage;
	private String mType;
	private String mData;
	
	public Home3Data() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param item
	 * @param title
	 */
	public Home3Data(String item, String title) {
		super();
		mItem = item;
		mTitle = title;
	}

	/**
	 * @param image
	 * @param type
	 * @param data
	 */
	public Home3Data(String image, String type, String data) {
		super();
		mImage = image;
		mType = type;
		mData = data;
	}
	/**
	 * 获取项目
	 * @param json
	 * @return
	 */
	public static Home3Data newInstance(String json){
		Home3Data home3Data = null;
		try{
			JSONObject obj = new JSONObject(json);
			if(obj.length() > 0){
				String item = obj.optString(Attr.ITEM);
				String title   = obj.optString(Attr.TITLE);
				home3Data = new Home3Data(item,title);
			}
		} catch (JSONException e ){
			e.printStackTrace();
		}
		return home3Data;
	}
	
	public static ArrayList<Home3Data> newInstanceList(String itemJson){
		ArrayList<Home3Data> home3DataList = new ArrayList<Home3Data>();
		try{
			JSONArray arr = new JSONArray(itemJson);
			int size = arr == null ? 0 : arr.length();
			for (int i =0 ; i < size ; i++){
				JSONObject obj = arr.getJSONObject(i);
				String image = obj.optString(ItemAttr.IMAGE);
				String type = obj.optString(ItemAttr.TYPE);
				String data = obj.optString(ItemAttr.DATA);
				home3DataList.add(new Home3Data(image,type,data));
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		return home3DataList;
	}

	public String getItem() {
		return mItem;
	}

	public void setItem(String item) {
		mItem = item;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public String getImage() {
		return mImage;
	}

	public void setImage(String image) {
		mImage = image;
	}

	public String getType() {
		return mType;
	}

	public void setType(String type) {
		mType = type;
	}

	public String getData() {
		return mData;
	}

	public void setData(String data) {
		mData = data;
	}

	@Override
	public String toString() {
		return "Home3Data [mItem=" + mItem + ", mTitle=" + mTitle + ", mImage="
				+ mImage + ", mType=" + mType + ", mData=" + mData + "]";
	}
	
	
}
