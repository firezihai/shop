package com.fengbeibei.shop.widget;


import android.R.color;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class CircleImageView extends ImageView{



	public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CircleImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Drawable drawable = getDrawable();
		if(drawable == null){
			Log.d("CircleImageView onDraw","drawable资源不存在");
		}
		try{
		Paint paint = new Paint();
		paint.setAntiAlias(true); //设置画笔无锯齿
		paint.setFilterBitmap(false);
		//设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		float width = getWidth();
		float height = getHeight();
		int layer = canvas.saveLayer(0.0f, 0.0f, width, height, null,31);
		drawable.setBounds(0, 0, (int)width, (int)height);//设置绘图范围
		drawable.draw(canvas);
		Bitmap mask = mask();
		canvas.drawBitmap(mask, 0, 0, paint);
		canvas.restoreToCount(layer);
		return;
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public Bitmap mask(){
		int width = getWidth();
		int height= getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint(1);
		paint.setColor(-16777216);
		RectF rectf = new RectF(0,0,width,height);
		canvas.drawOval(rectf, paint);
		return bitmap;
	}

	

}
