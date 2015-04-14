package com.cx.project.mentaltest.activity;

import java.util.ArrayList;
import java.util.List;

import com.cx.project.mentaltest.R;
import com.cx.project.mentaltest.adapter.TestItemAdapter;
import com.cx.project.mentaltest.custom.HeadView;
import com.cx.project.mentaltest.entity.TestItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class VocationalListActivity extends Activity implements OnItemClickListener{
	
	
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
		itemList.add(new TestItem("�ǲ��Ƕ�δ��������ã�����ʺ�ʲôְҵ?",R.drawable.vocational_test_1));
		itemList.add(new TestItem("�ǲ��Ƕ�δ��������ã�����ʺ�ʲôְҵ?",R.drawable.vocational_test_1));
		
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
