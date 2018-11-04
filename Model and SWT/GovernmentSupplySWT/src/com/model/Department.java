package com.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Department class is a model class used to store department entries.
 * departmentId is the primary key in table department
 * @author Administrator
 *
 */
@XmlRootElement
public class Department implements Serializable
{
	private int departmentId;
	private String departmentName;
	public Department(int departmentId, String departmentName) 		//Parameterized constructor 
	{
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}
	
	public Department()		//Default constructor
	{
		super();
	}

	/**
	 * Get departmentId from department table in database
	 * @see #setDepartmentId(int)
	 * @return departmentId
	 */
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	
	/**
	 * Get departmentName from department table in database
	 * @see #setDepartmentName(String)
	 * @return departmentName
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