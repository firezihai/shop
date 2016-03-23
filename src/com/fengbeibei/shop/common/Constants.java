package com.fengbeibei.shop.common;

import android.os.Environment;

public final class Constants {
	public final static String CACHE_DIR_IMAGE;
	public final static String CACHE_DIR;
	public final static String APP_CODE = "shop.fengbeibei.com";
	/**
	 * �������������Э��
	 */
	public final static String PROTOCOL = "http://";
	/** 
	 *����������
	 */
	public final static String HOST = "www.fengbeibei.com";
	
	/**
	 * �������˿�
	 */
	public final static String PORT = ":80";
	
	/**
	 * ��������Ӧ������
	 */
	public final static String APP = "/mobile";
	
	public final static String PAGESIZE = "20";
	
	/**
	 * ���������������
	 */
	public final static String APP_URL = PROTOCOL + HOST  + APP + "/index.php?";
	
	/**
	 * ��ҳ������������ӿ�
	 */
	public final static String HOME_URL = APP_URL + "act=index&op=index";
	
	/**
	 * ��ҳ��Ʒ�б�����ӿ�
	 */
	 public final static String HOME_GOODS_URL = APP_URL + "act=index&op=pull_load";
	 /**
	  * ������Ʒ���������Ʒ
	  */
	 public final static String PARENT_CATEGORY_URL = APP_URL+"act=goods_class";
	 /**
	  * �ӷ�����Ʒ����ӿ�
	  */
	 public final static String CHILD_CATEGORY_URL = APP_URL+"act=goods_class&op=get_child_list";
	 /**
	  * ���ﳵ��Ʒ�ӿ�
	  */
	 public final static String CART_LIST_URL = APP_URL+"act=cart&op=cart_list";
	 
	 static{
		 if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
			 CACHE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/fengbeibei/"; 
		 }else{
			 CACHE_DIR = Environment.getRootDirectory().getAbsolutePath() + "/fengbeibei/";
		 }
		 CACHE_DIR_IMAGE = CACHE_DIR + "/image/";
	 }
}
