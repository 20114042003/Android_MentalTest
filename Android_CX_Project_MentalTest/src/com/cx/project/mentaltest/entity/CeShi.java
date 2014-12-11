package com.cx.project.mentaltest.entity;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class CeShi {
	private int code;
	private List<Data> data;
	
	
	public CeShi() {
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data = data;
	}
	
	public static CeShi parser(JSONObject obj){
		CeShi ceshi=null;
		if(obj!=null){
			ceshi = new CeShi();
			try {
				ceshi.setCode(obj.getInt("code"));
				ceshi.setData(Data.parser(obj.getJSONArray("data")));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return  ceshi;
	}

}
