package com.cx.project.mentaltest.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.cx.project.mentaltest.R;
import com.cx.project.mentaltest.entity.TestItem;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TestItemAdapter extends BaseAdapter {
	
	
	private Context context;
	private List<TestItem> itemList;
	private LayoutInflater inflater;
	private ViewHolder holder;

	public TestItemAdapter(Context context, List<TestItem> itemList) {
		this.itemList = itemList;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return itemList==null?0:itemList.size();
	}

	@Override
	public Object getItem(int position) {
		return itemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view==null){
			view = inflater.inflate(R.layout.adapter_test_item, parent, false);
			
			holder = new ViewHolder();
			holder.txTitle = (TextView) view.findViewById(R.id.tx_title);
			holder.imgTest = (ImageView) view.findViewById(R.id.img_test);
			
			view.setTag(holder);
		}else{
			holder = (ViewHolder) view.getTag();
		}
		
		
		TestItem item = itemList.get(position);
		holder.txTitle.setText(item.getTitle());
//		holder.imgTest.setImageResource(item.getImageId());
		holder.imgTest.setImageBitmap(getImageFromAssetsFile(item.getImagePath()));
		
		
		return view;
	}
	
	private class ViewHolder {
		TextView txTitle;
		ImageView imgTest;
	}
	
	
	 private Bitmap getImageFromAssetsFile(String fileName)  
	  {  
	      Bitmap image = null;  
	      AssetManager am =context.getAssets();  
	      try  
	      {  
	          InputStream is = am.open(fileName);  
	          image = BitmapFactory.decodeStream(is);  
	          is.close();  
	      }  
	      catch (IOException e)  
	      {  
	          e.printStackTrace();  
	      }  
	  
	      return image;  
	  
	  }  

}
