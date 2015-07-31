package sef.module13.activity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO{

	private Connection conn;

	public AccountDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public List<Account> findAccount(String firstName, String lastName)
			throws AccountDAOException {
		
		List<Account> result = new ArrayList<Account>();
		try{
			String url = "jdbc:mysql://localhost/activity";
			String user = "root";
			String pass = "abcd1234";
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection successfully established!");
			
			PreparedStatement pStmt = cn.prepareStatement("select * from account where first_name like ? and last_name like ? ");
			System.out.println(firstName +" "+lastName);
			//	Change parameter to any keyword desired, if a record
			// 	matches the query then it will be displayed
			pStmt.setString(1,firstName+"%");
			pStmt.setString(2,lastName+"%");
			
			ResultSet rs = pStmt.executeQuery();
			
			System.out.println("Printing Query results:");
			while(rs.next()){System.out.println("test");
				result.add(new AccountImpl(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4)));
				System.out.println(rs.getString(1) + " - " + 
							rs.getString(2) + " - " + 
							rs.getString(3) + " - " + 
							rs.getString(4));
					
			}
			
			cn.close();
			
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	public Account findAccount(int id) throws AccountDAOException {
		String url = "jdbc:mysql://localhost/activity";
		String user = "root";
		String pass = "abcd1234";
		Account result=null;
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection(url, user, pass);
		System.out.println("Connection successfully established!");
		
		PreparedStatement pStmt = cn.prepareStatement("select * from account where id=? ");
		
		//	Change parameter to any keyword desired, if a record
		// 	matches the query then it will be displayed
		pStmt.setInt(1, id);
		
		ResultSet rs = pStmt.executeQuery();
		
		System.out.println("Printing Query results:");
		while(rs.next()){
			
			result=new AccountImpl(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4));
		}
		
		cn.close();
		
	}catch (Exception e){
		System.out.println(e.getMessage());
	}
		
		return result;
	}


	public boolean insertAccount(String firstName, String lastName, String email)
			throws AccountDAOException {
		
		String url = "jdbc:mysql://localhost/activity";
		String user = "root";
		String pass = "abcd1234";
		boolean result=false;
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection(url, user, pass);
		System.out.println("Connection successfully established!");
		//cn.setAutoCommit(false);
		PreparedStatement pStmt 
		= cn.prepareStatement("insert into account (first_name, last_name, e_mail) VALUES (?,?,?)");
	
	//	Change parameter to any keyword desired, if a record
	// 	matches the query then it will be displayed
	pStmt.setString(1,firstName);
	pStmt.setString(2,lastName);
	pStmt.setString(3,email);
	
	int rows = pStmt.executeUpdate();
	
	if(rows>0)
		result=true;
		}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
		
		return result;
	}

}
