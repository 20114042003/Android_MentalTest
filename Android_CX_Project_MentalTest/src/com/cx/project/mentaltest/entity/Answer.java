package com.cx.project.mentaltest.entity;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Answer {
	public int _id;
	public int type_id;
	public int test_id;
	public int answer_id;
	public String answer_str_id;
	public String answer_1;
	public String answer_2;
	public String answer_3;
	
	public static List<Answer> getAnswerBySql(int type_id,int test_id,SQLiteDatabase database){
		List<Answer> answerList = null;
		
		
		String sql = "select * from t_test_answer where type_id=? and test_id=?";
		Cursor cursor =database.rawQuery(sql, new String[]{String.valueOf(type_id),String.valueOf(test_id)});
		if(cursor.getCount()!=0){
			answerList = new ArrayList<Answer>();
			while(cursor.moveToNext()){
				Answer item = new Answer();
				item.answer_1 = cursor.getString(cursor.getColumnIndex("answer_1"));
				item.answer_2 = cursor.getString(cursor.getColumnIndex("answer_2"));
				item.answer_3 = cursor.getString(cursor.getColumnIndex("answer_3"));
				item.answer_str_id =cursor.getString(cursor.getColumnIndex("answer_str_id"));
				item.answer_id = cursor.getInt(cursor.getColumnIndex("answer_id"));
				item.test_id = cursor.getInt(cursor.getColumnIndex("test_id"));
				item.type_id =cursor.getInt(cursor.getColumnIndex("type_id"));
				item._id = cursor.getInt(cursor.getColumnIndex("_id"));
				
				answerList.add(item);
			}
			
		}
		cursor.close();
		return answerList;
	}

}
