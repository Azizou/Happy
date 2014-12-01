package com.example.happyme;

import java.util.ArrayList;

import android.app.Application;

public class MyApp extends Application  {
	String name;
	ArrayList<String> names = new ArrayList<String>();
	
	
	
	public void setName(String name){
		this.name=name;
	}
	
	public void addLeader(String name){
		names.add(name);
	}
	
	public void setNames(ArrayList<String> names){
		this.names=names;
		
	}
	
	public String getName(){
		return name;
		
	}
	public ArrayList<String> getLeaders(){
		return names;
	}
}
