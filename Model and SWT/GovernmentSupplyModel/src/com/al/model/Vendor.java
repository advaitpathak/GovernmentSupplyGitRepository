package com.al.model;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 *@Vendor class is a model class used to store accepted quotes in objects
 *vendorId is primary key used to refer particular vendor
 *vendorName is name associated with vendor
 *vendorRaing used for rating a vendor out of 5.Only Admin can set Ratings of vendor
 *establisheddate is date date of vendor when a vendor id established is used to check whether vendor is eligilble to quote for order.
 *vendorPassword is password for a vendor used to login in to system
 */
public class Vendor implements Serializable
{
	private int vendorId;
	private String vendorName;
	private int vendorRating;
	private String establishedDate;
	private String vendorPassword;
	
	/**
	 * Vendor is a parameterized constructor with following parameters
	 * @param vendorId is a unique id(primary key) in the database
	 * @param vendorName
	 * @param vendorRating
	 * @param establishedDate
	 * @param vendorPassword
	 */
	public Vendor(int vendorId, String vendorName, int vendorRating, String establishedDate,String vendorPassword) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorRating = vendorRating;
		this.establishedDate = establishedDate;
		this.vendorPassword = vendorPassword;
	}
	
	/**
	 * Default Constructor
	 */
	public Vendor()
	{
		super();
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public int getVendorRating() {
		return vendorRating;
	}
	public void setVendorRating(int vendorRating) {
		this.vendorRating = vendorRating;
	}
	public String getEstablishedDate() {
		return establishedDate;
	}
	public void setEstablishedDate(String establishedDate) {
		this.establishedDate = establishedDate;
	}
	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", vendorName=" + vendorName + ", vendorRating=" + vendorRating
				+ ", establishedDate=" + establishedDate + ",vendorPassword=" +vendorPassword+"]";
	}
	public String getVendorPassword() {
		return vendorPassword;
	}
	public void setVendorPassword(String vendorPassword) {
		this.vendorPassword = vendorPassword;
	}
	
	/**
	 * Checks whether entry is already is present
	 */
	
	public boolean equals(Object ob)
	{
		try
		{
			Vendor vendor = (Vendor) ob;
			if(this.vendorId == vendor.vendorId)
				return true;
			return false;
			
		}
		catch(Exception ex)
		{
			return super.equals(ob);
		}
	}
}
