package com.cx.project.mentaltest.adapter;

import java.util.List;



import com.cx.project.mentaltest.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * �໬�˵���ʾ�����ListView ʹ�õ�������
 * @author CxiaoX
 *
 * 2014��12��4������10:54:55
 */
public class MenuItemAdapter extends BaseAdapter{
	private Context context;
	private String itemNames[]={"��ҳ","�Ը����","ְҵ����","��в���","������ѯ","��������"};
	
	private ViewHolder viewHolder;
	

	public MenuItemAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return itemNames.length;
	}

	@Override
	public Object getItem(int position) {
		return itemNames[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.list_theme, parent,false);
			
			viewHolder =new ViewHolder();
			viewHolder.txTitle = (TextView) convertView.findViewById(R.id.tx_title);
			
			convertView.setTag(viewHolder);
			
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		String itemNam=  itemNames [position];
		viewHolder.txTitle.setText(itemNam);
		
		return convertView;
	}
	
	
	class ViewHolder{
		public TextView txTitle;
	}

}
