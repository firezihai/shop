package com.fengbeibei.shop.common;

public final class Constants {
	
	public final static String APP_CODE = "shop.fengbeibei.com";
	/**
	 * 与服务器端连接协义
	 */
	public final static String PROTOCOL = "http://";
	/** 
	 *服务器域名
	 */
	public final static String HOST = "www.fengbeibei.com";
	
	/**
	 * 服务器端口
	 */
	public final static String PORT = ":80";
	
	/**
	 * 服务器端应用名称
	 */
	public final static String APP = "/mobile";
	
	/**
	 * 服务器端请求入口
	 */
	public final static String APP_URL = PROTOCOL + HOST  + APP + "/index.php?";
	
	/**
	 * 首页请求内容请求接口
	 */
	public final static String HOME_URL = APP_URL + "act=index&op=index";
	
	/**
	 * 首页商品列表请求接口
	 */
	 public final static String HOME_GOODS_URL = APP_URL + "act=index&op=pull_load";
}
