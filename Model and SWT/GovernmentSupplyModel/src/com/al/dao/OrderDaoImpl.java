package com.al.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.al.connection.DBConnection;
import com.al.model.Order;
import com.al.dao.ClientDaoImpl;
/**
 * 
 * @author Administrator
 *	@OrderDaoImpl implements @OrderDao interface to perform the CRUD operations 
 */


public class OrderDaoImpl implements OrderDao 
{
	private static List<Order> allOrderList = new ArrayList<Order>();
	/**
	 * getOrder() method return order with particular order ID
	 */
	@Override
	public Order getOrder(int orderId)
	{	
		allOrderList = new ArrayList<Order>();
		try {
			Order dummy = new Order();
			dummy.setOrderId(orderId);
			if(allOrderList.contains(dummy))
			{
				int index = allOrderList.indexOf(dummy);
				return allOrderList.get(index);
			}
			
			Connection connection = DBConnection.getConnection();
			String sqlquery = "select * from "+TABLEOrder+" where "+COLorderId+"="+"?";
			
			/**
			 * establish connection and execute the sql query
			 */
			
			PreparedStatement ps = connection.prepareStatement(sqlquery);
			ps.setInt(1, orderId);
			ps.executeQuery();
			ResultSet rs=ps.getResultSet();
			/**
			 * Until there is data in resultSet
			 */
			if(rs.next()==false)return null;
			
			int clientId= rs.getInt(COLclientId);
			int quantityRequired =rs.getInt(COLquantityRequired);
			int productId = rs.getInt(COLproductId);
			String orderPlacedDate =rs.getString(COLorderPlacedDate);
			String deadline=rs.getString(COLdeadline);
			/**
			 * create order object with above parameters
			 */
			Order order = new Order(orderId,new ClientDaoImpl().getClient(clientId),new ProductDaoImpl().getProduct(productId),quantityRequired,orderPlacedDate,deadline);
			//AllOrderList.add(order);
			return order;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	/**
	 * getAllOrders() method returns list of Order
	 */
	@Override
	public List<Order> getAllOrders() 
	{
		allOrderList = new ArrayList<Order>();
		try
		{
			
			Connection connection = DBConnection.getConnection();
			String sqlquery = "select * from "+TABLEOrder;
			/**
			 * establish connection and execute the sql query
			 */
			PreparedStatement ps = connection.prepareStatement(sqlquery);
			
			ps.executeQuery();
			ResultSet rs=ps.getResultSet();
			/**
			 * Until there is data in resultSet
			 */
			while(rs.next())
			{
				int orderId=rs.getInt(COLorderId);
				int clientId= rs.getInt(COLclientId);
				int quantityRequired =rs.getInt(COLquantityRequired);
				int productId = rs.getInt(COLproductId);
				String orderPlacedDate =rs.getString(COLorderPlacedDate);
				String deadline=rs.getString(COLdeadline);
				/**
				 * create order object with above parameters
				 * Add it to list
				 */
				Order order = new Order(orderId,new ClientDaoImpl().getClient(clientId),new ProductDaoImpl().getProduct(productId),quantityRequired,orderPlacedDate,deadline);
				if(!allOrderList.contains(order))
				{
					allOrderList.add(order);
				}
				
			}
		return allOrderList;
		}
		catch(SQLException ex){System.out.println(ex);}
		
		return  null;
	}
	
	/**
	 * @addOrder() method adds order by taking argument as object Order
	 * If order already exists throw  OrderExistException
	 */
	@Override
	public void addOrder(Order order) throws OrderExistsException {
		try
		{
			Connection con = DBConnection.getConnection();
			String squery = "insert into "+TABLEOrder+" values(?,?,?,?,?,?)";
			
			/**
			 * establish connection and execute the sql query
			 * and also add it to the list 
			 */
			
			PreparedStatement ps = con.prepareStatement(squery);
			ps.setInt(1, order.getOrderId());
			ps.setInt(2, order.getClient().getClientId());
			ps.setInt(3, order.getProduct().getProductId());
			ps.setInt(4, order.getQuantityRequired());
			ps.setString(5, order.getOrderPlacedDate());
			ps.setString(6, order.getDeadline());
			ps.executeUpdate();
			allOrderList.add(order);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
	}


	/**
	 * UpdateOrder method  updates particular Order data
	 */
	@Override
	public void updateOrder(Order order) {
		try
		{
			
			Connection con = DBConnection.getConnection();
			String squery = "update "+TABLEOrder+" set "+COLclientId+"="+"?"+","+COLproductId+"="+"?"+","+COLquantityRequired+"="+"?"+","+COLorderPlacedDate+"="+"?"+","+COLdeadline+"="+"?"+" where "+COLorderId+"="+"?";
			PreparedStatement pst = con.prepareStatement(squery);
			
			pst.setInt(6, order.getOrderId());
			pst.setInt(1, order.getClient().getClientId());
			pst.setInt(2, order.getProduct().getProductId());
			pst.setInt(3, order.getQuantityRequired());
			pst.setString(4, order.getOrderPlacedDate());
			pst.setString(5, order.getDeadline());
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}

	/**
	 * deleteOrder method deletes order with order Id
	 */
	@Override
	public void deleteOrder(Order order) {
		try
		{
			
			Connection con = DBConnection.getConnection();
			String squery = "delete from "+TABLEOrder+" where "+COLorderId+"="+"?";
			/**
			 * Creates connection and execute sql query
			 */
			PreparedStatement pst = con.prepareStatement(squery);
			pst.setInt(1, order.getOrderId());
			pst.executeUpdate();
			/*
			 * Remove from list 
			 */
			allOrderList.remove(order);
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println("Order Which does not Exist cannot be deleted");
		}
	}

}
