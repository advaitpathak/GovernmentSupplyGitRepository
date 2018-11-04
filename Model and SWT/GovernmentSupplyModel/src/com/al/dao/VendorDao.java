package com.al.dao;

import java.util.List;
import com.al.model.Vendor;
/**
 * 
 * @author Administrator
 *interface @AcceptedQuotesDao 
 */
public interface VendorDao 
{
	String TABLEVendor ="Vendor",COLvendorId="vendorId",COLvendorName= "vendorName";
	 String COLestablshedDate = "establishedDate",COLvendorRating="vendorRating";
	 String COLvendorPassword="vendorPassword";
	 
	 Vendor getVendor(int vendorId);
	 List<Vendor> getAllVendors();
	 void addVendor(Vendor vendor)throws VendorExistsException;
	 void updateVendor(Vendor vendor);
	 void deleteVendor(Vendor vendor);
	 void updateVendorRating(int vendorRating, Vendor vendor);
}
