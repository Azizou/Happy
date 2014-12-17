package com.example.happyme;

import java.util.ArrayList;
import java.util.List;

import android.R;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	final Dialog dialog = new Dialog(this);
	ArrayList<String> names;
	SharedPreferences settings = getPreferences(0);

	String[] menutitles;
	TypedArray menuIcons;
	// nav drawer title
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	private ActionBarDrawerToggle mDrawerToggle;
	private List<Item> rowItems;
	private CustomAdapter adapter;
	private PendingIntent pendingIntent;

	long itv;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent myIntent = new Intent(MainActivity.this, Receiver.class);
		pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0,
				myIntent, 0);

		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		if (settings.getLong("itv", -1) == -1) {

			itv = 86400000;

		}

		alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
				itv, itv, pendingIntent);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = this.getTitle();
		menutitles = getResources().getStringArray(R.array.titles);
		menuIcons = getResources().obtainTypedArray(R.array.icons);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.slider_list);

		rowItems = new ArrayList<Item>();

		for (int i = 0; i < menutitles.length; i++) {
			Item items = new Item(menutitles[i], menuIcons.getResourceId(i, -1));
			rowItems.add(items);
		}

		menuIcons.recycle();
		adapter = new CustomAdapter(getApplicationContext(), rowItems);
		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(new SlideitemListener());

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		if (settings.getStringSet("mynames", null) == null) {
			// on first time display view for first nav item
			updateDisplay(2);
		} else {
			updateDisplay(1);
		}
	}

	class SlideitemListener implements ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			updateDisplay(position);
		}

	}

	private void updateDisplay(int position) {
		Fragment fragment = null;
		switch (position) {
		case 1:
			fragment = new Fragment1();
			break;
		case 2:
			fragment = new Fragment2();
			break;
		case 3:
			fragment = new Fragment3();
			break;
		case 4:
			fragment = new Fragment4();
			break;
		case 5:
			showDialog();
			return;

		default:
			break;
		}
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
			// update selected item and title, then close the drawer
			setTitle(menutitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/*** * Called when invalidateOptionsMenu() is triggered */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * * When using the ActionBarDrawerToggle, you must call it during *
	 * onPostCreate() and onConfigurationChanged()...
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onBackPressed() {
		finish();
	}

	public void showDialog() {

		dialog.setContentView(R.layout.custom);
		dialog.setTitle("Feedback");

		// set the custom dialog components - text, image and button
		EditText text = (EditText) dialog.findViewById(R.id.message);

		Button sendButton = (Button) dialog.findViewById(R.id.send);
		// if button is clicked, close the custom dialog

		Button cancelButton = (Button) dialog.findViewById(R.id.cancel);

		dialog.show();
	}

	public void onClick(View v) {
		if (v.getId() == R.id.send) {

			String message = et_body.getText().toString();

			RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
			RadioButton r = ((RadioButton) findViewById(rg
					.getCheckedRadioButtonId()));
			String type = r.getText().toString();

			type += "\n" + message;

			Intent i = new Intent(Intent.ACTION_SEND);
			i.setType("message/rfc822");
			i.putExtra(Intent.EXTRA_EMAIL,
					new String[] { "imaculatemosha@yahoo.com" });
			i.putExtra(Intent.EXTRA_SUBJECT, "Feedback from app");
			i.putExtra(Intent.EXTRA_TEXT, type);

			Toast.makeText(this, "Thank you!", Toast.LENGTH_SHORT).show();
			startActivity(Intent.createChooser(i, "Send mail..."));

		} else if (v.getId() == R.id.cancel) {
			dialog.dismiss();
		}
	}

}
