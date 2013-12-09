/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ccpa.n_body;

import java.util.Random;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * A view container where OpenGL ES graphics can be drawn on screen. This view
 * can also be used to capture touch events, such as a user interacting with
 * drawn objects.
 */
public class MyGLSurfaceView extends GLSurfaceView {

	Random n = new Random();
	// the user picks the size. defaults to 10
	public static int size = 10;

	GestureDetector gestureDetector;
	Context context;

	public MyGLSurfaceView(Context context) {
		this(context, null);
	}

	public MyGLSurfaceView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyGLSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs);

		// Tell EGL to use a ES 1.0 Context
		setEGLContextClientVersion(1);
		//setEGLConfigChooser(new MultisampleConfigChooser());

		setRenderer(new MyGLRenderer());
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

		gestureDetector = new GestureDetector(context, new GestureListener());
		// gestureDetector.setOnDoubleTapListener(this);
	}

	/* touch events */
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		return this.gestureDetector.onTouchEvent(e);
	}

	/*
	 * @Override public boolean onDoubleTap(MotionEvent e) {
	 * MyGLRenderer.togglePaused(); return true; }
	 * 
	 * @Override public boolean onDoubleTapEvent(MotionEvent e) { return true; }
	 * 
	 * @Override public boolean onSingleTapConfirmed(MotionEvent e) {
	 * MyGLRenderer.addCircle(e.getX(), e.getY());
	 * 
	 * Log.d("Tap", "onSingleTapConfirmed");
	 * 
	 * return true; }
	 * 
	 * @Override public boolean onDown(MotionEvent e) { Log.d("Tap", "onDown");
	 * return true; }
	 * 
	 * @Override public boolean onFling(MotionEvent e1, MotionEvent e2, float
	 * velocityX, float velocityY) {
	 * 
	 * Log.d("Tap", "onFling");
	 * 
	 * MyGLRenderer.addCircle(e1.getX(), e1.getY(), velocityX / VSCALE,
	 * velocityY / VSCALE);
	 * 
	 * return true; }
	 * 
	 * @Override public void onLongPress(MotionEvent e) { Log.d("Tap",
	 * "onLongPress"); }
	 * 
	 * @Override public boolean onScroll(MotionEvent e1, MotionEvent e2, float
	 * distanceX, float distanceY) { // TODO Auto-generated method stub return
	 * false; }
	 * 
	 * @Override public void onShowPress(MotionEvent e) { // TODO Auto-generated
	 * method stub }
	 * 
	 * @Override public boolean onSingleTapUp(MotionEvent e) { return true;
	 */

	private class GestureListener extends
			GestureDetector.SimpleOnGestureListener {
		public GestureListener() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onLongPress(MotionEvent e) {
			if(!globalVar.uniformSize){
				if(!globalVar.uniformColor){
					//                                             SCALE                           Red                 Green               Blue
					MyGLRenderer.addCircle(e.getX(), e.getY(), ((n.nextFloat() * size) + 3), ((n.nextFloat())), ((n.nextFloat())), ((n.nextFloat())));
					Log.d("Circle", e.getX() + ", " + e.getY());
					requestRender();
				}
				else
				{
					MyGLRenderer.addCircle(e.getX(), e.getY(), ((n.nextFloat() * size) + 3), globalVar.colorRed, globalVar.colorGreen, globalVar.colorBlue);
					Log.d("Circle", e.getX() + ", " + e.getY());
					requestRender();
				}
			}
			else
			{
				if(!globalVar.uniformColor){
					MyGLRenderer.addCircle(e.getX(), e.getY(), size+3, ((n.nextFloat())), ((n.nextFloat())), ((n.nextFloat())));
					Log.d("Circle", e.getX() + ", " + e.getY());
					requestRender();
				}
				else
				{
					MyGLRenderer.addCircle(e.getX(), e.getY(), size+3, globalVar.colorRed, globalVar.colorGreen, globalVar.colorBlue);
					Log.d("Circle", e.getX() + ", " + e.getY());
					requestRender();
				}
			}
		
			// return true; }
		}
	}
}
