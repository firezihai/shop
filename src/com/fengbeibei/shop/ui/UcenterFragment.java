package com.fengbeibei.shop.ui;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import com.fengbeibei.shop.R;
import com.fengbeibei.shop.activity.LoginActivity;
import com.fengbeibei.shop.bean.User;
import com.fengbeibei.shop.common.CircleImageDrawable;
import com.fengbeibei.shop.common.Constants;
import com.fengbeibei.shop.common.HttpClientHelper;
import com.fengbeibei.shop.common.HttpClientHelper.CallBack;
import com.fengbeibei.shop.common.MyApplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class UcenterFragment extends Fragment{
	private User mUser;
	private MyApplication mApplication;
	private TextView mUsername;
	private TextView mFollowGoods;
	private TextView mFollowStore;
	private TextView mWaitPayCount;
	private TextView mWaitShipCount;
	private TextView mWaitReceiptCount;
	private TextView mWaitCommentCount;
	private TextView mAccountBalance;
	private TextView mPoint;
	private TextView mVoucher;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View ucenterLayout = inflater.inflate(R.layout.ucenter, container, false);
		 mApplication = MyApplication.getInstance();
		 initView(ucenterLayout);
		 String key = mApplication.getLoginKey();
		 Boolean isLogin = mApplication.isLogin();
		if(key != null && !"".equals(key) && !isLogin){
			load(key);
			initData();
		} else if(isLogin){
			initData();
		}

		return ucenterLayout;
	}
	
	public void initView(View v){
		mUsername = (TextView) v.findViewById(R.id.username);
		ImageView mImageView = (ImageView) v.findViewById(R.id.user_avatar);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_user_avatar); 
		mImageView.setImageDrawable(new CircleImageDrawable(bitmap));
		mFollowGoods = (TextView) v.findViewById(R.id.follow_goods);
		mFollowStore = (TextView) v.findViewById(R.id.follow_store);
		mWaitPayCount = (TextView) v.findViewById(R.id.wait_pay_count);
		mWaitShipCount = (TextView) v.findViewById(R.id.wait_ship_count);
		mWaitReceiptCount = (TextView) v.findViewById(R.id.wait_receipt_count);
		mWaitCommentCount = (TextView) v.findViewById(R.id.wait_comment_count);
		mAccountBalance = (TextView) v.findViewById(R.id.account_balance);
		mPoint = (TextView) v.findViewById(R.id.point);
		mVoucher = (TextView) v.findViewById(R.id.voucher);

	}
	
	public void initData(){
		mUser = mApplication.getUserInfo();
		mUsername.setText(mUser.getUserName());
		mWaitPayCount.setText(mUser.getOrderNopayCount());
		mWaitShipCount.setText(mUser.getOrderNoshipCount());
		mWaitReceiptCount.setText(mUser.getOrderNoreceiptCount());
		mWaitCommentCount.setText(mUser.getOrderNocommentCount());
		Log.d("d", mUser.getUserName());
	}
	public void load(String key){
		
		String url = Constants.MEMBER_INFO_URL + "&key="+key;
		HttpClientHelper.asynGet(url,new CallBack(){

			@Override
			public void onFinish(Message response) {
				// TODO Auto-generated method stub
				if (response.what == HttpStatus.SC_OK){
					String json = (String)response.obj;
					Log.d("post",json);
					mUser = User.newInstance(json);
					if (mUser != null){
						MyApplication.getInstance().setUserInfo(mUser);
					}
				}
			}

			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(mApplication.isLogin()){
			mUser = mApplication.getUserInfo();
			initData();
		}
		
	}
	
	

}
