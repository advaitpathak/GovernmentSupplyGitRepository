package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * class contains the list of vendor and the function that returns the list of Vendors
 * @author Administrator
 *
 */
@XmlRootElement
public class Vendors {

	private List<Vendor> vendorList=new ArrayList<Vendor>();

	public List<Vendor> getVendorList() {
		return vendorList;
	}

	@XmlElement (name="vendor")
	public void setVendorList(List<Vendor> vendorList) {
		this.vendorList = vendorList;
	}
	
	public void add(Vendor vendor)
	{
		if(vendorList == null)
		{
			vendorList = new ArrayList<>();
		}
		vendorList.add(vendor);
	}
	
}
