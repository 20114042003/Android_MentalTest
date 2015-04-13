package com.cx.project.mentaltest.activity;

import com.cx.project.mentaltest.R;
import com.cx.project.mentaltest.custom.HeadView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class DispositionActivity extends Activity{
	
	private HeadView headView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_disposition);
		initView();
	}

	private void initView() {
		headView = (HeadView) findViewById(R.id.head_view);
		headView.setLeftOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}

}
