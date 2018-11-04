package com.al.model;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 * @Department class is a model class used to store department information in objects 
 * departmentId is primary key in a database
 * departmentName is name  of database
 */

public class Department implements Serializable
{
	int departmentId;
	String departmentName;
	/**
	 * Department is a parameterized constructor with following parameters
	 * @param departmentId is a unique id(primary key) in the database
	 * @param departmentName
	 */
	public Department(int departmentId, String departmentName) 		
	{
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}
	
	/**
	 * Default constructor
	 */
	public Department()
	{
		super();
	}
/**
 * 
 * @return department Id
 * @see Department#setDepartmentId(int)
 */
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * 
	 * @return department Name 
	 * @see Department#setDepartmentName(String)
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + "]";
	}
	
	/**
	 * Checks whether entry is already is present
	 */
	
	public boolean equals(Object ob)
	{
		try
		{
			Department department = (Department) ob;
			if(this.departmentId == department.departmentId)
				return true;
			return false;
			
		}
		catch(Exception ex)
		{
			return super.equals(ob);
		}
	}
}