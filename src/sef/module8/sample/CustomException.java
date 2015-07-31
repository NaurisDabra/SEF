package sef.module8.sample;

public class CustomException extends Exception{
private int age;
	public CustomException(int age) {
		this.age=age;// TODO Auto-generated constructor stub
	}

	//overriding the toString method of Exception
	public String toString()
	{
		return "This is my Custom Exception, age " +age;
	}
}
