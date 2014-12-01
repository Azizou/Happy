package com.example.happyme;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


	public class CustomAdapter extends BaseAdapter {
		 Context context;  List<Item> item;

		 CustomAdapter(Context context, List<Item> item) {
		  this.context = context;
		  this.item = item; 
		} 

		@Override
		 public View getView(int position, View convertView, ViewGroup parent) {
		  if (convertView == null) {     
		       LayoutInflater mInflater = (LayoutInflater) context                     .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);         
		  convertView = mInflater.inflate(R.layout.list_item, null); 
		    }     
		  ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon); 
		     TextView txtTitle = (TextView) convertView.findViewById(R.id.title); 
		    Item row_pos = item.get(position);     

		   // setting the image resource and title 
		     imgIcon.setImageResource(row_pos.getIcon()); 
		     txtTitle.setText(row_pos.getTitle());     
		   return convertView; 
		 }

		 @Override 
		public int getCount() { 
		         return item.size(); 
		         } 

		@Override   
		 public Object getItem(int position) { 
		           return item.get(position); 
		    }

		 @Override  
		  public long getItemId(int position) { 
		         return item.indexOf(getItem(position));
		 }
		}


