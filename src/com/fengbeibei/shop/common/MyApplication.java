package com.fengbeibei.shop.common;

import java.io.File;
import java.util.Properties;

import com.fengbeibei.shop.R;
import com.fengbeibei.shop.bean.User;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MyApplication extends Application{
	private static Context mContext;
	private static MyApplication mIntance;
	private static boolean mIsLogin = true;
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
		String key = getLoginKey();
		String isLogin = getProperty("user.login_state");
		if(key != null && !"".equals(key) && isLogin != null &&  isLogin.equals("1")){
			mIsLogin = true;
		}else{
			mIsLogin = false;
		}
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
				setProperty("user.login_state","1");
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
	
	public static void showToast(String message){
		if (message != null  && !message.equalsIgnoreCase("")){
			View view = LayoutInflater.from(mContext).inflate(R.layout.view_toast,null);
			((TextView) view.findViewById(R.id.toastMessage)).setText(message);
			Toast toast = new Toast(mContext);
			toast.setView(view);
			toast.setGravity(Gravity.CENTER,0, 0);
			toast.setDuration(Toast.LENGTH_LONG);
			toast.show();
		}
	}
	
	public static Dialog createLoadingDialog(Context context,String msg){
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.view_toast, null);
		LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.dialog_view);
		ImageView imageView = (ImageView) v.findViewById(R.id.toastIcon);
	   TextView textMsg = (TextView) v.findViewById(R.id.toastMessage);
		Animation loadingAnimation = AnimationUtils.loadAnimation(mContext, R.anim.loading_animation);
		imageView.startAnimation(loadingAnimation);
		textMsg.setText(msg);
		imageView.setImageResource(R.drawable.loading_circle);
		imageView.setVisibility(View.VISIBLE);
		Dialog loadingDialog = new Dialog(context,R.style.loadingDialog);
		loadingDialog.setCancelable(false);
		loadingDialog.setContentView(linearLayout, new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		return loadingDialog;
	}
}
