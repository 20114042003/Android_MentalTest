package com.cx.project.mentaltest.activity;

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
 * @description  测试列表类  <br />
 * @author CxiaoX
 *
 * 2015年4月18日上午1:11:57
 */
public class TestListActivity extends Activity implements OnClickListener ,OnItemClickListener{
	
	
	//参数相关
	private int typeId;
	private int testId;
	
	private HeadView headView;
	private DataManagerUtil dataManagerUtil;
	private ListView lvTest;
	private List<TestItem> itemList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dataManagerUtil = new DataManagerUtil(this);
		setContentView(R.layout.activity_test_list);
		initParams();
		initView();
	}
	

	private void initParams() {
		typeId = getIntent().getIntExtra(Extra.TYPE_ID, 1);
		itemList  = TestItem.getItemByTpye(typeId, dataManagerUtil.openDatabase());
		dataManagerUtil.closeDatabase();
		
	}


	private void initView() {
		initHeadView();
		intiListView();
		
	}
	


	

	private void initHeadView() {
		headView = (HeadView) findViewById(R.id.head_view);
		setHeadViewTitle(typeId);
		headView.setLeftOnClickListener(this);
		
	}
	
	private void setHeadViewTitle(int typeId){
		String title ="";
		if(typeId==0) {
			title = "职业测试";
		}else if(typeId == 1){
			title ="性格测试";
		}else if(typeId ==2){
			title = "情感测试";
		}
		headView.setTitle(title);
		
	}
	
	private void intiListView() {
		lvTest = (ListView) findViewById(R.id.lv_test_list);
		lvTest.setAdapter(new TestItemAdapter(this,itemList));
		lvTest.setOnItemClickListener(this);
		
	}



	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case HeadView.LEFT_ICON:
			finish();
			break;

		default:
			break;
		}

	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		TestItem item = itemList.get(position);
		int testId = item.getTestId();
		Intent intent = new Intent(this, SkipTestActivity.class);
		intent.putExtra(Extra.TEST_ID, testId);
		intent.putExtra(Extra.TYPE_ID, typeId);
		startActivity(intent);
	}

}
