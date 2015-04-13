package com.cx.project.mentaltest.entity;

public class TestItem {
	private  int  testId;
	private String title;
	private  int imageId;
	
	public TestItem() {
	}


	public TestItem(String title, int imageId) {
		this.title = title;
		this.imageId = imageId;
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


	
	
	

}
