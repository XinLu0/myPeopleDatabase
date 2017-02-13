package com.database;

public enum Sex{
	male,female;
	@Override
	public String toString()
	{
		if(this == male) return "male";
		else return "female";
	}
}
