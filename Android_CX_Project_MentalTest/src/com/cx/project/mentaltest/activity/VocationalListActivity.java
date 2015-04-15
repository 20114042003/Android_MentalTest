package com.cx.project.mentaltest.activity;

import java.util.ArrayList;
import java.util.List;

import com.cx.project.mentaltest.R;
import com.cx.project.mentaltest.adapter.TestItemAdapter;
import com.cx.project.mentaltest.custom.HeadView;
import com.cx.project.mentaltest.entity.TestItem;
import com.cx.project.mentaltest.utils.DataManagerUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class VocationalListActivity extends Activity implements OnItemClickListener{
	
	private DataManagerUtil dataManagerUtil;
	private ListView lvVocational;
	private List<TestItem> itemList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dataManagerUtil = new DataManagerUtil(this);
		setContentView(R.layout.activty_vocational);
		initParams();
		initView();
	}

	private void initParams() {
		itemList = new ArrayList<TestItem>();
		
		itemList = TestItem.getItemBySql(0,dataManagerUtil.openDatabase());
		dataManagerUtil.closeDatabase();
//		getResources().getdr
//		itemList.add(new TestItem("是不是对未来充满迷茫，你适合什么职业?",R.drawable.vocational_test_1));
//		itemList.add(new TestItem("你的心，有多累？",R.drawable.vocational_test_2));
//		itemList.add(new TestItem("你还会再辞职多少次？",R.drawable.vocational_test_3));
//		itemList.add(new TestItem("是不是对未来充满迷茫，你适合什么职业?","vocational_test_1.png"));
//		itemList.add(new TestItem("你的心，有多累？","vocational_test_2.jpeg"));
//		itemList.add(new TestItem("你还会再辞职多少次？","vocational_test_3.jpg"));
		
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
		lvVocational.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		switch (position) {
		case 0:
			startActivity(new Intent(this,VocationalTestActivity.class ));
			break;

		default:
			break;
		}
	}
	
	

}
