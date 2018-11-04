package com.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Section implements Serializable
{
	private int sectionId;
	private String sectionName;
	private Department department;
	
	public Section(int sectionId, String sectionName, Department department)
	{
		super();
		this.sectionId = sectionId;
		this.sectionName = sectionName;
		this.department = department;
	}
	
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
}