package com.fengbeibei.shop.ui;

import com.fengbeibei.shop.R;
import com.fengbeibei.shop.activity.LoginActivity;
import com.fengbeibei.shop.common.Constants;
import com.fengbeibei.shop.common.HttpClientHelper;
import com.fengbeibei.shop.common.MyApplication;
import com.fengbeibei.shop.common.HttpClientHelper.CallBack;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CartFragment extends Fragment{
	private MyApplication mApplication;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View cartLayout = inflater.inflate(R.layout.cart, container, false);
		 mApplication = MyApplication.getInstance();
		return cartLayout;
	}
	


	public void initData(){
		HttpClientHelper.asynGet(Constants.CART_LIST_URL, new CallBack(){

			@Override
			public void onFinish(Message response) {
				// TODO Auto-generated method stub
				
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
		Log.d("ucenterFragment", "onResume");
		if(mApplication.isLogin()){

			initData();
		}
		
	}
}
