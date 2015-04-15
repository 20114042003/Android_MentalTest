package com.cx.project.mentaltest.entity;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TestItem {
	private int typeId;
	private  int  testId;
	private String title;
	private  int imageId;
	private String imagePath;
	
	public TestItem() {
	}


	public TestItem(String title, int imageId) {
		this.title = title;
		this.imageId = imageId;
	}
	
	
	
	public TestItem(String title, String imagePath) {
		super();
		this.title = title;
		this.imagePath = imagePath;
	}


	public TestItem(int testId, String title, int imageId, String imagePath) {
		this.testId = testId;
		this.title = title;
		this.imageId = imageId;
		this.imagePath = imagePath;
	}


	public int getTestId() {
		return testId;
	}

	

	public int getTypeId() {
		return typeId;
	}


	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}


	public void setTestId(int testId) {
		this.testId = testId;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getImageId() {
		return imageId;
	}


	public void setImageId(int imageId) {
		this.imageId = imageId;
	}


	public static List<TestItem> getItemBySql(int typeId,SQLiteDatabase database){

		List<TestItem> testItemList = null;
		
		
		String sql = "select * from t_test_item where type_id=? ";
		Cursor cursor =database.rawQuery(sql, new String[]{String.valueOf(typeId)});
		if(cursor.getCount()!=0){
			testItemList = new ArrayList<TestItem>();
			while(cursor.moveToNext()){
				TestItem item = new TestItem();
				item.imagePath = cursor.getString(cursor.getColumnIndex("test_img_name"));
				item.title = cursor.getString(cursor.getColumnIndex("test_titel"));
				item.typeId = cursor.getInt(cursor.getColumnIndex("type_id"));
				item.testId = cursor.getInt(cursor.getColumnIndex("test_id"));
				
				testItemList.add(item);
			}
		}
		
		return testItemList;
	}
	
	

}
