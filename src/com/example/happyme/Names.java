package com.example.happyme;

import java.util.ArrayList;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class Names extends Activity {
	
	ArrayList<String> names = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.names);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		Bundle bundle = getIntent().getExtras();
			name = bundle.getString("NAME");
			filename = bundle.getString("FILE");
		}

		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		setContentView(R.layout.names);
	}
	@Override
	public void onBackPressed() {
		finish();
	}
}
