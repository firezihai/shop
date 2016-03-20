package com.fengbeibei.shop.bean;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Category {
	public static class Attr{
		public static final String GC_ID = "gc_id";
		public static final String GC_NAME = "gc_name";
		public static final String GC_IMAGE = "image";
	}
	private String mGcId;
	private String mGcName;
	private String mGcImage;
	public Category(String gcId, String gcName, String gcImage) {
		super();
		mGcId = gcId;
		mGcName = gcName;
		mGcImage = gcImage;
	}
	public static ArrayList<Category> newIntance(String json){
		ArrayList<Category> categoryList = new ArrayList<Category>();
		try{
			JSONArray arr = new JSONArray(json);
			int size = arr == null  ? 0 : arr.length();
			for (int i = 0; i < size ; i++) {
				JSONObject obj = arr.getJSONObject(i);
				String gcId = obj.optString(Attr.GC_ID);
				String gcName = obj.optString(Attr.GC_NAME);
				String gcImage = obj.optString(Attr.GC_IMAGE);
				categoryList.add(new Category(gcId,gcName,gcImage));
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		return categoryList;
	}
	public String getGcId() {
		return mGcId;
	}
	public void setGcId(String gcId) {
		mGcId = gcId;
	}
	public String getGcName() {
		return mGcName;
	}
	public void setGcName(String gcName) {
		mGcName = gcName;
	}
	public String getGcImage() {
		return mGcImage;
	}
	public void setGcImage(String gcImage) {
		mGcImage = gcImage;
	}
	@Override
	public String toString() {
		return "Category [mGcId=" + mGcId + ", mGcName=" + mGcName
				+ ", mGcImage=" + mGcImage + "]";
	}
	
	
}
