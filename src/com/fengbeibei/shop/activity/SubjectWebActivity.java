package com.fengbeibei.shop.activity;


import com.fengbeibei.shop.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class SubjectWebActivity extends Activity{
	private WebView mWebView;
	private ProgressBar mPorgressBar;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_activity);
		mWebView = (WebView) findViewById(R.id.webviewID);
		mPorgressBar = (ProgressBar) findViewById(R.id.progressBar1);
		WebSettings setting = mWebView.getSettings();

		setting.setUseWideViewPort(true);
		setting.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		setting.setLoadWithOverviewMode(true);
		setting.setDefaultZoom(WebSettings.ZoomDensity.FAR);
		setting.setSupportZoom(false);
		setting.setBuiltInZoomControls(false);
		setting.setJavaScriptEnabled(true);
		setting.setCacheMode(WebSettings.LOAD_NO_CACHE);
		setting.setAppCacheEnabled(false);
	    if(Build.VERSION.SDK_INT >= 19) {
	    	setting.setLoadsImagesAutomatically(true);
	    } else {
	    	setting.setLoadsImagesAutomatically(false);
	    }
		
		String data = getIntent().getStringExtra("data");
		data = "http://www.fengbeibei.com/"+data;
		mWebView.setWebChromeClient(new MyWebChromeClient());
		
		mWebView.loadUrl(data);
		mWebView.setWebViewClient(new WebViewClient(){

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				if(!mWebView.getSettings().getLoadsImagesAutomatically()) {
					mWebView.getSettings().setLoadsImagesAutomatically(true);
			    }
			//	super.onPageFinished(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
			}

			

			@Override
			public void onReceivedError(WebView view,
					WebResourceRequest request, WebResourceError error) {
				// TODO Auto-generated method stub
				view.loadUrl("file:///android_asset/error.html");
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}
			
		});
	}
	private class MyWebChromeClient extends WebChromeClient{

		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			// TODO Auto-generated method stub
			mPorgressBar .setProgress(newProgress);  
	        if(newProgress==100){  
	        	mPorgressBar.setVisibility(View.GONE);  
	        }  
	        super.onProgressChanged(view, newProgress);  
		}
		
	}
	

}
