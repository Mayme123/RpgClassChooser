package com.mattayme.rpgclasschooser;

public class RpgClass {

	private String mClassTitle;
	private int mDescription;
	private String[] attributes;
	
	public RpgClass(String classTitle,int description){
	    mDescription = description;
		mClassTitle = classTitle;
	}

	public String getClassTitle() {
		return mClassTitle;
	}

	public void setClassTitle(String classTitle) {
		mClassTitle = classTitle;
	}

	public int getDescription() {
		return mDescription;
	}

	public void setDescription(int description) {
		mDescription = description;
	}

	public String[] getAttributes() {
		return attributes;
	}

	public void setAttributes(String[] string) {
		this.attributes = string;
	}
}
