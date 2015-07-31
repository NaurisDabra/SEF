/**
 * 
 */
package sef.module2.sample;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Student
 *
 */
public class SampleClass {

	/**
	 * @param args
	 * @throws Exception 
	
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 Random randomGenerator = new Random();
		 int n=randomGenerator.nextInt(100);
		 
while(true){
	int num = readNum();
		
		 if(num==n)
		 {System.out.println("Jus uzminejat");
		 break;}
		 if(num>n)
			 System.out.println("Ievaditais skaitlis ir mazaks");
		 if(num<n)
			 System.out.println("Ievaditais skaitlis ir lielaks");
		 }
}

public static int readNum() throws IOException {
	int num;
	 
	Scanner in = new Scanner(System.in);
	 System.out.println("Ievadiet numuru");
	 try{
	 num = in.nextInt();}
	 catch(Exception e){
		 System.out.println("Ir jaievada numurs");
		 num = readNum();
	 }
	 if(num<0)
	 {
		 System.out.println("Numuram jabut pozitivam");
		 num =readNum();
	 }

	return num;
	
}

}
