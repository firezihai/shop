package com.fengbeibei.shop.ui;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import com.fengbeibei.shop.R;
import com.fengbeibei.shop.bean.User;
import com.fengbeibei.shop.common.Constants;
import com.fengbeibei.shop.common.HttpClientHelper;
import com.fengbeibei.shop.common.HttpClientHelper.CallBack;
import com.fengbeibei.shop.common.MyApplication;

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


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View ucenterLayout = inflater.inflate(R.layout.ucenter, container, false);
		MyApplication myApplication = MyApplication.getInstance();
		if(myApplication.isLogin()){
			mUser = myApplication.getUserInfo();
		}else{	
			initData();
		}
		
		return ucenterLayout;
	}
	
	public void initView(View v){
		TextView username = (TextView) v.findViewById(R.id.username);
		TextView followGoods = (TextView) v.findViewById(R.id.follow_goods);
		TextView followStore = (TextView) v.findViewById(R.id.follow_store);
		TextView waitPayCount = (TextView) v.findViewById(R.id.wait_pay_count);
		TextView waitShipCount = (TextView) v.findViewById(R.id.wait_ship_count);
		TextView waitReceiptCount = (TextView) v.findViewById(R.id.wait_receipt_count);
		TextView waitCommentCount = (TextView) v.findViewById(R.id.wait_comment_count);
		TextView accountBalance = (TextView) v.findViewById(R.id.account_balance);
		TextView point = (TextView) v.findViewById(R.id.point);
		TextView voucher = (TextView) v.findViewById(R.id.voucher);
		username.setText(mUser.getUserName());
		waitPayCount.setText(mUser.getOrderNopayCount());
		waitShipCount.setText(mUser.getOrderNoshipCount());
		waitReceiptCount.setText(mUser.getOrderNoreceiptCount());
		waitCommentCount.setText(mUser.getOrderNocommentCount());
	}
	public void initData(){
		HttpClientHelper.asynGet(Constants.MEMBER_INFO_URL,new CallBack(){

			@Override
			public void onFinish(Message response) {
				// TODO Auto-generated method stub
				if (response.what == HttpStatus.SC_OK){
					String json = (String)response.obj;

					
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
	

}
