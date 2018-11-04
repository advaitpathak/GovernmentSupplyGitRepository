package com.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vendor implements Serializable
{
	private int vendorId;
	private String vendorName;
	private int vendorRating;
	private String establishedDate;
	private String vendorPassword;
	
	public Vendor(int vendorId, String vendorName, int vendorRating, String establishedDate,String vendorPassword) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorRating = vendorRating;
		this.establishedDate = establishedDate;
		this.vendorPassword = vendorPassword;
	}
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
}
