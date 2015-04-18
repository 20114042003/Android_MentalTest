package com.cx.project.mentaltest.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class User {
	
	private static final String TAG ="User";
	
	public static final String TABLE_NAME ="t_user";
	
	private String email;
	private String password;
	private boolean Login=false;
	
	
	//���캯��
	public User() {
	}
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	//get set
	
	public String getEmail() {
		return email;
	}
	public boolean isLogin() {
		return Login;
	}
	public void setLogin(boolean login) {
		this.Login = login;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	private static final User instance = new User();
	public static User getInstance(){
		return instance;
	}
	public static boolean isLoginState(){
		return instance.isLogin();
	}
	
	
	/**
	 * �����û���
	 * @param db ʵ�ʲ������ݿ�Ķ���,ͨ��getWritableDatabase();
	 */
	public void inserSelf(SQLiteDatabase db){
		String sql = "INSERT INTO "+TABLE_NAME+"(email,password) VALUES(?,?)";
		Object[] bindArgs = {
				this.email,
				this.password,
		};
		db.execSQL(sql, bindArgs);
		db.close();
	}
	
	/**
	 * ����˺����� ����¼
	 * @param db
	 * @return  ��¼�ɹ� true ,��¼ʧ�� false
	 */
	public boolean checkLogin(SQLiteDatabase db){
		boolean retState= false;
		String sql = "SELECT * FROM "+TABLE_NAME+" WHERE email=? AND password=?";
		Cursor cursor= db.rawQuery(sql, new String[]{email,password});
		if(cursor.getCount()!=0){
			retState= true;
		}
		cursor.close();
		return retState;
	}
	
	/**
	 * ����˺��Ƿ����
	 * @param db
	 * @return  �˺Ŵ��ڷ���true,�˺Ų����ڷ���false
	 */
	public boolean checkEmail(SQLiteDatabase db){
		boolean retState= false;
		String sql = "SELECT * FROM "+TABLE_NAME+" WHERE email=?";
		Cursor cursor= db.rawQuery(sql, new String[]{email});
		Log.i(TAG,"Count="+ cursor.getCount());
		if(cursor.getCount()!=0){
			retState= true;
		}
		cursor.close();
		return retState;
		
	}

}
