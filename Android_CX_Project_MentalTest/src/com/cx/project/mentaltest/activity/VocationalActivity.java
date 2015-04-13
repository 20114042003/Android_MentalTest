package com.cx.project.mentaltest.activity;

import java.util.ArrayList;
import java.util.List;

import com.cx.project.mentaltest.R;
import com.cx.project.mentaltest.adapter.TestItemAdapter;
import com.cx.project.mentaltest.custom.HeadView;
import com.cx.project.mentaltest.entity.TestItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class VocationalActivity extends Activity{
	
	
	private ListView lvVocational;
	private List<TestItem> itemList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activty_vocational);
		initParams();
		initView();
	}

	private void initParams() {
		itemList = new ArrayList<TestItem>();
		itemList.add(new TestItem("是不是对未来充满迷茫，你适合什么职业?",R.drawable.vocational_test_1));
		itemList.add(new TestItem("是不是对未来充满迷茫，你适合什么职业?",R.drawable.vocational_test_1));
		
	}

	private void initView() {
		
		initHeadView();
		initTestList();
	}


	

	private void initHeadView() {
		HeadView headView= (HeadView)findViewById(R.id.head_view);
		headView.setLeftOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
	}
	
	private void initTestList() {
		lvVocational = (ListView) findViewById(R.id.lv_vocation);
		lvVocational.setAdapter(new TestItemAdapter(this, itemList));
	}
	
	

}
