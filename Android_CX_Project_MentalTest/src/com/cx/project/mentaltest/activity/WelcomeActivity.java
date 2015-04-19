package com.cx.project.mentaltest.activity;

import com.cx.project.mentaltest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

public class WelcomeActivity extends PortraitActivity{
	private static final String TAG ="WelcomeActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_welcome);
		
		/**
		 * 第一个参数 ： 总时间
		 * 第二个参数：隔多久执行一次 onTick 方法
		 * 参数为5000,1000，是，onTick执行3次，onFinish执行1次。
		 */
		new CountDownTimer(3000,3000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
			}
			
			@Override
			public void onFinish() {
				toMainActivity();
				finish();
			}

		}.start();
	}
	
	/**
	 * 跳转至主界面
	 */
	private void toMainActivity() {
		startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
	}

}
