package com.al.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.al.connection.DBConnection;
import com.al.model.Product;
import com.al.model.Section;

/**
 * 
 * @author Administrator
 *	@ProductDaoImpl implements @ProductDao interface to perform the CRUD operations 
 */
public class ProductDaoImpl implements ProductDao 
{
	private static List<Product> allproductList = new ArrayList<Product>();

/**
 * getProduct() method gets product with particular productId
 */
	@Override
	public Product getProduct(int productId) 
	{
		try  
		{
			Product dummy = new Product();
			dummy.setProductId(productId);
			/**
			 * Checks if it is already present in list 
			 * If  it is present in the list then return from the list
			 */
			if(allproductList.contains(dummy))
			{
				int index =allproductList.indexOf(dummy);
				return allproductList.get(index);
			}
		
			Connection connection = DBConnection.getConnection();
			String sqlquery = "Select * from "+TABLEProduct +" where "+ COLproductId+"=?";
			/**
			 * Create connection with database and  executes query
			 * 	
			 */
		
			PreparedStatement pst = connection.prepareStatement(sqlquery);
			pst.setInt(1, productId);
			pst.executeQuery();
			ResultSet rs = pst.getResultSet();
			/**
			 * Until there is data in resultSet
			 */
			if(rs.next()==false)
			{
				return null;
			}
			String product_Name=rs.getString(COLproductName);
			int product_Cost  = rs.getInt(COLproductCost);
			int section_Id = rs.getInt(COLsectionId);
			String product_Details = rs.getString(COLproductDetail);
			/**
			 * Creates Project Object with its required parameter
			 * Add it to the list
			 */
			Product productobj = new Product(productId,product_Name,product_Cost,product_Details,new SectionDaoImpl().getSection(section_Id));
			allproductList.add(productobj);
			return productobj;
			}	 
			catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	/**
	 * getAllProduct() method returns list of all Products
	 */
	@Override
	public List<Product> getAllProduct() 
	{
		allproductList=new ArrayList<Product>();
		try {
			
			
			Connection connection=DBConnection.getConnection();
			String sqlQuery="select * from "+TABLEProduct;
			/**
			 * Create connection with database and  executes query
			 * 	
			*/
			
			PreparedStatement pst=connection.prepareStatement(sqlQuery);
			
			pst.executeQuery();
			ResultSet rs=pst.getResultSet();
			/**
			 * Until there is data in resultSet
			 */
			while(rs.next())
			{
				int product_id=rs.getInt(COLproductId);
				String product_Name = rs.getString(COLproductName);
				int product_cost=rs.getInt(COLproductCost);
				String product_Details = rs.getString(COLproductDetail);
				int section_id=rs.getInt(COLsectionId);
				/**
				 * Creates Product Object with its required parameter
				 * Add it to the list
				 * Return  list
				 */
				Product productObj = new Product(product_id,product_Name,product_cost,product_Details,new SectionDaoImpl().getSection(section_id));
				allproductList.add(productObj);
				
			}
			
			
			
			return  allproductList;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * method addProduct adds a Product to the list and database
	 * and returns void 
	 */
	@Override
	public void addProduct(Product product) throws ProductExistException 
	{
		if(allproductList.contains(product))
		{
			throw new ProductExistException();
		}
		
		try {
		Connection connection = DBConnection.getConnection();
		String sqlquery="insert into " + TABLEProduct+" values(?,?,?,?,?)";
		
		PreparedStatement pst = connection.prepareStatement(sqlquery);
		pst.setInt(1, product.getProductId());
		pst.setString(2, product.getProductName());
		pst.setInt(3, product.getProductCost());
		pst.setInt(4, product.getSection().getSectionId());
		pst.setString(5, product.getProductDetails());
		
		pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	/**
	 * updateProduct method  updates particular Product's data
	 */
	@Override
	public void updateProduct(Product product)
	{
		try
		{
			
			Connection connection=DBConnection.getConnection();
			String sqlQuery="update "+TABLEProduct+" set "+COLproductName+" =?, "+COLproductCost+ "=?,"+COLproductDetail+" =?,"+COLsectionId+" =?"+" where productid="+ product.getProductId() +";";
					
			PreparedStatement pst=connection.prepareStatement(sqlQuery);

			
			pst.setString(1, product.getProductName());
			pst.setInt(2,product.getProductCost());
			pst.setString(3, product.getProductDetails());
			pst.setInt(4,product.getSection().getSectionId());

			
			pst.executeUpdate();
			
			
			
			for(Product p: allproductList)
			{
				if(p.getProductId()==product.getProductId())
				{
					p.setProductId(product.getProductId());
					p.setProductName(product.getProductName());
					p.setProductCost(product.getProductCost());
					p.setProductDetails(product.getProductDetails());
					p.setSection(product.getSection());
				}
			}

		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	/**
	 * deleteDepartment method deletes client with particular Id
	 */
	@Override
	public void deleteProduct(Product product) 
	{
		try {
			Connection connection = DBConnection.getConnection();
			String sqlquery="delete from "+TABLEProduct +" where "+COLproductId +"=?";
			
			PreparedStatement pst = connection.prepareStatement(sqlquery);
			pst.setInt(1, product.getProductId());
			
			
			pst.executeUpdate();
			allproductList.remove(product);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}

}
