package com.cx.project.mentaltest.utils;


import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class NetUtil {
	/**
	 * 获取JSON字符串
	 * @param uri
	 * @return
	 */
	public static String getJson(String uri) {
		String json = null;
		try {
			HttpEntity httpEntity = getHttpEntity(uri);
			if (httpEntity != null) {
				json = EntityUtils.toString(httpEntity, "UTF-8");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return json;
	}
	
	/**
	 * 获取Bitmap
	 * @param uri
	 * @return
	 */
	public static Bitmap getBitmap(String uri) {
		Bitmap bitmap = null;
		try {

			HttpEntity httpEntity = getHttpEntity(uri);
			if (httpEntity != null) {
				InputStream is = httpEntity.getContent();
				bitmap = BitmapFactory.decodeStream(is);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	
	
	/**
	 * 对于HttpClient获得Get请求 的实例
	 * @param uri
	 * @return
	 */
	private static HttpEntity getHttpEntity(String uri) {
		HttpEntity httpEntity = null;
		
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpParams httpParams = httpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
			String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6";
			HttpProtocolParams. setUserAgent(httpParams, userAgent);

			HttpGet httpGet = new HttpGet(uri);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				httpEntity = httpResponse.getEntity();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return httpEntity;
	}
	
	
	public static String getJSON(String uri){
		String json = null;
		
		//实例化模拟浏览器
		HttpClient httpClient = new DefaultHttpClient();
		//设置参数
		HttpParams httpParams = httpClient.getParams();
		String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6";
        HttpProtocolParams. setUserAgent(httpParams, userAgent);
        //设置超时请求
	    HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
		// 设置GET请求。
	    HttpGet httpGet = new HttpGet(uri);
	    try {
	    	//获取响应
			HttpResponse httpResponse=  httpClient.execute(httpGet);
			if(httpResponse!=null && httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				HttpEntity httpEntity= httpResponse.getEntity();
				if(httpEntity!=null){
					
					json = EntityUtils.toString(httpEntity, "UTF-8");
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return json;
	}
	
	
	
	
}
