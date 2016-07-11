package com.fengbeibei.shop.activity;

import java.util.HashMap;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import com.fengbeibei.shop.R;
import com.fengbeibei.shop.common.Constants;
import com.fengbeibei.shop.common.HttpClientHelper;
import com.fengbeibei.shop.common.HttpClientHelper.CallBack;
import com.fengbeibei.shop.common.MyApplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class LoginActivity extends Activity{
	private EditText mAccountEdit;
	private EditText mPasswordEdit;
	private Button mSubmitBtn;
	private Button mCancelLoginBtn;
	private String mAccountStr = "";
	private String mPasswordStr ="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		mAccountEdit = (EditText)findViewById(R.id.account_edit);
		mPasswordEdit =(EditText)findViewById(R.id.password_edit);
		mSubmitBtn = (Button)findViewById(R.id.submit_btn);
		mSubmitBtn.setClickable(false);
		mCancelLoginBtn = (Button) findViewById(R.id.cancel_login);
		mCancelLoginBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LoginActivity.this.finish();
			}
			
		});
		mAccountEdit.addTextChangedListener(new TextWatcher(){
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

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(s.length() >=2 && mPasswordStr.length() >= 6){
					mSubmitBtn.setClickable(true);
					changeButtonStyle();
				}else{
					
					mSubmitBtn.setClickable(false);
					changeButtonStyle();
				}
				 mAccountStr= mAccountEdit.getText().toString();
			}
			
		});
		mPasswordEdit.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(s.length() >= 6 && mAccountStr.length() >= 2){
					mSubmitBtn.setClickable(true);
					changeButtonStyle();
				}else{
					mSubmitBtn.setClickable(false);
					changeButtonStyle();
				}
				
				mPasswordStr = mPasswordEdit.getText().toString();
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

		mSubmitBtn.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				if(!mSubmitBtn.isClickable()){
					return;
				}
				HashMap<String,String> params = new HashMap<String,String>();
				params.put("username", mAccountStr);
				params.put("password", mPasswordStr);
				params.put("client","android");
				HttpClientHelper.asynPost(Constants.LOGIN_URL,params,new CallBack(){

					@Override
					public void onFinish(Message response) {
						// TODO Auto-generated method stub
						if (response.what == HttpStatus.SC_OK){
							String json = (String)response.obj;
							try{
								JSONObject obj = new JSONObject(json);
								
								if (obj.has("error")){
									String error = obj.getString("error");
									Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
									return;
								}
								MyApplication.getInstance().setLoginKey("user.key", obj.getString("key"));
								Intent mIntent = new Intent("0011"); 
								sendBroadcast(mIntent);
								LoginActivity.this.finish();
							} catch (JSONException e){
								e.printStackTrace();
							}
						}
					}

					@Override
					public void onError(Exception e) {
						// TODO Auto-generated method stub
						
					}
					
				});
			}
			
		});
	}
	
	@TargetApi(23)
	public void changeButtonStyle(){
		if(mSubmitBtn.isClickable()){
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
				mSubmitBtn.setTextColor(getResources().getColor(R.color.white,null));
			}else{
				mSubmitBtn.setTextColor(getResources().getColor(R.color.white));
			}
			mSubmitBtn.setBackgroundResource(R.color.btn_enable_bg_color);
		}else{
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
				mSubmitBtn.setTextColor(getResources().getColor(R.color.btn_disable_text_color,null));
			}else{
				mSubmitBtn.setTextColor(getResources().getColor(R.color.btn_disable_text_color));
			}
			mSubmitBtn.setBackgroundResource(R.color.btn_disable_bg_color);
		}
	}

	
}
