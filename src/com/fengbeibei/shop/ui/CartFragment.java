package com.fengbeibei.shop.ui;

import com.fengbeibei.shop.R;

import android.os.Bundle;
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

	

}
