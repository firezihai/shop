package com.fengbeibei.shop.common;

public final class Constants {
	
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
}
