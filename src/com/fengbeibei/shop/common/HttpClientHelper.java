package com.fengbeibei.shop.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

@SuppressWarnings("deprecation")
public class HttpClientHelper {
	private static final String HASMORE = "hasmore";
	private static final String CODE = "code";
	private static final String RESULT = "RESULT";
	private static final String DATAS = "datas";
	private static final String COUNT  = "count";
	private static HttpClient mHttpClient = null;
	public interface CallBack{
		public void onFinish(Message response);
		public void onError(Exception e);
	}
	private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(6,
			30, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	static {
		if(mHttpClient == null){
			SchemeRegistry schreg = new SchemeRegistry();
			schreg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
//			schreg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
			HttpParams httpParams = new BasicHttpParams();
		    HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
		    HttpProtocolParams.setUseExpectContinue(httpParams, false);
			// 设置最大连接数  

			// 设置最大连接数  
	        ConnManagerParams.setMaxTotalConnections(httpParams, 10000);  
	        // 设置获取连接的最大等待时间  
	        ConnManagerParams.setTimeout(httpParams, 60000);  
	        // 设置每个路由最大连接数  
	        ConnPerRouteBean connPerRoute = new ConnPerRouteBean(10000);
	        ConnManagerParams.setMaxConnectionsPerRoute(httpParams,connPerRoute);  
	        // 设置连接超时时间  
	        HttpConnectionParams.setConnectionTimeout(httpParams, 100000);
	        // 设置读取超时时间  
	        HttpConnectionParams.setSoTimeout(httpParams, 100000);
			ClientConnectionManager connManager = new  ThreadSafeClientConnManager(httpParams, schreg);
			
			mHttpClient = new DefaultHttpClient(connManager, httpParams);

		}
	}
	
	public static HttpClient getHttpClient(){
		return mHttpClient;
	}
	
	public static String get(String url) throws IOException{
		String result = null;
		HttpGet get = new HttpGet(url);
		HttpResponse response = mHttpClient.execute(get);
		if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()){
			result = EntityUtils.toString(response.getEntity());
		}
		return result;
	}
	
	public static String post(String url,HashMap<String,String> params) throws IOException{
		String result = null;
		HttpPost post = new HttpPost(url);
		if(null != params){
			List<NameValuePair> pairList = new ArrayList<NameValuePair>();
			for (Entry<String, String> paramPair : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(paramPair.getKey()
						, paramPair.getValue());
				pairList.add(pair);
			}
			HttpEntity entity = new UrlEncodedFormEntity(pairList, HTTP.UTF_8);
			post.setEntity(entity);
		}
		HttpResponse response = mHttpClient.execute(post);
	    if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()){
	        result = EntityUtils.toString(response.getEntity());
	    }
		return result;
	}
	
	public static void asynGet(final String url , final CallBack callback ){
		final Handler handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				callback.onFinish(msg);
			}
			
		};
		threadPool.execute(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg = handler.obtainMessage(HttpStatus.SC_OK);
				msg.getData().putBoolean(HASMORE, false);
				try{
					String json = get(url);
					if (json != null && !"".equals(json)	&& !"null".equalsIgnoreCase(json)) {
						json = json.replaceAll("\\x0a|\\x0d", "");
						JSONObject obj = new JSONObject(json);
						if (null != obj && obj.has(CODE)) {
							if (obj.has(DATAS)) {
								msg.obj = obj.getString(DATAS);
							}
							 if(obj.has(HASMORE)){
								 msg.getData().putBoolean(HASMORE, obj.getBoolean(HASMORE));
							 }
							 if(obj.has(COUNT)){
								 msg.getData().putLong(COUNT, obj.getLong(COUNT));
							 }
							
							 if(obj.has(RESULT)){
								 msg.getData().putString(RESULT, obj.getString(RESULT));
							 }
						}
					}else{
						msg.what = HttpStatus.SC_INTERNAL_SERVER_ERROR;
					}
					
				} catch (IOException e){
					msg.what = HttpStatus.SC_REQUEST_TIMEOUT;
					e.printStackTrace();
				} catch (JSONException e) {
					msg.what = HttpStatus.SC_INTERNAL_SERVER_ERROR;
					e.printStackTrace();
				}
				handler.sendMessage(msg);
			}
			
		});
	}
	
	public static void asynPost(final String url,final HashMap<String,String> params,final CallBack callback){
		final Handler handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				callback.onFinish(msg);
			}
			
		};
		threadPool.execute(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg = handler.obtainMessage(HttpStatus.SC_OK);
				msg.getData().getBoolean(HASMORE,false);
				try{
					String json = post(url,params);
					if(json != null && !"".equals(json) && !"null".equalsIgnoreCase(json) ){
						// 注意:目前服务器返回的JSON数据串中会有特殊字符（如换行）。需要处理一下 assic码x0a换行 ,x0d回车
						json = json.replaceAll("\\x0a|\\x0d", json);
						JSONObject jsonObj = new JSONObject(json);
						if(jsonObj != null && jsonObj.has(CODE)){
					//		msg.what = Integer.valueOf(jsonObj.getString(CODE));
							if(jsonObj.has(DATAS)){
								msg.obj = jsonObj.getString(DATAS);
							}
							if(jsonObj.has(HASMORE)){
								msg.getData().putBoolean(DATAS, jsonObj.getBoolean(HASMORE));
							}
							if (jsonObj.has(RESULT)){
								msg.getData().putString(RESULT, jsonObj.getString(RESULT));
							}
							if (jsonObj.has(COUNT)){
								msg.getData().putLong(COUNT, jsonObj.getLong(COUNT));
							}
						}else{
							msg.what = HttpStatus.SC_REQUEST_TIMEOUT;
						}
					}else{
						msg.what = HttpStatus.SC_INTERNAL_SERVER_ERROR;
					}
				} catch (IOException e){
					msg.what = HttpStatus.SC_REQUEST_TIMEOUT;
					e.printStackTrace();
				} catch (JSONException e) {
					msg.what = HttpStatus.SC_INTERNAL_SERVER_ERROR;
					e.printStackTrace();
				}
				handler.sendMessage(msg);
			}
			
		});
	}

}
