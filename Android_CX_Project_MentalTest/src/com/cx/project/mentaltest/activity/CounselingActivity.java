package com.cx.project.mentaltest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.cx.project.mentaltest.R;
import com.cx.project.mentaltest.custom.HeadView;

public class CounselingActivity extends PortraitActivity {
	private static final String TAG ="CounselingActivity";
	private HeadView headView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_counseling);
		
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

	public void onclick1(View view){
		startActivity(new Intent(this, IntroductionConsultingActivity.class));
	}
	public void onclick2(View view){
		Toast.makeText(this, "¿ª·¢ÖÐ", Toast.LENGTH_SHORT).show();
	}

}
