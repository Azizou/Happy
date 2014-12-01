package com.example.happyme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Profile extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_start);

	}

	public void toggle_contents(View v) {
		TextView me = (TextView) v;
		toggle(me.getId());

	}

	public void toggle(int id) {
		LinearLayout l = (LinearLayout) findViewById(id);
		if (l.isShown()) {
			AnimFx.slide_up(this, l);
			l.setVisibility(View.GONE);
		} else {
			AnimFx.slide_down(this, l);
			l.setVisibility(View.VISIBLE);

		}

	}
}
