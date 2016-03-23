package com.fengbeibei.shop.common;

import java.io.File;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{
	private boolean LOGIN_STATE = false;
	private static Context mContext;
	private static MyApplication mIntance;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		mContext = getApplicationContext();
		mIntance = this;
		initImageLoader(this);
	}
	public static MyApplication getInstance(){
		return mIntance;
	}
	public static Context getContext(){
		return mContext;
	}
	public boolean isLogin(){
		return LOGIN_STATE;
	}
	
	public void loaderUserInfo(){
		
	}
	public void initImageLoader(Context context){
		File cacheDir = new File(Constants.CACHE_DIR_IMAGE);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.diskCache(new UnlimitedDiscCache(cacheDir))
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}
