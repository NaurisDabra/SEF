package sef.module14.activity;
class Base {
	    public void method(int i) {
	      System.out.println("Value is " + i);
	    }
	  }
	 public class Sub extends Base {
	   public void method(int j) {
	      System.out.println("This value is " + j);
	    }
	   public void method(String s) {
	     System.out.println("I was passed " + s);
	   }
	   public static void main(String args[]) {
	    Base b1 = new Base();
	     Base b2 = new Sub();
	     b1.method(5);
	     b2.method(6);
	   }
	 }
