package com.al.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.al.connection.DBConnection;
import com.al.model.Vendor;

/**
 * 
 * @author Administrator
 *	@VendorDaoImpl implements VendorDao interface to perform the CRUD operations 
 */
public class VendorDaoImpl implements VendorDao
{
	private static List<Vendor> allVendorList = new ArrayList<Vendor>();
	/**
	 * getVendor() method gives particular Vendor using  specified  Vendor Id
	 */
	@Override
	public Vendor getVendor(int vendorId) 
	{	
		allVendorList = new ArrayList<Vendor>();
		
		try {
			Vendor dummy = new Vendor();
			dummy.setVendorId(vendorId);
			/**
			 * Checks if it is already present in list 
			 * If  it is present in the list then return from the list
			 */
			if(allVendorList.contains(dummy))
			{
				int index = allVendorList.indexOf(dummy);
				return allVendorList.get(index);
			}
			
			Connection connection = DBConnection.getConnection();
			String sqlquery = "select * from "+TABLEVendor+" where "+COLvendorId+"="+"?";
			/**
			 * Create connection with database and  executes query
			 * 	
			*/
			
			
			PreparedStatement ps = connection.prepareStatement(sqlquery);
			ps.setInt(1, vendorId);
			ps.executeQuery();
			ResultSet rs=ps.getResultSet();
			if(rs.next()==false)
			{
				return null;
			}
			
			String vName= rs.getString(COLvendorName);
			int vRating=rs.getInt(COLvendorRating);
			String VestablishDate =rs.getString(COLestablshedDate);
			String vPassword =rs.getString(COLvendorPassword);
			/**
			 * Creates Vendor Object with its required parameter
			 * Add it to the list
			 * return list
			 */
			Vendor vendor = new Vendor(vendorId,vName,vRating,VestablishDate,vPassword);
			allVendorList.add(vendor);
			return vendor;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;				
	}

	/**
	 * getAllVendors() method returns list of all Vendors
	 */
	@Override
	public List<Vendor> getAllVendors() {
		allVendorList = new ArrayList<Vendor>();
		try
		{
			
			
			Connection connection = DBConnection.getConnection();
			String sqlquery = "select * from "+TABLEVendor;
			/**
			 * establish connection and execute the sql query
			 */
			PreparedStatement ps = connection.prepareStatement(sqlquery);
			
			ps.executeQuery();
			ResultSet rs=ps.getResultSet();
			
			while(rs.next())
			{
				int vendorId = rs.getInt(COLvendorId);
				String vName=rs.getString(COLvendorName);
				int vRating=rs.getInt(COLvendorRating);
				String VestablishDate =rs.getString(COLestablshedDate);
				String vPassword =rs.getString(COLvendorPassword);
				/**
				 * Creates Vendor Object with its required parameter
				 * Add it to the list
				 * return list
				 */
				Vendor vendor = new Vendor(vendorId,vName,vRating,VestablishDate,vPassword);
				allVendorList.add(vendor);
				
				
			}
		return allVendorList;
		}
		catch(SQLException ex){System.out.println(ex);}
		
		return  null;
		
	}
	/**
	 * method addVendor adds a Department to the list and database
	 * and returns void 
	 */
	@Override
	public void addVendor(Vendor vendor) throws VendorExistsException {
		if(allVendorList.contains(vendor))
			throw new VendorExistsException();
		try
		{
			Connection con = DBConnection.getConnection();
			String squery = "insert into "+TABLEVendor+" values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(squery);
			ps.setInt(1, vendor.getVendorId());
			ps.setString(2, vendor.getVendorName());
			ps.setInt(3, vendor.getVendorRating());
			ps.setString(4, vendor.getEstablishedDate());
			ps.setString(5, vendor.getVendorPassword());
			ps.executeUpdate();
			allVendorList.add(vendor);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
	}

	/**
	 * updateVendor method  updates particular Vendor's data
	 */
	@Override
	public void updateVendor(Vendor vendor) {
		try
		{
			
			Connection con = DBConnection.getConnection();
			String squery = "update "+TABLEVendor+" set "+COLvendorName+"="+"?"+","+COLvendorRating+"="+"?"+","+COLestablshedDate+"="+"?"+","+COLvendorPassword+"="+"?"+" where "+COLvendorId+"="+"?";
			PreparedStatement pst = con.prepareStatement(squery);
			
			pst.setInt(5, vendor.getVendorId());
			pst.setString(1, vendor.getVendorName());
			pst.setString(3, vendor.getEstablishedDate());
			pst.setInt(2 , vendor.getVendorRating());
			pst.setString(4, vendor.getVendorPassword());
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

	/**
	 * deleteVendor method deletes Vendor with particular Id
	 */
	@Override
	public void deleteVendor(Vendor vendor) {
		try
		{
			
			Connection con = DBConnection.getConnection();
			String squery = "delete from "+TABLEVendor+" where "+COLvendorId+"="+"?";
			PreparedStatement pst = con.prepareStatement(squery);
			pst.setInt(1, vendor.getVendorId());
			pst.executeUpdate();
			allVendorList.remove(vendor);
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println("Vendor Which does not Exist cannot be deleted");
		}
		
		
	}

	@Override
	public void updateVendorRating(int vendorRating, Vendor vendor) {
		try
		{
			Connection con = DBConnection.getConnection();
			String squery = "update "+TABLEVendor+" set "+COLvendorRating+"="+"?"+" where "+COLvendorId+"="+"?";
			PreparedStatement pst = con.prepareStatement(squery);
			
			pst.setInt(1, vendorRating);
			pst.setInt(2 , vendor.getVendorId());
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
}
