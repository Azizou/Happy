package com.example.happyme;

public class Model {
	
	String name;
	String filename;
	int iconid;
	boolean selected;
	public Model(String name){
		this.name  = name;
		this.filename=name+".txt";
		
	}
	
	public Model(String name, String filename, int iconid, boolean selected){
		this.name = name;
		this.filename = filename;
		this.iconid  = iconid;
		this.selected = selected;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getIconid() {
		return iconid;
	}
	public void setIconid(int iconid) {
		this.iconid = iconid;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	

}
