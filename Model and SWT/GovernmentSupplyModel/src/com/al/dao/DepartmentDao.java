package com.al.dao;

import java.sql.SQLException;
import java.util.List;
import com.al.model.Department;
/**
 * 
 * @author Administrator
 * interface @AcceptedQuotesDao 
 */

public interface DepartmentDao 
{
	String TABLEDepartment ="Department",COLdepartmentId="departmentId",COLdepartmentName= "departmentName";
	Department getDepartment(int departmentId);
	 List<Department> getAllDepartment();
	 void addDepartment(Department department)throws DepartmentExistException;
	 void updateDepartment(Department department) throws SQLException;
	 void deleteDepartment(Department department);


}

