package com.fengbeibei.shop.bean;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdList {
	public static class Attr{
		public static final String IMAGE = "image";
		public static final String TYPE ="type";
		public static final String DATA = "data";
	}
	private String mImage;
	private String mType;
	private String mData;
	public AdList(String image, String type, String data) {
		super();
		mImage = image;
		mType = type;
		mData = data;
	}
	
	public static ArrayList<AdList> newInstance(String jsonData){
		ArrayList<AdList> adData = new ArrayList<AdList>();
		try{
			JSONArray arr = new JSONArray(jsonData);
			int size = null == arr ? 0 : arr.length();
			for(int i =0 ; i < size ; i++){
				JSONObject obj = arr.getJSONObject(i);
				String image = obj.optString(Attr.IMAGE);
				String type = obj.optString(Attr.TYPE);
				String data = obj.optString(Attr.DATA);
				adData.add(new AdList(image,type,data));
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		return adData;
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
		return "AdList [mImage=" + mImage + ", mType=" + mType + ", mData="
				+ mData + "]";
	}
	
	
	
}
