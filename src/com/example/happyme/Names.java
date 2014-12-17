package com.example.happyme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

public class Names extends Activity {

	Set<String> names = new TreeSet<String>();
	Set<String> cats = new TreeSet<String>();
	SharedPreferences settings = getPreferences(0);
	SharedPreferences.Editor editor = settings.edit();
	Map<String, Integer> map = new TreeMap<String, Integer>() {
		{
			put("African", R.array.African);
			put("European", R.array.European);
			put("Asian", R.array.Asian);
			put("NorthAmerican", R.array.NorthAmerican);
			put("SouthAmerican", R.array.SouthAmerican);
			put("eng", R.array.eng);
			put("med", R.array.med);
			put("math", R.array.math);
			put("phy", R.array.phy);
			put("chem", R.array.chem);
			put("cs", R.array.cs);
			put("techent", R.array.techent);
			put("re", R.array.re);
			put("food", R.array.food);
			put("clothes", R.array.clothes);
			put("health", R.array.health);
			put("rights", R.array.rights);
			put("edu", R.array.edu);
			put("other", R.array.other);
			put("sports", R.array.sports);
			put("music", R.array.music);
			put("film", R.array.film);
			put("fashion", R.array.fashion);
			put("reality", R.array.reality);
		}
	};
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.names);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		Bundle bundle = getIntent().getExtras();
		cats = new TreeSet<String>(bundle.getStringArrayList("types"));

		lv = (ListView) findViewById(R.id.mycheckboxes);

		// render all the names from cats in unchecked Checkboxes.

		ArrayList<Model> a = getNames(cats);

		try {
			lv.setAdapter(new ListAdapter(this, R.layout.models, a));

		} catch (IOException e) {
			Log.e("Names", "Couldnt get names list");
		}

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		
	}

	public ArrayList<Model> getNames(Set<String> set){
		ArrayList<Model> newList = new ArrayList<Model>();
		
		
		ArrayList<String> myarray;
		Model m;
		for(String s: set){
			myarray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(map.get(s)));
			for(String st: myarray){
				
				m = new Model(st);
				if(settings.getStringSet("mynames", null)!=null){
					names=settings.getStringSet("mynames", null);
							if(names.contains(st)){
								m.setSelected(true);
							}
				}
				
				newList.add(m);
			}
		}
		return newList;
	}

	@Override
	public void onBackPressed() {
		finish();
	}

	private class ListAdapter extends ArrayAdapter<Model> {
		Context context;
		List<Model> models;
		SharedPreferences settings = getPreferences(0);
		Model row_pos;
		SharedPreferences.Editor editor = settings.edit();
		public ListAdapter(Context context, int textViewResourceId,
				ArrayList<Model> list) { // --CloneChangeRequired
			super(context, textViewResourceId, list);
			this.models = list;

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				
				if (settings.getStringSet("mynames", null) == null) {
					LayoutInflater mInflater = (LayoutInflater) context
							.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
					convertView = mInflater.inflate(R.layout.item, null);
				}
				
				
				
				
				CheckBox cb = (CheckBox)convertView.findViewById(R.id.cb);
				
				
				row_pos = models.get(position);
				cb.setText(row_pos.getName());
				if(row_pos.isSelected()){
					cb.setChecked(true);
				}
				
			
				// setting the image resource and title
				
			
				return convertView;
			}
		}

		public void clicked(View v){
			editor.commit();
		
			startActivity(new Intent(context, MainActivity.class));
		}
		
		public void checkChange(View v){
			CheckBox c = (CheckBox)v;
			
			if(c.isChecked()){
				settings.getStringSet("mynames", null).add(row_pos.getName());
			}else{
				settings.getStringSet("mynames", null).remove(row_pos.getName());
			}
			
			
		}
		
		@Override
		public int getCount() {
			return models.size();
		}

		@Override
		public Model getItem(int position) {
			return models.get(position);
		}

		@Override
		public long getItemId(int position) {
			return models.indexOf(getItem(position));
		}
	}

}
