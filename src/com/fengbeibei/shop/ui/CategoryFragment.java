package com.fengbeibei.shop.ui;

import com.fengbeibei.shop.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CategoryFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View categoryLayout = inflater.inflate(R.layout.category,container, false);
		
		return categoryLayout;
	}


}
