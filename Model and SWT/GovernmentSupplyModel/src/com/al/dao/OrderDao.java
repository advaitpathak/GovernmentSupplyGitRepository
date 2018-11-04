package com.al.dao;

import java.util.List;

import com.al.model.Order;
/**
 * 
 * @author Administrator
 *interface @OrdetrDao
 */

public interface OrderDao {

	String TABLEOrder ="OrderDetail",COLorderId="orderId",COLclientId= "clientId";
	 String COLquantityRequired = "quantityRequired",COLorderPlacedDate="orderPlacedDate";
	 String COLdeadline="deadline",COLproductId="productId";
	 
	 Order getOrder(int orderId);
	 List<Order> getAllOrders();
	 void addOrder(Order order)throws OrderExistsException;
	 void updateOrder(Order order);
	 void deleteOrder(Order order);
}
