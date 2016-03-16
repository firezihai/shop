package com.fengbeibei.shop.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpHelper {

	public interface HttpCallback{
		void onFinish(String response);
		void onError(Exception e);
	}
	public static void doPost(final String address,final HttpCallback callback){
		new Thread(new Runnable(){

			@Override
			public void run() {
				HttpURLConnection connection = null;
				try{
					URL url = new URL(address);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("POST");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					connection.setDoInput(true);
					connection.setDoOutput(true);
					InputStream inputStream = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
					
					StringBuilder response = new StringBuilder();
					String line;
					while((line = reader.readLine()) != null){
						response.append(line);
					}
					if(callback != null){
						inputStream.close();
						reader.close();
						callback.onFinish(response.toString());
					}
					
				} catch (Exception e){
					if(callback != null){
						callback.onError(e);
					}
				} finally {
					if(connection != null){
						connection.disconnect();
					}
				}
				
			}
			
		});
	}
	
	public static void doGet(final String address,final HttpCallback callback){
		new Thread(new Runnable(){

			@Override
			public void run() {
				HttpURLConnection connection = null;
				try{
					URL url = new URL(address);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					connection.setDoInput(true);
					connection.setDoOutput(true);
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while((line = reader.readLine()) != null){
						response.append(line);
					}
					if(callback != null){
						callback.onFinish(response.toString());
					}
					
				} catch (Exception e){
					if(callback != null){
						callback.onError(e);
					}
				} finally {
					if(connection != null){
						connection.disconnect();
					}
				}
				
			}
			
		});
	}
	
}
