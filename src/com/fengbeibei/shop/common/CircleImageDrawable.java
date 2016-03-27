package com.fengbeibei.shop.common;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;

public class CircleImageDrawable extends Drawable{
	private Paint mPaint;
	private Bitmap mBitmap;
	private RectF rectf;
	private int mWidth;
	public CircleImageDrawable(Bitmap bitmap) {
		mBitmap = bitmap;
		BitmapShader bitmapShader = new BitmapShader(bitmap,TileMode.CLAMP,TileMode.CLAMP);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setShader(bitmapShader);
		mWidth = Math.min(mBitmap.getWidth(), mBitmap.getHeight());
	}
	
	@Override
	public void setBounds(int left, int top, int right, int bottom) {
		// TODO Auto-generated method stub
		super.setBounds(left, top, right, bottom);
		rectf = new RectF(left,top,right,bottom);
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawCircle(mWidth/2, mWidth/2, mWidth/2, mPaint);
	}
	
	@Override
	public int getIntrinsicHeight() {
		// TODO Auto-generated method stub
		return mWidth;
	}

	@Override
	public int getIntrinsicWidth() {
		// TODO Auto-generated method stub
		return mWidth;
	}

	@Override
	public int getOpacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAlpha(int alpha) {
		// TODO Auto-generated method stub
		mPaint.setAlpha(alpha);
	}

	@Override
	public void setColorFilter(ColorFilter colorFilter) {
		// TODO Auto-generated method stub
		mPaint.setColorFilter(colorFilter);
	}

}
