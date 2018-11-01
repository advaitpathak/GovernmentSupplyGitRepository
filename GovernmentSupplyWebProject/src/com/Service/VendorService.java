package com.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.al.dao.VendorDaoImpl;
import com.al.dao.VendorExistsException;
import com.al.model.Quotation;
import com.al.model.Vendor;

class VendorCompareOnEstablishedDate implements Comparator<Vendor>
{
	@Override
	public int compare(Vendor vendor1, Vendor vendor2)
	{
		return (vendor1.getEstablishedDate().compareTo(vendor2.getEstablishedDate()));
	}
}

class VendorCompareOnRating implements Comparator<Vendor>
{
	@Override
	public int compare(Vendor vendor1, Vendor vendor2)
	{
		return (vendor2.getVendorRating()-vendor1.getVendorRating());
	}
}

public class VendorService implements VendorServiceInterface {

	@Override
	public Vendor getVendor(int vendorId) {
		// TODO Auto-generated method stub
		return vendorDao.getVendor(vendorId);
	}

	@Override
	public List<Vendor> getAllVendors() {
		// TODO Auto-generated method stub
		return vendorDao.getAllVendors();
	}

	@Override
	public void addVendor(Vendor vendor) throws VendorExistsException {
		// TODO Auto-generated method stub
		vendorDao.addVendor(vendor);
	}

	@Override
	public void updateVendor(Vendor vendor) {
		// TODO Auto-generated method stub
		vendorDao.updateVendor(vendor);
	}

	@Override
	public void deleteVendor(Vendor vendor) {
		// TODO Auto-generated method stub
		vendorDao.deleteVendor(vendor);
	}

	@Override
	public boolean vendorEligibility(Vendor vendor) {
		// TODO Auto-generated method stub
		
	    long millis=System.currentTimeMillis();  
	    java.sql.Date sqlCurrentDate=new java.sql.Date(millis);  
	    String sqlCurrentDateStr = sqlCurrentDate.toString();

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	    Date establishedDate;
		Date currentDate;
		try
		{
			establishedDate = sdf.parse(vendor.getEstablishedDate());
			currentDate = sdf.parse(sqlCurrentDateStr);
		
	    long diffInMillies = Math.abs(currentDate.getTime() - establishedDate.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    if(diff>=365)
	    {
	    	return true;
	    }
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Vendor> oldestVendor(List<Vendor> allVendorList) {
		allVendorList.sort(new VendorCompareOnEstablishedDate());
		return allVendorList;
	}

	@Override
	public List<Vendor> bestVendorFirst(List<Vendor> allVendorList) {
		allVendorList.sort(new VendorCompareOnRating());
		return allVendorList;
	}

	@Override
	public void updateVendorRating(int vendorRating, Vendor vendor) {
		vendorDao.updateVendorRating(vendorRating, vendor);;
		
		
	}
}