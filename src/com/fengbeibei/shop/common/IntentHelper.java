package com.fengbeibei.shop.common;

import android.content.Context;
import android.content.Intent;

import com.fengbeibei.shop.activity.GoodsDetailActivity;
import com.fengbeibei.shop.activity.SubjectWebActivity;

public class IntentHelper {

	public static void goodsDetail(Context context,String goods_id){
		Intent intent  = new Intent(context,GoodsDetailActivity.class);
		intent.putExtra("goods_id", goods_id);
		context.startActivity(intent);
	}
	
	public static void subjectWeb(Context context,String url){
		Intent intent  = new Intent(context,SubjectWebActivity.class);
		intent.putExtra("data", url);
		context.startActivity(intent);
	}
	
	public static void filter(Context context,String type ,final String data){
		if(type.equals("keyword")){
			
		}else if(type.equals("special")){//专题编号
			
		} else if (type.equals("url")){
			IntentHelper.subjectWeb(context, data);
		} else if (type.equals("goods")){
			IntentHelper.goodsDetail(context,data);
		}
	}
}
