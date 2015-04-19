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
		 * ��һ������ �� ��ʱ��
		 * �ڶ��������������ִ��һ�� onTick ����
		 * ����Ϊ5000,1000���ǣ�onTickִ��3�Σ�onFinishִ��1�Ρ�
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
	 * ��ת��������
	 */
	private void toMainActivity() {
		startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
	}

}
