package com.example.happyme;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;





import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment2 extends Fragment {
	EditText t;
	CheckBox Afro, NA, SA, Euro, Aus,Asia, physci, chemsci,medsci, cssci, engsci, eetech, eere, eefood, eeclothes, sehealth, seedu, serights, seother, esports, emusic, efilm , ecomedy, ereality;
	
	Map<Integer, String> Checks = new TreeMap<Integer, String>();
	@SuppressWarnings("serial")
	Map<Integer, Integer> myids = new TreeMap<Integer, Integer>(){{
	       put(R.id.idpol, R.id.pol); put(R.id.sce, R.id.sce);put(R.id.idbe, R.id.be);put(R.id.idse, R.id.se);put(R.id.ident, R.id.ent);}};
	Set<String> Cat= new HashSet<String>();
	String name;
	
	Button p;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View rootView = inflater.inflate(R.layout.profile_start, container, false);
		getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		SharedPreferences settings = getActivity().getPreferences(0);
		SharedPreferences.Editor editor = settings.edit();
		
		t = (EditText)rootView.findViewById(R.id.u_name);
		ViewsCheck(R.id.Afro_pol, Afro);
		ViewsCheck(R.id.NA_pol,NA);
		ViewsCheck(R.id.SA_pol, SA);
		ViewsCheck(R.id.Aus_pol, Aus);
		ViewsCheck(R.id.Euro_pol, Euro);
		ViewsCheck(R.id.Asia_pol, Asia);
		ViewsCheck(R.id.Afro_pol, Afro);
		ViewsCheck(R.id.physci, physci);
		ViewsCheck(R.id.chemsci, chemsci);
		ViewsCheck(R.id.medsci, medsci);
		ViewsCheck(R.id.cssci, cssci);
		ViewsCheck(R.id.engsci, engsci);
		ViewsCheck(R.id.eetech,eetech);
		ViewsCheck(R.id.eere, eere);
		ViewsCheck(R.id.food,eefood);
		ViewsCheck(R.id.clothes,eeclothes);
		ViewsCheck(R.id.sehealth,sehealth);
		ViewsCheck(R.id.seedu,seedu);
		ViewsCheck(R.id.serights,serights);
		ViewsCheck(R.id.seother,seother);
		ViewsCheck(R.id.esports,esports);
		ViewsCheck(R.id.emusic,emusic);
		ViewsCheck(R.id.efilm, efilm);
		ViewsCheck(R.id.ereality,ereality);
		ViewsCheck(R.id.ecomedy,ecomedy);
		
		
		p= (Button)findViewById(R.id.pbutton);
		
	
		
		if(settings.getString("Username", "")==null){
			return rootView;
		}else{
			Cat = settings.getStringSet("cats",Cat);
			name = settings.getString("Username", "");
			
			t.setText(name);
			Set<Integer> ints = Checks.keySet();
			for(Integer i: ints){
				CheckBox c = (CheckBox)getView().findViewById(i);
				c.setChecked(true);
			}
		}
		
		editor.putString("Username",t.getText().toString());
		editor.putStringSet("cats", Cat);
		editor.commit();

		return rootView;
		

	
	}
	
	
	public void submitClicked(View v){
		
		SharedPreferences settings = getActivity().getPreferences(0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("Username",t.getText().toString());
		editor.putStringSet("cats", Cat);
		editor.commit();
		
		Intent myIntent = new Intent(getView().getContext(), Names.class);
		myIntent.putStringArrayListExtra("types", new ArrayList<String>(Checks.values()));

		startActivity(myIntent);
		
	}
	
	public void toggle_contents(View v) {
		TextView me = (TextView) v;
		toggle(myids.get(me.getId()));
		
	}

	public void toggle(int id) {
		LinearLayout l = (LinearLayout) getView().findViewById(id);
		if (l.isShown()) {
			AnimFx.slide_up(getView().getContext(), l);
			l.setVisibility(View.GONE);
		} else {
			AnimFx.slide_down(getView().getContext(), l);
			l.setVisibility(View.VISIBLE);

		}

	}
	
	public void ViewsCheck(int id, CheckBox c){
		c = (CheckBox)getView().findViewById(id);
		if (c.isChecked()){
			Cat.add(c.getText().toString());
			Checks.put(id, c.getText().toString());
		}
		
	}

	

	
}
