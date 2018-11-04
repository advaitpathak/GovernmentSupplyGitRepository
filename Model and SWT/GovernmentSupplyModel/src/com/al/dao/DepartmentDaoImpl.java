package com.al.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.al.connection.DBConnection;
import com.al.model.Department;
/**
 * 
 * @author Administrator
 *	@DepartmentDaoImpl implements @DepartmentDao interface to perform the CRUD operations 
 */
public class DepartmentDaoImpl implements DepartmentDao 
{
	private static List<Department> alldepartmentList = new ArrayList<Department>();
	/**
	 * getDepartment() method gives particular Department using  specified  Department Id
	 */
	@Override
	public Department getDepartment(int departmentId) 
	{
		alldepartmentList = new ArrayList<Department>();
		try  
		  {
			Department dummy = new Department();
			dummy.setDepartmentId(departmentId);
			/**
			 * Checks if it is already present in list 
			 * If  it is present in the list then return from the list
			 */
			if(alldepartmentList.contains(dummy))
			{
				int index =alldepartmentList.indexOf(dummy);
				return alldepartmentList.get(index);
			}
			Connection connection = DBConnection.getConnection();
			String sqlquery = "Select * from "+TABLEDepartment +" where "+ COLdepartmentId+"=?";
			
			/**
			 * Create connection with database and  executes query
			 * 	
			*/
			
			PreparedStatement pst = connection.prepareStatement(sqlquery);
			pst.setInt(1, departmentId);
			pst.executeQuery();
			ResultSet rs = pst.getResultSet();
			
			/**
			 * Until there is data in resultSet
			 */
			if(rs.next()==false)
			{
				return null;
			}
			String department_Name=rs.getString(COLdepartmentName);
			/**
			 * Creates Department Object with its required parameter
			 * Add it to the list
			 */
			
			Department departmentObj = new Department(departmentId,department_Name);
			alldepartmentList.add(departmentObj);
			return departmentObj;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}

	/**
	 * getAllDepartment() method returns list of all Departments
	 */
	@Override
	public List<Department> getAllDepartment() 
	{
		alldepartmentList = new ArrayList<Department>();
		try 
		{
			Connection connection = DBConnection.getConnection();
			String sqlquery =  "Select * from "+TABLEDepartment;
			
			/**
			 * establish connection and execute the sql query
			 */
			
			PreparedStatement pst = connection.prepareStatement(sqlquery);
			
			pst.executeQuery();
			ResultSet rs = pst.getResultSet();
			while(rs.next())
			{
				int department_Id= rs.getInt(COLdepartmentId);
				
				String department_Name=rs.getString(COLdepartmentName);
				/**
				 * Creates Department Object with its required parameter
				 * Add it to the list
				 */
				Department departmentObj = new Department(department_Id,department_Name);
				alldepartmentList.add(departmentObj);
				
			}
			return alldepartmentList;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
	/**
	 * method addDepartmente adds a Department to the list and database
	 * and returns void 
	 */
	@Override
	public void addDepartment(Department department) throws DepartmentExistException 
	{
		if(alldepartmentList.contains(department))
		{
			throw new DepartmentExistException();
		}
		
		try {
		Connection connection = DBConnection.getConnection();
		String sqlquery="insert into " + TABLEDepartment+" values(?,?)";
		
		PreparedStatement pst = connection.prepareStatement(sqlquery);
		pst.setInt(1, department.getDepartmentId());
		pst.setString(2, department.getDepartmentName());
		
		pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * updateDepartment method  updates particular Department's data
	 */
	@Override
	public void updateDepartment(Department department) throws SQLException {
		Connection con = DBConnection.getConnection();
		String squery = "update "+TABLEDepartment+" set "+COLdepartmentName+"="+"?"+" where "+COLdepartmentId+"="+"?";
		PreparedStatement pst;
		
			pst = con.prepareStatement(squery);
			pst.setInt(2, department.getDepartmentId());
			pst.setString(1, department.getDepartmentName());
			pst.executeUpdate();
		
		
		

	}

	/**
	 * deleteDepartment method deletes Department with particular Id
	 */
	@Override
	public void deleteDepartment(Department department) 
	{
		try {
			Connection connection = DBConnection.getConnection();
			String sqlquery="delete from "+TABLEDepartment +" where "+COLdepartmentId +"=?";
			
			PreparedStatement pst = connection.prepareStatement(sqlquery);
			pst.setInt(1, department.getDepartmentId());
			
			
			pst.executeUpdate();
			alldepartmentList.remove(department);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
