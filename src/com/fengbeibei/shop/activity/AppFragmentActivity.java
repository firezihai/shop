package com.fengbeibei.shop.activity;

import com.fengbeibei.shop.R;
import com.fengbeibei.shop.ui.HomeFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public  abstract class AppFragmentActivity extends FragmentActivity{
	private FragmentManager mFragmentManager;
	protected abstract Fragment createtFragment();
	public int getLayoutResId(){
		return R.layout.activity_fragment;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResId());
		mFragmentManager = getSupportFragmentManager();
		Fragment fragment = mFragmentManager.findFragmentById(R.id.fragmentContainer);
		if(fragment == null){
			fragment = createtFragment();
			mFragmentManager.beginTransaction()
												.add(R.id.fragmentContainer, fragment)
												.commit();
		}
	}
	
	
}
