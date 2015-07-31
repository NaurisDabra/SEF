package sef.module13.sample;

import java.sql.*;

public class ConnectionSample {

	//	This source code tries to establish a connection to a MySQL server
	//	and displays a message concerning the error if connection fails
	public static void main(String[] args) {

		try {
			String url = "jdbc:mysql://localhost/world";
			String user = "root";
			String pass = "jauki21";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection successfully established! \n");
			
			cn.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
