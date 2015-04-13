package com.cx.project.mentaltest.custom;


import com.cx.project.mentaltest.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class HeadView extends FrameLayout{
	
	public static final int LEFT_ICON = R.id.img_head_left_icon;
	public static final int RIGHT_ICON = R.id.img_head_right_icon;
	
	
	private ImageView imgLeft;
	private ImageView imgRight;
	private TextView txTitle;

	public HeadView(Context context) {
		super(context);
	}
	
	public HeadView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater.from(context).inflate(R.layout.view_head,this);
		
		initView();
		initData(attrs);
	}
	
	

	public HeadView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		imgLeft = (ImageView) findViewById(R.id.img_head_left_icon);
		imgRight = (ImageView) findViewById(R.id.img_head_right_icon);
		txTitle = (TextView) findViewById(R.id.tx_head_title);
		
	}
	/**
	 * �󶨲����ļ��е�����
	 * @param attrs
	 */
	private void initData(AttributeSet attrs) {
		TypedArray array = getContext().obtainStyledAttributes(attrs,R.styleable.HeadView);
		
		//���xml�е�����
		String  title = array.getString(R.styleable.HeadView_hv_title); 
		int leftIconId = array.getResourceId(R.styleable.HeadView_left_icon, R.drawable.back_style);
		int rightIconId = array.getResourceId(R.styleable.HeadView_right_icon, R.drawable.selector_add);
		boolean showRight = array.getBoolean(R.styleable.HeadView_show_right, false);
	
		
		//����xml �е�����
		txTitle.setText(title);
		imgLeft.setImageResource(leftIconId);
		imgRight.setImageResource(rightIconId);
		if(showRight){
			imgRight.setVisibility(View.VISIBLE);
		}else{
			imgRight.setVisibility(View.GONE);
		}
	}
	
	

	/**
	 * ����ͷ���ؼ��� ����
	 * @param title
	 */
	public void setTitle(String title){
		txTitle.setText(title);
	}
	
	/**
	 * Ϊ��ߵ�ͼ�����ü���
	 * @param listener
	 */
	public void  setLeftOnClickListener(OnClickListener listener){
		imgLeft.setOnClickListener(listener);
	}
	
	/**
	 * Ϊ�ұߵ�ͼ�����ü���
	 * @param listener
	 */
	public void setRightOnClickListener(OnClickListener listener){
		imgRight.setOnClickListener(listener);
	}
	/**
	 * Ϊ�������߶����ü���
	 * @param listener
	 */
	public void setLeftAndRightOnClickListener(OnClickListener listener){
		setLeftOnClickListener(listener);
		setRightOnClickListener(listener);
	}
	
	
	
	
	

}
