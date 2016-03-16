package com.fengbeibei.shop.common;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class SystemHelper {
	/**
	 * DisplayImageOptions����������ͼƬ��ʾ����
	 */
	public static DisplayImageOptions getDisplayImageOptions() {
		DisplayImageOptions options =  new DisplayImageOptions.Builder()
		.showImageForEmptyUri(0)
		.showImageOnFail(0)
		.resetViewBeforeLoading(true)
		.cacheOnDisk(true)
		.cacheInMemory(true)						// �������ص�ͼƬ�Ƿ񻺴����ڴ���
		.imageScaleType(ImageScaleType.EXACTLY)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.considerExifParams(true)
		.displayer(new FadeInBitmapDisplayer(000))//ͼƬ��ʾʱ��
		.build();;
		
		return options;	
	}
}
