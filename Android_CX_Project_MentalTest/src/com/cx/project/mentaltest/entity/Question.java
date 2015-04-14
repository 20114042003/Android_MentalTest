package com.cx.project.mentaltest.entity;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Question {
	public int test_id;
	public int type_id;
	public int question_id;
	public String question_content;
	public String answer_a;
	public String answer_b;
	public String answer_c;
	public String answer_d;
	public String value_a;
	public String value_b;
	public String value_c;
	public String value_d;
	public String your_answer;
	public String your_value;
	
	public static List<Question> getQuestionBySql(int type_id,int test_id,SQLiteDatabase database){
		List<Question> questionList = null;
		
		
		String sql = "select * from t_test_question where type_id=? and test_id=?";
		Cursor cursor =database.rawQuery(sql, new String[]{String.valueOf(type_id),String.valueOf(test_id)});
		if(cursor.getCount()!=0){
			questionList = new ArrayList<Question>();
			while(cursor.moveToNext()){
				Question item = new Question();
				item.answer_a = cursor.getString(cursor.getColumnIndex("answer_a"));
				item.answer_b = cursor.getString(cursor.getColumnIndex("answer_b"));
				item.answer_c = cursor.getString(cursor.getColumnIndex("answer_c"));
				item.answer_d = cursor.getString(cursor.getColumnIndex("answer_d"));
				item.question_content =cursor.getString(cursor.getColumnIndex("question_content"));
				item.question_id = cursor.getInt(cursor.getColumnIndex("question_id"));
				item.test_id = cursor.getInt(cursor.getColumnIndex("test_id"));
				item.type_id =cursor.getInt(cursor.getColumnIndex("type_id"));
				item.value_a = cursor.getString(cursor.getColumnIndex("value_a"));
				item.value_b = cursor.getString(cursor.getColumnIndex("value_b"));
				item.value_c = cursor.getString(cursor.getColumnIndex("value_c"));
				item.value_d = cursor.getString(cursor.getColumnIndex("value_d"));
				item.your_answer = cursor.getString(cursor.getColumnIndex("your_answer"));
				item.your_value = cursor.getString(cursor.getColumnIndex("your_value"));
				
				
				questionList.add(item);
			}
			
		}
		cursor.close();
		return questionList;
	}
	
	

}
