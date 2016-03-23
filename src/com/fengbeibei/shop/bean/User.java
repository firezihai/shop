package com.fengbeibei.shop.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	private String mUserId;
	private String mUserName;
	private String mUserAvatar;
	public static class Attr{
		public static final String USER_ID = "user_id";
		public static final String USER_NAME = "user_name";
		public static final String USER_AVATAR = "user_avatar";
	}
	public User(String userId, String userName, String userAvatar) {
		super();
		mUserId = userId;
		mUserName = userName;
		mUserAvatar = userAvatar;
	}
	public User newInstance(String json){
		User user = null;
		try{
			JSONObject obj = new JSONObject(json);
			if(obj.length() > 0){
				String user_id = obj.getString(Attr.USER_ID);
				String user_name = obj.getString(Attr.USER_NAME);
				String user_avatar = obj.getString(Attr.USER_AVATAR);
				user = new User(user_id,user_name,user_avatar);
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		return user;
	}
	public String getUserIid() {
		return mUserId;
	}
	public void setUserIid(String userIid) {
		mUserId = userIid;
	}
	public String getUserName() {
		return mUserName;
	}
	public void setUserName(String userName) {
		mUserName = userName;
	}
	public String getUserAvatar() {
		return mUserAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		mUserAvatar = userAvatar;
	}
	@Override
	public String toString() {
		return "User [mUserIid=" + mUserId + ", mUserName=" + mUserName
				+ ", mUserAvatar=" + mUserAvatar + "]";
	}
	
	
	
}
