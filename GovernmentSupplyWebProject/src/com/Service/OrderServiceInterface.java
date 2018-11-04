package com.Service;

import java.util.List;

import com.al.dao.ClientDao;
import com.al.dao.ClientDaoImpl;
import com.al.dao.OrderDao;
import com.al.dao.OrderDaoImpl;
import com.al.dao.OrderExistsException;
import com.al.dao.ProductDao;
import com.al.dao.ProductDaoImpl;
import com.al.model.Order;
/**
 * 
 * @author Administrator
 *Interface @OrderServiceInterface
 */
public interface OrderServiceInterface {
	
	OrderDao orderDao=new OrderDaoImpl();
	ClientDao clientDao= new ClientDaoImpl();
	ProductDao productDao= new ProductDaoImpl();
	Order getOrder(int orderId);
	List<Order> getAllOrders();
	void addOrder(Order order)throws OrderExistsException;
	void updateOrder(Order order);
	void deleteOrder(Order order);
	void getOrderFromUser(int orderId,int clientId,int productId, int quantityRequired , String  orderPlacedDate,String deadline);
	 
	 
}
