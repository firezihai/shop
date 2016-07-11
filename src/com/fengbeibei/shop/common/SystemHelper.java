package com.fengbeibei.shop.common;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class SystemHelper {
	/**
	 * DisplayImageOptions是用于设置图片显示的类
	 */
	public static DisplayImageOptions getDisplayImageOptions() {
		DisplayImageOptions options =  new DisplayImageOptions.Builder()
		.showImageForEmptyUri(0)
		.showImageOnFail(0)
		.resetViewBeforeLoading(true)
		.cacheOnDisk(true)
		.cacheInMemory(true)						// 设置下载的图片是否缓存在内存中
		.imageScaleType(ImageScaleType.EXACTLY)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.considerExifParams(true)
		.displayer(new FadeInBitmapDisplayer(000))//图片显示时间
		.build();
		
		return options;	
	}
}
