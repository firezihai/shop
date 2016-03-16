package com.fengbeibei.shop.activity;

import android.support.v4.app.Fragment;

import com.fengbeibei.shop.ui.HomeFragment;

public class HomeActivity extends AppFragmentActivity{

	@Override
	protected Fragment createFragment(){
		return new HomeFragment();
	}

}
