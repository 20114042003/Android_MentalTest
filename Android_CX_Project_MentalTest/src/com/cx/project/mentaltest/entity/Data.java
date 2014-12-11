package com.cx.project.mentaltest.entity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Data {
	private boolean is_recommend; 
	private int choice_type;
	private String _id; 
	private String absolute_url;
	private String brief;  
	private String ad_url1;  
	private String created;
	private int viewnum; 
	private String program; 
	private String ad_title2; 
	private int questionnum; 
	private String cover;
	private int carrynum;
	private boolean require_login;
	private boolean is_completed;
	private long id;
	private int category_id;
	private boolean is_removed;
	private int commentnum;
	private String type; 
	private String ad_title1; 
	private String title; 
	private String ad_url2;
	
	
	
	public Data() {
	}
	public boolean isIs_recommend() {
		return is_recommend;
	}
	public void setIs_recommend(boolean is_recommend) {
		this.is_recommend = is_recommend;
	}
	public int getChoice_type() {
		return choice_type;
	}
	public void setChoice_type(int choice_type) {
		this.choice_type = choice_type;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getAbsolute_url() {
		return absolute_url;
	}
	public void setAbsolute_url(String absolute_url) {
		this.absolute_url = absolute_url;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getAd_url1() {
		return ad_url1;
	}
	public void setAd_url1(String ad_url1) {
		this.ad_url1 = ad_url1;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public int getViewnum() {
		return viewnum;
	}
	public void setViewnum(int viewnum) {
		this.viewnum = viewnum;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getAd_title2() {
		return ad_title2;
	}
	public void setAd_title2(String ad_title2) {
		this.ad_title2 = ad_title2;
	}
	public int getQuestionnum() {
		return questionnum;
	}
	public void setQuestionnum(int questionnum) {
		this.questionnum = questionnum;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public int getCarrynum() {
		return carrynum;
	}
	public void setCarrynum(int carrynum) {
		this.carrynum = carrynum;
	}
	public boolean isRequire_login() {
		return require_login;
	}
	public void setRequire_login(boolean require_login) {
		this.require_login = require_login;
	}
	public boolean isIs_completed() {
		return is_completed;
	}
	public void setIs_completed(boolean is_completed) {
		this.is_completed = is_completed;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public boolean isIs_removed() {
		return is_removed;
	}
	public void setIs_removed(boolean is_removed) {
		this.is_removed = is_removed;
	}
	public int getCommentnum() {
		return commentnum;
	}
	public void setCommentnum(int commentnum) {
		this.commentnum = commentnum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAd_title1() {
		return ad_title1;
	}
	public void setAd_title1(String ad_title1) {
		this.ad_title1 = ad_title1;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAd_url2() {
		return ad_url2;
	}
	public void setAd_url2(String ad_url2) {
		this.ad_url2 = ad_url2;
	} 
	
	
	public static List<Data> parser(JSONArray array){
		List<Data> list=null;
		try {
			if(array!=null&&array.length()>0){
				list = new ArrayList<Data>();
				for(int i=0;i<array.length();i++){
					Data data = new Data();
					JSONObject obj = array.getJSONObject(i);
					data.setIs_recommend(obj.getBoolean("is_recommend"));
					data.setChoice_type(obj.getInt("choice_type"));
					data.set_id(obj.getString("_id"));
					data.setAbsolute_url(obj.getString("absolute_url"));
					data.setBrief(obj.getString("brief"));
					data.setAd_url1(obj.getString("ad_url1"));
					data.setCreated(obj.getString("created"));
					data.setViewnum(obj.getInt("viewnum"));
					data.setProgram(obj.getString("program"));
					data.setAd_title2(obj.getString("ad_title2"));
					data.setQuestionnum(obj.getInt("questionnum"));
					data.setCover(obj.getString("cover"));
					data.setCarrynum(obj.getInt("carrynum"));
					data.setRequire_login(obj.getBoolean("require_login"));
					data.setIs_completed(obj.getBoolean("is_completed"));
					data.setId(obj.getLong("id"));
					data.setCategory_id(obj.getInt("category_id"));
					data.setIs_removed(obj.getBoolean("is_removed"));
					data.setCommentnum(obj.getInt("commentnum"));
					data.setType(obj.getString("type"));
					data.setAd_title1(obj.getString("ad_title1"));
					data.setTitle(obj.getString("title"));
					data.setAd_url2(obj.getString("ad_url2"));
					
					list.add(data);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
		
	}

}
