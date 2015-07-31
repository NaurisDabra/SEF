package sef.module18.activity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import junit.framework.TestCase;

public class ProjectRepositoryTest extends TestCase {
	private Connection cn;
	private ProjectRepositoryImpl Impl;
	protected void setUp() throws Exception {
		super.setUp();
		String url = "jdbc:mysql://localhost/activity";
		String user = "root";
		String pass = "abcd1234";
		Class.forName("com.mysql.jdbc.Driver");
		cn = DriverManager.getConnection(url, user, pass);
		Impl = new ProjectRepositoryImpl(cn);
		
	}
	public void testProjectRepositoryImpl() throws SQLException {
		assertEquals(Impl.getClass(), new ProjectRepositoryImpl(cn).getClass());
	}
	
	public void testFindProjectByID() throws HRSSystemException {
		assertEquals(1, (Impl.findProjectByID(1)).getProjectID());
		assertNull(Impl.findProjectByID(-1));
	}

	public void testFindProjectByName() throws HRSSystemException {
		assertEquals(4, Impl.findProjectByName("System").size());
		assertEquals(0, Impl.findProjectByName("Syste1231234m").size());
		assertEquals(1, Impl.findProjectByName("Real Estate Search").size());
	}

	public void testFindProjectByEmployee() throws HRSSystemException {
		assertEquals(0, Impl.findProjectByEmployee(-5).size());
		assertEquals(1, Impl.findProjectByEmployee(1).size());
	}

	public void testInsertProject() throws HRSSystemException {
	   assertTrue(Impl.insertProject(new ProjectImpl(2, "Labaka sistema", "Tas ir joks"))!=0);
	   try{
		   assertTrue(Impl.insertProject(new ProjectImpl(2, "Labaka sistemagffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", "Tas ir joks"))==0);
	   fail();
	   }catch(HRSSystemException ex){}
	}

	public void testUpdateProject() throws HRSSystemException {
		assertTrue(Impl.updateProject(new ProjectImpl(2, "Real time stealing system", "Tas ir joks")));
		assertFalse(Impl.updateProject(new ProjectImpl(-2, "Real time stealing system", "Tas ir joks")));
	}

}
