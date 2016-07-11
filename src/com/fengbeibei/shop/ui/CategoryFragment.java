package com.fengbeibei.shop.ui;

import java.util.ArrayList;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fengbeibei.shop.R;
import com.fengbeibei.shop.adapter.CategoryAdapter;
import com.fengbeibei.shop.adapter.CategoryGridViewAdapter;
import com.fengbeibei.shop.bean.Category;
import com.fengbeibei.shop.common.Constants;
import com.fengbeibei.shop.common.HttpClientHelper;
import com.fengbeibei.shop.common.HttpClientHelper.CallBack;
import com.fengbeibei.shop.widget.MyGridView;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class CategoryFragment extends Fragment{
	private ListView mParentCategoryLayout;
	private LinearLayout mChildCategoryLayout;
	private CategoryAdapter mCategoryAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View categoryLayout = inflater.inflate(R.layout.category,container, false);
		 mParentCategoryLayout = (ListView)categoryLayout.findViewById(R.id.parentCategory);
		mChildCategoryLayout = (LinearLayout) categoryLayout.findViewById(R.id.childCategory);
		initParentCategory();
		getChildCategory("1");
		return categoryLayout;
	}
	
	
	public void initParentCategory(){
		HttpClientHelper.asynGet(Constants.PARENT_CATEGORY_URL, new CallBack(){

			@Override
			public void onFinish(Message response) {
				// TODO Auto-generated method stub
				if(response.what == HttpStatus.SC_OK){
					String json = (String) response.obj;
					try{
						JSONObject obj = new JSONObject(json);
						String categoryJson= obj.getString("class_list");
						ArrayList<Category> parentCategory = Category.newIntance(categoryJson);
						mCategoryAdapter = new CategoryAdapter(CategoryFragment.this.getContext());
						mCategoryAdapter.setSelectedPosition(0);
						mCategoryAdapter.setCategoryData(parentCategory);
						
						mParentCategoryLayout.setAdapter(mCategoryAdapter);
						mParentCategoryLayout.setOnItemClickListener(new OnItemClickListener(){

							@Override
							public void onItemClick(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								// TODO Auto-generated method stub
								int itemPosition = mCategoryAdapter.getSelectedPosition();
								Category category =(Category) mParentCategoryLayout.getItemAtPosition(arg2);
								mCategoryAdapter.setSelectedPosition(arg2);
						     //  mCategoryAdapter.notifyDataSetInvalidated();
								if(category != null && !(itemPosition == arg2) ){
									getChildCategory(category.getGcId());
								}
							}
							
						});
				
					} catch(JSONException e){
						e.printStackTrace();
					}
					
				}
			}

			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public void getChildCategory(String gcId){
		String url = Constants.CHILD_CATEGORY_URL+"&gc_id="+gcId;
		HttpClientHelper.asynGet(url, new CallBack(){

			@Override
			public void onFinish(Message response) {
				// TODO Auto-generated method stub
				if(response.what == HttpStatus.SC_OK){
					mChildCategoryLayout.removeAllViews();
					String json = (String)response.obj;
					try{
						JSONObject obj = new JSONObject(json);
						if(!obj.isNull("class_list")){
							JSONArray arr = new JSONArray(obj.getString("class_list"));
							int size = arr == null ? 0 : arr.length();
							for( int i = 0 ; i < size ; i ++){
								JSONObject cateObj = arr.getJSONObject(i);
								JSONObject cateJsonObj = new JSONObject(cateObj.toString());
								String parentId = cateJsonObj.getString("gc_id");
								String parentName = cateJsonObj.getString("gc_name");
								View childCategoryView =	getActivity().getLayoutInflater().inflate(R.layout.child_category_item, null);
								TextView childCategoryTitle = (TextView) childCategoryView.findViewById(R.id.categoryName);
								childCategoryTitle.setText(parentName);
							
						
								if(!cateJsonObj .isNull("child")){
										String childJson = cateJsonObj.optString("child");
										MyGridView categoryGridView = (MyGridView)childCategoryView.findViewById(R.id.categoryGridView);
										ArrayList<Category> childCategoryData = Category.newIntance(childJson);
										CategoryGridViewAdapter  categoryGridViewAdapter = new CategoryGridViewAdapter(CategoryFragment.this.getContext(),R.layout.child_category_grid,childCategoryData);
										categoryGridView.setAdapter(categoryGridViewAdapter);
								}
								mChildCategoryLayout.addView(childCategoryView);
							}
						}
						
					} catch (JSONException e){
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	

}
