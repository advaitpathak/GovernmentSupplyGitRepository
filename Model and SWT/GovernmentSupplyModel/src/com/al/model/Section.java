package com.al.model;

import java.io.Serializable;
/**
 * 
 * @author Administrator
 *@Section class is a model class used to store accepted quotes in objects
 *sectionId is a primary key of section
 *section Name stores name of section
 *department is object of class object used to show to which department particular section belongs
 */

public class Section implements Serializable
{
	private int sectionId;
	private String sectionName;
	private Department department;
	
	/**
	 * Section is a parameterized constructor with following parameters
	 * @param sectionId is a unique Key(Primary Key) in datatbase
	 * @param sectionName
	 * @param department is a object of class @Department
	 */
	public Section(int sectionId, String sectionName, Department department)
	{
		super();
		this.sectionId = sectionId;
		this.sectionName = sectionName;
		this.department = department;
	}
	
	/**
	 * Default constructor
	 */
	public Section() 
	{
		super();
	}
	
	public int getSectionId() {
		return sectionId;
	}
	
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Section [sectionId=" + sectionId + ", sectionName=" + sectionName + ", department=" + department + "]";
	}
	
	/**
	 * Checks whether entry is already is present
	 */
	public boolean equals(Object ob)
	{
		try
		{
			Section section = (Section) ob;
			if(this.sectionId == section.sectionId)
				return true;
			return false;
			
		}
		catch(Exception ex)
		{
			return super.equals(ob);
		}
	}
}