package com.cx.project.mentaltest.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

/**
 * 
 * @description  ��������  <br />
 * @author CxiaoX
 *
 * 2015��4��18������1:04:06
 */
public class PortraitActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

}
