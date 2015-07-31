package sef.module11.activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileWriterTest {

	public FileWriterTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String arg[]) {
		File target = null;
		if (arg.length > 0)
			target = new File(".\\bin\\sef\\module11\\activity\\" + arg[0]);
		else
			target = new File(".\\bin\\sef\\module11\\activity\\ActivityOutput.txt");
		System.out.println("Enter a string or type 'END' to exit");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";

		while (!line.equals("END")) {
			try {
				line = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO System.out.println("You typed: " + line);
			if (!line.equals("END")) {
				try {
					PrintWriter writer = new PrintWriter(new FileWriter(target, true));

					writer.println(line);
					writer.flush();
					writer.close();
				} catch (FileNotFoundException fnfe) {
					fnfe.printStackTrace();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}

		System.out.println("GOODBYE!");

	}
}
