package com.Service;

import java.util.ArrayList;
import java.util.List;


import com.al.dao.OrderExistsException;
import com.al.model.Client;
import com.al.model.Order;
import com.al.model.Product;
/**
 * 
 * @author Administrator
 *Class @OrderService provides service layer functionalities for Order Table 
 */
public class OrderService implements OrderServiceInterface {
	/**
	 * Method @Order getOrder(int orderId)
	 * Returns Order with OrderId from Order Table through OrderDao 
	 */
	@Override
	public Order getOrder(int orderId) {
		
		return orderDao.getOrder(orderId);
	}
/**
 * Method @List<Order> getAllOrders()
 * returns List of all Orders from Order Table through OrderDao
 */
	@Override
	public List<Order> getAllOrders() {
		
		return orderDao.getAllOrders();
	}
/**
 * Method @addOrder(Order order)
 * Adds new Order into order Table through OrderDao
 */
	@Override
	public void addOrder(Order order) throws OrderExistsException {
		orderDao.addOrder(order);
		
	}
/**
 * 
 */
	@Override
	public void updateOrder(Order order) {
		orderDao.updateOrder(order);
		
	}
/**
 * Method @deleteOrder(Order order)
 * Deletes Order from Order Table through OrderDao
 */
	@Override
	public void deleteOrder(Order order) {
		orderDao.deleteOrder(order);
		
	}
/**
 * Method @getOrderFromUser(int orderId, int clientId, int productId, int quantityRequired,String orderPlacedDate, String deadline)
 * Accepts parameter to create new Object of Order and Add that object as new Order into Order Table through OrderDao
 */
	@Override
	public void getOrderFromUser(int orderId, int clientId, int productId, int quantityRequired,
			String orderPlacedDate, String deadline) {
		Order order = new Order();
		order.setOrderId(orderId);
		Client client =clientDao.getClient(clientId);
		order.setClient(client);
		Product product = productDao.getProduct(productId);
		order.setProduct(product);
		order.setQuantityRequired(quantityRequired);
		order.setOrderPlacedDate(orderPlacedDate);
		order.setDeadline(deadline);
		
		try {
			orderDao.addOrder(order);
		} catch (OrderExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * Method @List<Order> getOrderOfParticularClient(int clientId)
 * Fetches list if order placed by Government Client with clientId through ClientDao
 * @param clientId
 * @return List of Order
 */
	public List<Order> getOrderOfParticularClient(int clientId)
	{
		List<Order> ClientsOrder=new ArrayList<Order>();
		List<Order> allOrders = getAllOrders();
		for(Order order:allOrders)
		{
			int clientId2 = order.getClient().getClientId();
			if(clientId2==clientId)
				ClientsOrder.add(order);
		}
		return ClientsOrder;
	}
	
}
