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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CartFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View cartLayout = inflater.inflate(R.layout.cart, container, false);
		return cartLayout;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
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
	

}
