package com.fengbeibei.shop.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class Home2Data {
	public static class Attr{
		public static final String TITLE = "title";
		public static final String SQUARE_IMAGE = "square_image";
		public static final String SQUARE_TYPE = "square_type";
		public static final String SQUARE_DATA = "square_data";
		public static final String RECTANGLE1_IMAGE = "rectangle1_image";
		public static final String RECTANGLE1_TYPE = "rectangle1_type";
		public static final String RECTANGLE1_DATA = "rectangle1_data";
		public static final String RECTANGLE2_IMAGE = "rectangle2_image";
		public static final String RECTANGLE2_TYPE = "rectangle2_type";
		public static final String RECTANGLE2_DATA = "rectangle2_data";
	}
	private String mTitle;
	private String mSquareImage;
	private String mSquareType;
	private String mSquareData;
	private String mRectangle1Image;
	private String mRectangle1Type;
	private String mRectangle1Data;
	private String mRectangle2Image;
	private String mRectangle2Type;
	private String mRectangle2Data;
	public Home2Data() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param title
	 * @param squareImage
	 * @param squareType
	 * @param squareData
	 * @param rectangle1Image
	 * @param rectangle1Type
	 * @param rectangle1Data
	 * @param rectangle2Image
	 * @param rectangle2Type
	 * @param rectangle2Data
	 */
	public Home2Data(String title, String squareImage, String squareType,
			String squareData, String rectangle1Image, String rectangle1Type,
			String rectangle1Data, String rectangle2Image,
			String rectangle2Type, String rectangle2Data) {
		super();
		mTitle = title;
		mSquareImage = squareImage;
		mSquareType = squareType;
		mSquareData = squareData;
		mRectangle1Image = rectangle1Image;
		mRectangle1Type = rectangle1Type;
		mRectangle1Data = rectangle1Data;
		mRectangle2Image = rectangle2Image;
		mRectangle2Type = rectangle2Type;
		mRectangle2Data = rectangle2Data;
	}
	
	public static Home2Data newInstance(String json){
		Home2Data home2Data = null;
		try{
			JSONObject obj = new JSONObject(json);
			if(obj.length() > 0){
				String title =  obj.optString(Attr.TITLE);
				String squareImage = obj.optString(Attr.SQUARE_IMAGE);
				String squareType = obj.optString(Attr.SQUARE_TYPE);
				String squareData = obj.optString(Attr.SQUARE_DATA);
				String rectangle1Image = obj.optString(Attr.RECTANGLE1_IMAGE);
				String rectangle1Type = obj.optString(Attr.RECTANGLE1_TYPE);
				String rectangle1Data = obj.optString(Attr.RECTANGLE1_DATA);
				String rectangle2Image = obj.optString(Attr.RECTANGLE2_IMAGE);
				String rectangle2Type = obj.optString(Attr.RECTANGLE2_TYPE);
				String rectangle2Data= obj.optString(Attr.RECTANGLE2_DATA);
				home2Data = new Home2Data(title,squareImage,squareType,squareData,rectangle1Image,rectangle1Type,rectangle1Data,rectangle2Image,rectangle2Type,rectangle2Data);
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		return home2Data;
	}
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String title) {
		mTitle = title;
	}
	public String getSquareImage() {
		return mSquareImage;
	}
	public void setSquareImage(String squareImage) {
		mSquareImage = squareImage;
	}
	public String getSquareType() {
		return mSquareType;
	}
	public void setSquareType(String squareType) {
		mSquareType = squareType;
	}
	public String getSquareData() {
		return mSquareData;
	}
	public void setSquareData(String squareData) {
		mSquareData = squareData;
	}
	public String getRectangle1Image() {
		return mRectangle1Image;
	}
	public void setRectangle1Image(String rectangle1Image) {
		mRectangle1Image = rectangle1Image;
	}
	public String getRectangle1Type() {
		return mRectangle1Type;
	}
	public void setRectangle1Type(String rectangle1Type) {
		mRectangle1Type = rectangle1Type;
	}
	public String getRectangle1Data() {
		return mRectangle1Data;
	}
	public void setRectangle1Data(String rectangle1Data) {
		mRectangle1Data = rectangle1Data;
	}
	public String getRectangle2Image() {
		return mRectangle2Image;
	}
	public void setRectangle2Image(String rectangle2Image) {
		mRectangle2Image = rectangle2Image;
	}
	public String getRectangle2Type() {
		return mRectangle2Type;
	}
	public void setRectangle2Type(String rectangle2Type) {
		mRectangle2Type = rectangle2Type;
	}
	@Override
	public String toString() {
		return "Home2Data [mTitle=" + mTitle + ", mSquareImage=" + mSquareImage
				+ ", mSquareType=" + mSquareType + ", mSquareData="
				+ mSquareData + ", mRectangle1Image=" + mRectangle1Image
				+ ", mRectangle1Type=" + mRectangle1Type + ", mRectangle1Data="
				+ mRectangle1Data + ", mRectangle2Image=" + mRectangle2Image
				+ ", mRectangle2Type=" + mRectangle2Type + ", mRectangle2Data="
				+ mRectangle2Data + "]";
	}
	public String getRectangle2Data() {
		return mRectangle2Data;
	}
	public void setRectangle2Data(String rectangle2Data) {
		mRectangle2Data = rectangle2Data;
	}

	
}
