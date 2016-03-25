package com.fengbeibei.shop.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import android.content.Context;
import android.os.Environment;

public class AppConfig {
	private final static String APP_CONFIG = "config";
	/** ͼƬ����Ŀ¼  */
	public static final String CACHE_DIR_IMAGE;
	/** ���ػ���Ŀ¼  */
	public static final String CACHE_DIR;
	/**
	 * �����Ķ���
	 */
	private Context mContext;
	/**
	 * AppConfigʵ��
	 */
	private static AppConfig appConfig;
	static {
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
			CACHE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/fengbeibei/";
		}else{
			CACHE_DIR = Environment.getRootDirectory().getAbsolutePath() + "/fengbeibei/";
		}
		CACHE_DIR_IMAGE = CACHE_DIR + "/image";
	}
	/**
	 * Appconfig��������
	 * @param context
	 * @return
	 */
	public static AppConfig intance(Context context){
		if (appConfig == null){
			appConfig = new AppConfig();
			appConfig.mContext = context;
		}
		
		return appConfig;
	}
	/**
	 * ����һ��Properties����
	 * @return 
	 */
	private Properties getProperties(){
		FileInputStream fileInputStream = null;
		Properties properties = new Properties();
		try{
			File configDir = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
			fileInputStream = new FileInputStream(configDir.getPath() + File.separator + APP_CONFIG);
			properties.load(fileInputStream);
			
		} catch (Exception e){
			
		} finally{
			try{
				fileInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return properties;
	}
	/**
	 * ����һ��Properties����
	 * @param p
	 */
	private void setProperties(Properties p){
		FileOutputStream fileOutputStream = null;
		try{
			File configDir = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
			File conf = new File(configDir,APP_CONFIG);
			fileOutputStream = new FileOutputStream(conf);
			p.store(fileOutputStream, null);
			fileOutputStream.flush();
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				fileOutputStream.close();
			} catch (Exception e){
			}
		}
	}
	/**
	 * ����key��Properties�ļ��л�ȡ��Ӧ��ֵ
	 * @param key
	 * @return
	 */
	public String get(String key){
		Properties properties = getProperties();
		return (properties != null) ? properties.getProperty(key) : null;
	}
	/**
	 * ��Properties�ļ���д��һ��key/value�ļ�ֵ������
	 * @param key
	 * @param value
	 */
	public void set(String key,String value){
		Properties properties = getProperties();
		properties.setProperty(key, value);
		setProperties(properties);
	}
	/**
	 * 
	 * @param p
	 */
	public void set(Properties p){
		Properties properties = getProperties();
		properties.putAll(p);
		setProperties(properties);
	}
	/**
	 * ����key����Properties�Ƴ�һ����ֵ������
	 * @param key
	 */
	public void remove(String... key){
		Properties properties = getProperties();
		for (String k : key){
			properties.remove(k);
		}
		setProperties(properties);
	}
}
