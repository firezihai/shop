package com.fengbeibei.shop.common;

import java.io.File;
import java.util.Properties;

import com.fengbeibei.shop.bean.User;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{
	private static Context mContext;
	private static MyApplication mIntance;
	private static boolean mIsLogin = false;
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
	/**
	 * 登陆状态
	 * @return
	 */
	public boolean isLogin(){
		return mIsLogin;
	}
	/**
	 * 获取登陆key
	 * @return
	 */
	public String getLoginKey(){
		return AppConfig.intance(this).get("user.key");
	}
	/**
	 * 加载会员信息
	 */
	public User getUserInfo(){
		User user = new User();
		user.setUserName(getProperty("user.name"));
		user.setPoint(getProperty("user.point"));
		user.setPredeposit(getProperty("user.predeposit"));
		user.setVoucherCount(getProperty("user.voucher_count"));
		user.setAvailableRcBalance(getProperty("user.available_rc_balance"));
		user.setOrderNopayCount(getProperty("user.order_nopay_count"));
		user.setOrderNoshipCount(getProperty("user.order_noship_count"));
		user.setOrderNoreceiptCount(getProperty("user.order_noreceipt_count"));
		user.setOrderNocommentCount(getProperty("user.order_nocomment_count"));
		user.setFriendCount(getProperty("user.friend_count"));
		user.setFavoritesCount(getProperty("uer.favorites_count"));
		user.setInviteQrcode(getProperty("user.invite_qrcode"));
		return user;
	}
	
	public void setUserInfo(final User user){
		setProperties(new Properties(){
			{
				setProperty("user.name",user.getUserName());
				setProperty("user.avatar",user.getUserAvatar());
				setProperty("user.point",user.getPoint());
				setProperty("user.predeposit",user.getPredeposit());
				setProperty("user.voucher_count",user.getVoucherCount());
				setProperty("user.available_rc_balance",user.getAvailableRcBalance());
				setProperty("user.order_nopay_count",user.getOrderNopayCount());
				setProperty("user.order_noship_count",user.getOrderNoshipCount());
				setProperty("user.order_noreceipt_count",user.getOrderNoreceiptCount());
				setProperty("user.order_nocomment_count",user.getOrderNocommentCount());
				setProperty("user.friend_count",user.getFriendCount());
				setProperty("user.favorites_count",user.getFavoritesCount());
				setProperty("user.invite_qrcoe",user.getInviteQrcode());
			}
		});
	}
	/**
	 * 
	 */
	/**
	 * 将登陆key写入配置信息
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("serial")
	public void setLoginKey(final String key,final String value){
		this.mIsLogin = true;
		setProperty(key,value);
	}
	/**
	 * 保存一个propeties对象
	 * @param properties
	 */
	public void setProperties(Properties properties){
		AppConfig.intance(mContext).set(properties);
	}
	/**
	 * 向properties文件写入键值对配置
	 * @param key
	 * @param value
	 */
	public void setProperty(String key,String value){
		AppConfig.intance(this).set(key, value);
	}
	/**
	 * 读取properties文件中对应键的值
	 * @param key
	 * @return 
	 */
	public String getProperty(String key){
		return AppConfig.intance(this).get(key);
	}
	public void initImageLoader(Context context){
		File cacheDir = new File(AppConfig.CACHE_DIR_IMAGE);
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
