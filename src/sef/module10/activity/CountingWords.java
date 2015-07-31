package sef.module10.activity;

import java.io.*;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CountingWords {
	static long timea;
	static long timeb;

	public CountingWords() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) {
		
		String filename = "test.txt";
		Path file = Paths.get("./" + filename);
		String text = "";
		
		timea = System.currentTimeMillis();
		
		
		System.out.println("done");
		timeb = System.currentTimeMillis() - timea;
		System.out.println("took " + timeb);
	}

}
