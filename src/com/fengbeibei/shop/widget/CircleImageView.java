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
			Log.d("CircleImageView onDraw","drawable��Դ������");
		}
		try{
		Paint paint = new Paint();
		paint.setAntiAlias(true); //���û����޾��
		paint.setFilterBitmap(false);
		//���õ�����ͼ���ཻʱ��ģʽ��SRC_INΪȡSRCͼ���ཻ�Ĳ��֣�����Ľ���ȥ��
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		float width = getWidth();
		float height = getHeight();
		int layer = canvas.saveLayer(0.0f, 0.0f, width, height, null,31);
		drawable.setBounds(0, 0, (int)width, (int)height);//���û�ͼ��Χ
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
