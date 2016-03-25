package com.fengbeibei.shop.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	private String mUserId;
	private String mUserName;
	private String mUserAvatar;
	private String mPoint;
	private String mPredeposit;
	private String mVoucherCount;
	private String mAvailableRcBalance;
	private String mOrderNopayCount;
	private String mOrderNoreceiptCount;
	private String mOrderNoshipCount;
	private String mOrderNocommentCount;
	private String mFriendCount;
	private String mFavoritesCount;
	private String mInviteQrcode;
	public static class Attr{
		public static final String USER_ID = "user_id";
		public static final String USER_NAME = "user_name";
		public static final String USER_AVATAR = "avator";
		public static final String POINT = "point";
		public static final String PREDEPOSIT= "predepoit";
		public static final String VOUCHER_COUNT = "voucher_count";
		public static final String AVAILABLE_RC_BALANCE = "available_rc_balance";
		public static final String ORDER_NOPAY_COUNT = "order_nopay_count";
		public static final String ORDER_NORECEIPT_COUNT = "order_noreceipt_count";
		public static final String ORDER_NOSHIP_COUNT = "order_noship_count";
		public static final String ORDER_NOCOMMENT_COUNT = "order_nocomment_count";
		public static final String FRIEND_COUNT = "friend_count";
		public static final String FAVORITES_COUNT = "favorites_count";
		public static final String INVITE_QRCODE = "invite_qrcode";
	}
	
	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param userId
	 * @param userName
	 * @param userAvatar
	 * @param point
	 * @param predeposit
	 * @param voucherCount
	 * @param availableRcBalance
	 * @param orderNopayCount
	 * @param orderNoreceiptCount
	 * @param orderNocommentCount
	 * @param friendCount
	 * @param favoritesCount
	 * @param inviteQrcode
	 */
	public User(String userId, String userName, String userAvatar,
			String point, String predeposit, String voucherCount,
			String availableRcBalance, String orderNopayCount,String orderNoshipCount,
			String orderNoreceiptCount, String orderNocommentCount,
			String friendCount, String favoritesCount, String inviteQrcode) {
		super();
		mUserId = userId;
		mUserName = userName;
		mUserAvatar = userAvatar;
		mPoint = point;
		mPredeposit = predeposit;
		mVoucherCount = voucherCount;
		mAvailableRcBalance = availableRcBalance;
		mOrderNopayCount = orderNopayCount;
		mOrderNoshipCount = orderNoshipCount;
		mOrderNoreceiptCount = orderNoreceiptCount;
		mOrderNocommentCount = orderNocommentCount;
		mFriendCount = friendCount;
		mFavoritesCount = favoritesCount;
		mInviteQrcode = inviteQrcode;
	}

	public static User newInstance(String json){
		User user = null;
		try{
			JSONObject obj = new JSONObject(json);
			String infoJson = obj.getString("member_info");
			JSONObject infoObj = new JSONObject(infoJson);
			if(infoObj.length() > 0){
				String user_id = "1";
				String user_name = infoObj.getString(Attr.USER_NAME);
				String user_avatar = infoObj.getString(Attr.USER_AVATAR);
				String point = infoObj.getString(Attr.POINT);
				String predeposit = infoObj.getString(Attr.PREDEPOSIT);
				String voucherCount = infoObj.getString(Attr.VOUCHER_COUNT);
				String availableRcBalance = infoObj.getString(Attr.AVAILABLE_RC_BALANCE);
				String orderNopayCount = infoObj.getString(Attr.ORDER_NOPAY_COUNT);
				String orderNoshipCount = infoObj.getString(Attr.ORDER_NOSHIP_COUNT);
				String orderNoreceiptCount = infoObj.getString(Attr.ORDER_NORECEIPT_COUNT);
				String orderNocommentCount = infoObj.getString(Attr.ORDER_NOCOMMENT_COUNT);
				String friendCount = infoObj.getString(Attr.FRIEND_COUNT);
				String favoritesCount = infoObj.getString(Attr.FAVORITES_COUNT);
				String inviteQrcode = infoObj.getString(Attr.INVITE_QRCODE);
				user = new User(user_id,user_name,user_avatar,point,predeposit,voucherCount,availableRcBalance,orderNopayCount,orderNoshipCount,orderNoreceiptCount,orderNocommentCount,friendCount,favoritesCount,inviteQrcode);
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		return user;
	}

	public String getUserId() {
		return mUserId;
	}

	public void setUserId(String userId) {
		mUserId = userId;
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

	public String getPoint() {
		return mPoint;
	}

	public void setPoint(String point) {
		mPoint = point;
	}

	public String getPredeposit() {
		return mPredeposit;
	}

	public void setPredeposit(String predeposit) {
		mPredeposit = predeposit;
	}

	public String getVoucherCount() {
		return mVoucherCount;
	}

	public void setVoucherCount(String voucherCount) {
		mVoucherCount = voucherCount;
	}

	public String getAvailableRcBalance() {
		return mAvailableRcBalance;
	}

	public void setAvailableRcBalance(String availableRcBalance) {
		mAvailableRcBalance = availableRcBalance;
	}

	public String getOrderNopayCount() {
		return mOrderNopayCount;
	}

	public void setOrderNopayCount(String orderNopayCount) {
		mOrderNopayCount = orderNopayCount;
	}
	
	public String getOrderNoshipCount() {
		return mOrderNoshipCount;
	}

	public void setOrderNoshipCount(String orderNoshipCount) {
		mOrderNoshipCount = orderNoshipCount;
	}

	public String getOrderNoreceiptCount() {
		return mOrderNoreceiptCount;
	}

	public void setOrderNoreceiptCount(String orderNoreceiptCount) {
		mOrderNoreceiptCount = orderNoreceiptCount;
	}

	public String getOrderNocommentCount() {
		return mOrderNocommentCount;
	}

	public void setOrderNocommentCount(String orderNocommentCount) {
		mOrderNocommentCount = orderNocommentCount;
	}

	public String getFriendCount() {
		return mFriendCount;
	}

	public void setFriendCount(String friendCount) {
		mFriendCount = friendCount;
	}

	public String getFavoritesCount() {
		return mFavoritesCount;
	}

	public void setFavoritesCount(String favoritesCount) {
		mFavoritesCount = favoritesCount;
	}

	public String getInviteQrcode() {
		return mInviteQrcode;
	}

	public void setInviteQrcode(String inviteQrcode) {
		mInviteQrcode = inviteQrcode;
	}

	@Override
	public String toString() {
		return "User [mUserId=" + mUserId + ", mUserName=" + mUserName
				+ ", mUserAvatar=" + mUserAvatar + ", mPoint=" + mPoint
				+ ", mPredeposit=" + mPredeposit + ", mVoucherCount="
				+ mVoucherCount + ", mAvailableRcBalance="
				+ mAvailableRcBalance + ", mOrderNopayCount="
				+ mOrderNopayCount + ", mOrderNoreceiptCount="
				+ mOrderNoreceiptCount + ", mOrderNocommentCount="
				+ mOrderNocommentCount + ", mFriendCount=" + mFriendCount
				+ ", mFavoritesCount=" + mFavoritesCount + ", mInviteQrcode="
				+ mInviteQrcode + "]";
	}
	
	
	
	
	
}
