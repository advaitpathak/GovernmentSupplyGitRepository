package com.ws.soap.getOrdersWs;

import java.util.List;

import com.al.model.Order;

public interface GetAllOrdersInterface
{
	String operationNameParam = "getAllOrders";
	public List<Order> getAllOrders();
}
