package com.al.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.al.connection.DBConnection;

import com.al.model.Section;


/**
 * 
 * @author Administrator
 *	@SectionDaoImpl implements SectionDao interface to perform the CRUD operations 
 */


public class SectionDaoImpl implements SectionDao 
{
	private static List<Section> allsectiontList = new ArrayList<Section>();
	
	/**
	 * getSection() method gives particular Section using  specified  Department Id
	 */
	@Override
	public Section getSection(int sectionId) 
	{	allsectiontList = new ArrayList<Section>();
		try  
		  {
			Section dummy = new Section();
			dummy.setSectionId(sectionId);
			/**
			 * Checks if it is already present in list 
			 * If  it is present in the list then return from the list
			 */
			if(allsectiontList.contains(dummy))
			{
				int index =allsectiontList.indexOf(dummy);
				return allsectiontList.get(index);
			}
			Connection connection = DBConnection.getConnection();
			String sqlquery = "Select * from "+TABLESection +" where "+ COLsectionId+"=?";
			/**
			 * Create connection with database and  executes query
			 * 	
			*/
			
			
			PreparedStatement pst = connection.prepareStatement(sqlquery);
			pst.setInt(1, sectionId);
			pst.executeQuery();
			ResultSet rs = pst.getResultSet();
			/**
			 * Until there is data in resultSet
			 */
			
			if(rs.next()==false)
			{
				return null;
			}
			String section_Name = rs.getString(COLsectionName);
			int department_id = rs.getInt(COLdepartment);
			
			/**
			 * Creates Section Object with its required parameter
			 * Add it to the list
			 * return list
			 */
			
			Section sectionObj = new Section(sectionId,section_Name, new DepartmentDaoImpl().getDepartment(department_id));
			allsectiontList.add(sectionObj);
			return sectionObj;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		return null;
	}

	/**
	 * getAllSection() method returns list of all Departments
	 */
	@Override
	public List<Section> getAllSection() 
	{
		allsectiontList = new ArrayList<Section>();
		try 
		{
			Connection connection = DBConnection.getConnection();
			String sqlquery =  "Select * from "+TABLESection;
			
			/**
			 * Create connection with database and  executes query
			 * 	
			*/
			
			PreparedStatement pst = connection.prepareStatement(sqlquery);
			
			pst.executeQuery();
			ResultSet rs = pst.getResultSet();
			/**
			 * Until there is data in resultSet
			 */
			while(rs.next())
			{
				int section_Id= rs.getInt(COLsectionId);
				
				String section_Name=rs.getString(COLsectionName);
				int department_id = rs.getInt(COLdepartment);
				/**
				 * Creates Section Object with its required parameter
				 * Add it to the list
				 */
				Section sectionObj = new Section(section_Id,section_Name, new DepartmentDaoImpl().getDepartment(department_id));
				allsectiontList.add(sectionObj);
				
			}
			return allsectiontList;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return null;
	}
	/**
	 * method addSection adds a Section to the list and database
	 * and returns void 
	 */
	@Override
	public void addSection(Section section) throws SectionExistException 
	{
		if(allsectiontList.contains(section))
		{
			throw new SectionExistException();
		}
		
		try {
		Connection connection = DBConnection.getConnection();
		String sqlquery="insert into " + TABLESection+" values(?,?,?)";
		
		PreparedStatement pst = connection.prepareStatement(sqlquery);
		pst.setInt(1, section.getSectionId());
		pst.setString(2,section.getSectionName());
		pst.setInt(3,section.getDepartment().getDepartmentId());
		
		pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateSection(Section section) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * deleteSection method deletes section with particular Id
	 */
	@Override
	public void deleteSection(Section section) 
	{
		try {
			Connection connection = DBConnection.getConnection();
			String sqlquery="delete from "+TABLESection +" where "+COLsectionId +"=?";
			
			PreparedStatement pst = connection.prepareStatement(sqlquery);
			pst.setInt(1, section.getSectionId());
			
			
			pst.executeUpdate();
			allsectiontList.remove(section);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}

}
