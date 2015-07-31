package sef.module18.activity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import junit.framework.TestCase;
import sef.module18.sample.MyCalculator;

public class EmployeeRepositoryTest extends TestCase {

	private EmployeeRepositoryImpl Impl;
	Connection cn;

	protected void setUp() throws Exception {
		super.setUp();
		String url = "jdbc:mysql://localhost/activity";
		String user = "root";
		String pass = "abcd1234";
		Class.forName("com.mysql.jdbc.Driver");
		cn = DriverManager.getConnection(url, user, pass);
		Impl = new EmployeeRepositoryImpl(cn);

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testEmployeeRepositoryImpl() throws SQLException {
		assertEquals(Impl.getClass(), (new EmployeeRepositoryImpl(cn)).getClass());
	}

	public void testFindEmployeeByID() throws HRSSystemException {
		assertEquals(2, (Impl.findEmployeeByID(2)).getEmployeeID());
		assertNull(Impl.findEmployeeByID(-1));
	}

	public void testFindEmployeeByName() throws HRSSystemException {
		assertEquals(2, (Impl.findEmployeeByName("J", "Doe")).size());
		assertEquals(1, (Impl.findEmployeeByName("John", "Doe")).size());
		assertEquals(0, (Impl.findEmployeeByName("Z", "Doe")).size());
	}

	public void testFindEmployeeByLevel() throws HRSSystemException {
		assertEquals(1, (Impl.findEmployeeByLevel(4)).size());
		assertEquals(2, (Impl.findEmployeeByLevel(1)).size());
		assertEquals(0, (Impl.findEmployeeByLevel(-7)).size());
	}

	public void testFindEmployeeByProject() throws HRSSystemException {
		assertEquals(1, (Impl.findEmployeeByProject(1)).size());
		assertEquals(0, (Impl.findEmployeeByProject(-5)).size());
		assertEquals(2, (Impl.findEmployeeByProject(3)).size());
	}

	public void testInsertEmployee() throws HRSSystemException {

		assertTrue((Impl.insertEmployee(new EmployeeImpl(5, "Tommy", "Bastard", 5)) != 0));
		try {
			assertTrue((Impl.insertEmployee(new EmployeeImpl(5, "Tommy", "Bastard", -555)) == 0));
			fail();
		} catch (HRSSystemException e) {
		}
	}

	public void testUpdateEmployee() throws HRSSystemException {
		assertTrue(Impl.updateEmployee(new EmployeeImpl(4, "Nauris", "Dabra", 4)));
		assertFalse(Impl.updateEmployee(new EmployeeImpl(-74, "Nauris", "Dabra", 5)));

	}

}
