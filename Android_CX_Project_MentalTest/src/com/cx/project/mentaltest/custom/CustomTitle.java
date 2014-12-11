package com.cx.project.mentaltest.custom;


import com.cx.project.mentaltest.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomTitle extends FrameLayout{
	private TextView txTitle;
	private ImageView imgNavi;

	public CustomTitle(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		//�󶨲���
		LayoutInflater.from(context).inflate(R.layout.custom_title, this);
		
		initNavi();
		initTitle();
	}

	private void initNavi() {
		imgNavi = (ImageView) findViewById(R.id.img_navi);
		
	}

	/**
	 * ��ʼ������
	 */
	private void initTitle() {
		txTitle = (TextView) findViewById(R.id.tx_title);
		
	}
	
	/**
	 * ���ñ���
	 * @param title
	 */
	public void setTitle(String title) {
		txTitle.setText(title);
	}
	
	

}
