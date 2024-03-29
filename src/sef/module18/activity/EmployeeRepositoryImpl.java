package sef.module18.activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

	private Connection conn;
	
	public EmployeeRepositoryImpl(Connection conn) throws SQLException {
		this.conn = conn;
		this.conn.setAutoCommit(false);
	}

	/* (non-Javadoc)
	 * @see sef.module18.activity.EmployeeRepository#findEmpoyeeByID(int)
	 */
	@Override
	public Employee findEmployeeByID(int employeeID) throws HRSSystemException{

		Employee result = null;
		try {
			PreparedStatement pStmt = conn
					.prepareStatement("select * from Employee where ID = ?");
			pStmt.setInt(1, employeeID);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				result = new EmployeeImpl(rs.getInt("ID"), 
							rs.getString("FIRSTNAME"),
							rs.getString("LASTNAME"), 
							rs.getInt("LEVEL"));
				
			}
			rs.close();
			pStmt.close();
		} catch (SQLException e) {
			throw new HRSSystemException(HRSSystemException.ERROR_FIND_EMPLOYEE_ID,
					e);
		}
		return result;	
	}

	/* (non-Javadoc)
	 * @see sef.module18.activity.EmployeeRepository#findEmployeeByName(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Employee> findEmployeeByName(String firstname, String lastname) throws HRSSystemException {
		List<Employee> results = new ArrayList<Employee>();
		try {
			PreparedStatement pStmt = conn
					.prepareStatement("select * from Employee where upper(FIRSTNAME) like ? and upper(LASTNAME) like ? " +
									  "order by ID ASC");
			pStmt.setString(1, "%" + firstname.toUpperCase() + "%");
			pStmt.setString(2, "%" + lastname.toUpperCase() + "%");

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				results.add(new EmployeeImpl(rs.getInt("ID"), 
							rs.getString("FIRSTNAME"),
							rs.getString("LASTNAME"), 
							rs.getInt("LEVEL")));
			}
			rs.close();
			pStmt.close();

		} catch (SQLException e) {
			throw new HRSSystemException(HRSSystemException.ERROR_FIND_EMPLOYEE_NAME,
					e);
		}
		return results;
	}

	/* (non-Javadoc)
	 * @see sef.module18.activity.EmployeeRepository#findEmployeeByLevel(int)
	 */
	@Override
	public List<Employee> findEmployeeByLevel(int level) throws HRSSystemException{

		List<Employee> results = new ArrayList<Employee>();
		try {
			PreparedStatement pStmt = conn
					.prepareStatement("select * from Employee where LEVEL = ? order by ID ASC");
			pStmt.setInt(1, level);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				results.add(new EmployeeImpl(rs.getInt("ID"), 
							rs.getString("FIRSTNAME"),
							rs.getString("LASTNAME"), 
							rs.getInt("LEVEL")));
				
			}
			rs.close();
			pStmt.close();

		} catch (SQLException e) {
			throw new HRSSystemException(HRSSystemException.ERROR_FIND_EMPLOYEE_LEVEL,
					e);
		}
		return results;
	}
	
	/* (non-Javadoc)
	 * @see sef.module18.activity.EmployeeRepository#findEmployeeByProject(int)
	 */
	@Override
	public List<Employee> findEmployeeByProject(int projectID) throws HRSSystemException {
		List<Employee> results = new ArrayList<Employee>();
		try {
			PreparedStatement pStmt = conn
					.prepareStatement("select e.* from Employee e join Employee_Project_Map epm on e.ID = epm.EMPLOYEEID " +
									  "where epm.PROJECTID = ? order by e.ID ASC");
			pStmt.setInt(1, projectID);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				results.add(new EmployeeImpl(rs.getInt("ID"), 
							rs.getString("FIRSTNAME"),
							rs.getString("LASTNAME"), 
							rs.getInt("LEVEL")));
			}
			rs.close();
			pStmt.close();

		} catch (SQLException e) {
			throw new HRSSystemException(HRSSystemException.ERROR_FIND_EMPLOYEE_PROJECTID,
					e);
		}
		return results;
	}

	/* (non-Javadoc)
	 * @see sef.module18.activity.EmployeeRepository#insertEmployee(sef.module18.activity.Employee)
	 */	
	@Override
	public int insertEmployee(Employee employee) throws HRSSystemException{
		
		int employeeID = 0;
		
		try {
			PreparedStatement pStmt = conn
					.prepareStatement("insert into Employee (FIRSTNAME, LASTNAME, LEVEL) VALUES (?,?,?)");

			pStmt.setString(1, employee.getFirstName());
			pStmt.setString(2, employee.getLastName());
			pStmt.setInt(3, employee.getLevel());

			int rows = pStmt.executeUpdate();
	
			conn.commit();
			
			if (rows == 1) {
				pStmt = conn
					.prepareStatement("select LAST_INSERT_ID()");

				ResultSet rs = pStmt.executeQuery();
				if (rs.next()){
					employeeID = rs.getInt(1);
				}
				
				rs.close();
			}
			
			pStmt.close();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new HRSSystemException(
						HRSSystemException.ERROR_INSERT_EMPLOYEE, e);

			}
			throw new HRSSystemException(
					HRSSystemException.ERROR_INSERT_EMPLOYEE, ex);
		}

		return employeeID;
	}

	/* (non-Javadoc)
	 * @see sef.module18.activity.EmployeeRepository#updateEmployee(sef.module18.activity.Employee)
	 */	
	@Override
	public boolean updateEmployee(Employee employee) throws HRSSystemException{
		
		try {
			PreparedStatement pStmt = conn
					.prepareStatement("update Employee set FIRSTNAME = ?, LASTNAME = ?, " +
							"LEVEL = ? where ID = ?");

			pStmt.setString(1, employee.getFirstName());
			pStmt.setString(2, employee.getLastName());
			pStmt.setInt(3, employee.getLevel());
			pStmt.setInt(4, employee.getEmployeeID());
			
			int rows = pStmt.executeUpdate();
			conn.commit();
			pStmt.close();
			
			if (rows == 1) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new HRSSystemException(
						HRSSystemException.ERROR_UPDATE_EMPLOYEE, e);

			}
			throw new HRSSystemException(
					HRSSystemException.ERROR_UPDATE_EMPLOYEE, ex);
		}
	}
}
