package com.example.happyme;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;

import junit.framework.Assert;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Story extends Activity {
	String feel;
	
	TextView text;
	ImageView image;
	Set<String> names;
	Button next;
	long position;
	
	ArrayList<String> namesList;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.story);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		
		
		
		
		Bundle b = getIntent().getExtras();
		feel = b.getString("feel");
		position = b.getLong("position");
		namesList = b.getStringArrayList("namesList");
		

		text = (TextView) findViewById(R.id.id_story);
		image = (ImageView) findViewById(R.id.pic);
		getDetails();
		next = (Button) findViewById(R.id.next_button);
		if (position == namesList.size()-1) {

			next.setText("Return");
			
			
			
		}

	}

	public void getDetails() {
		String name =namesList.get((int)position);
		String filename = name + ".txt";
		String story = readFile(this, filename);
		text.setText(story);

		image.setImageResource(getDrawable(this, "drawable/"+ name+".png"));

	}

	public static int getDrawable(Context context, String name)// method to get
																// id
	{
		Assert.assertNotNull(context);
		Assert.assertNotNull(name);
		return context.getResources().getIdentifier(name, // return id
				"drawable", context.getPackageName());
	}

	public String readFile(Context context, String filename) {
		String ret = "";

		try {

			AssetManager am = context.getAssets();
			InputStream inputStream = am.open(filename);

			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ((receiveString = bufferedReader.readLine()) != null) {

					if (feel.equals(receiveString.trim())) {
						// found
						break;
					}

				}

				while (!(receiveString = bufferedReader.readLine()).equals(":")) {
					stringBuilder.append(receiveString);
				}

				inputStream.close();
				ret = stringBuilder.toString();
			}
		} catch (FileNotFoundException e) {
			Log.e("Story", "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e("Story", "Can not read file: " + e.toString());
		}

		return ret;
	}

	public void next(View v) {
		if (position != namesList.size()-1){
			Intent intent = new Intent(this, Story.class );
			Bundle b = new Bundle();
			b.putString("feel", feel);
			b.putLong("position", position);
			b.putStringArrayList("namesList", namesList);
			startActivity(intent);
			
		}else{
			startActivity(new Intent(this, MainActivity.class));
		}
		
	}
}
