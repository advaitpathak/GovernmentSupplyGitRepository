package com.Service;

import java.util.List;

import com.al.dao.VendorDao;
import com.al.dao.VendorDaoImpl;
import com.al.dao.VendorExistsException;
import com.al.model.Vendor;
/**
 * 
 * @author Administrator
 *Interface @VendorServiceInterface
 */
public interface VendorServiceInterface {

	VendorDao vendorDao=new VendorDaoImpl();
	
	Vendor getVendor(int vendorId);
	List<Vendor> getAllVendors();
	void addVendor(Vendor vendor)throws VendorExistsException;
	void updateVendor(Vendor vendor);
	void deleteVendor(Vendor vendor);
	
	boolean vendorEligibility(Vendor vendor);
	List<Vendor> oldestVendor(List<Vendor> allVendorList);
	List<Vendor> bestVendorFirst(List<Vendor> allVendorList);
	void updateVendorRating(int vendorRating, Vendor vendor);
}
