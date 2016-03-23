package com.fengbeibei.shop.activity;

import com.fengbeibei.shop.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity{
	private EditText mAccountEdit;
	private EditText mPasswordEdit;
	private Button mSubmitBtn;
	private Button mCancelLoginBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		mAccountEdit = (EditText)findViewById(R.id.account_edit);
		mPasswordEdit =(EditText)findViewById(R.id.password_edit);
		mSubmitBtn = (Button)findViewById(R.id.submit_btn);
		mCancelLoginBtn = (Button) findViewById(R.id.cancel_login);
		mPasswordEdit.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(s.length() > 6){
					mSubmitBtn.setBackgroundColor(getResources().getColor(R.color.orange));
				}else{
					mSubmitBtn.setBackgroundColor(getResources().getColor(R.color.darkgrey));
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	

}
