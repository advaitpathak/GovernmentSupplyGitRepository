package com.ws.soap.getOrdersWs;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.Service.OrderService;
import com.al.model.Order;

/*Web service to get all the orders placed by the government client*/
@WebService(name="GetAllOrders", serviceName="GetAllOrders", portName="newPort") 
public class GetAllOrders implements GetAllOrdersInterface
{
	@WebMethod (exclude = false, operationName=operationNameParam)
	public List<Order> getAllOrders() 
	{
		OrderService orderService = new OrderService();
		List<Order> allOrders = orderService.getAllOrders();
		return allOrders;
	}
}
