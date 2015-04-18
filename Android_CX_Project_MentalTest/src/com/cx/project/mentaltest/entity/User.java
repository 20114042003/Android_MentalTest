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
	
	
	//构造函数
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
	 * 插入用户表
	 * @param db 实际操作数据库的对象,通过getWritableDatabase();
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
	 * 检查账号密码 并登录
	 * @param db
	 * @return  登录成功 true ,登录失败 false
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
	 * 检查账号是否存在
	 * @param db
	 * @return  账号存在返回true,账号不存在返回false
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
