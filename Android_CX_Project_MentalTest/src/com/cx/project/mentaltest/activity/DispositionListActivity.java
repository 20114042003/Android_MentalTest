package com.cx.project.mentaltest.activity;

import java.util.ArrayList;
import java.util.List;

import com.cx.project.mentaltest.Extra;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 
 * @description  性格测试列表  <br />
 * @author CxiaoX
 *
 * 2015年4月18日上午1:12:55
 */
public class DispositionListActivity extends Activity implements OnItemClickListener{
	
	private HeadView headView;
	private DataManagerUtil dataManagerUtil;
	private ListView lvDisposition;
	private List<TestItem> itemList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dataManagerUtil = new DataManagerUtil(this);
		setContentView(R.layout.activity_disposition);
		initParams();
		initView();
	}

	private void initParams() {

		itemList = new ArrayList<TestItem>();
		itemList = TestItem.getItemBySql(1,dataManagerUtil.openDatabase());
		dataManagerUtil.closeDatabase();		
	}

	private void initView() {
		headView = (HeadView) findViewById(R.id.head_view);
		headView.setLeftOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		lvDisposition = (ListView) findViewById(R.id.lv_disposition);
		lvDisposition.setAdapter(new TestItemAdapter(this,itemList));
		
		lvDisposition.setOnItemClickListener(this);
		
	}
	
	

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		TestItem item = itemList.get(position);
		int typeId =   item.getTypeId();
		int testId = item.getTestId();
		Intent intent=null;
		if(position==0){
			intent = new Intent(this, SkipTestActivity.class);
		}else if(position==2){
			intent =new Intent(this, OneSelectTestActivity.class);
		}else{
			intent = new Intent(this, SkipTestActivity.class);
		}
		
		intent.putExtra(Extra.TYPE_ID, typeId);
		intent.putExtra(Extra.TEST_ID, testId);
		startActivity(intent);
	}

}
