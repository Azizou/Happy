package com.example.happyme;

import java.util.ArrayList;
import java.util.Set;

import android.R;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

public class Fragment1 extends Fragment {
	SharedPreferences settings = getActivity().getPreferences(0);
	Set <String> names;
	ArrayList<String> namesList;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment1, container, false);
		
		
		getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		
		return rootView;
	}
	
	public void onRadioButtonClicked(View v){
		RadioButton r = (RadioButton)v;
		String feel = r.getText().toString();
		names = settings.getStringSet("mynames", null);
		namesList = new ArrayList<String>(names);
		
		Intent intent = new Intent(getView().getContext(), Story.class);
		
		intent.putExtra("feel",feel);
		intent.putExtra("position", (long)0);
		intent.putStringArrayListExtra("namesList", namesList);

		startActivity(intent);
		
	}
}
