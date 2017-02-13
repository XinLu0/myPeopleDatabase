package com.database;

public class MongoDocument {
	private String name;
	private int	age;
	private Sex sex;
	
	public MongoDocument(String n, int a, Sex s)
	{
		name = n;
		age =a;
		sex =s;
	}
	public String getName()
	{
		return name;

	}
	public void setName(String s){
		name =s;
	}
	public int getAge()
	{
		return age;
		
	}
	public void setAge(int a)
	{
		age =a;
	}
	public Sex getSex(){
		return sex;
	}
	public void setSex(Sex s){
		sex =s;
	}
	@Override
	public String toString(){
		return "name: "+name+" age: "+age+" sex: "+(sex==Sex.male?"male":"female");
	}
}
