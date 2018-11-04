package com.al.model;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 * @Order class is a model class used to store order information in objects 
 * orderId is a primary key associated for particular order
 * product is an object of class @Product
 * client is an Object of class @Client
 * quantityRequired is quantity of product required for a client
 * orderPlace date order placing date which should not be greater than deadline
 * deadline is date which shows end date for receiving order
 */

public class Order implements Serializable
{
	private int orderId;
	private Product product;
	private Client client;
	private int quantityRequired;
	private String orderPlacedDate;
	private String deadline;
	
	/**
	 * Default constructor
	 */
	public Order() {
		super();
	}
	/**
	 * Order is a parameterized constructor with following parameters
	 * @param orderId is a unique id(primary key) in the database
	 * @param client
	 * @param product
	 * @param quantityRequired
	 * @param orderPlacedDate
	 * @param deadline
	 */
	public Order(int orderId, Client client,Product product, int quantityRequired, String orderPlacedDate, String deadline) {
		super();
		
		this.orderId = orderId;
		this.product = product;
		this.client=client;
		this.quantityRequired = quantityRequired;
		this.orderPlacedDate = orderPlacedDate;
		this.deadline = deadline;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getQuantityRequired() {
		return quantityRequired;
	}
	public void setQuantityRequired(int quantityRequired) {
		this.quantityRequired = quantityRequired;
	}
	public String getOrderPlacedDate() {
		return orderPlacedDate;
	}
	public void setOrderPlacedDate(String orderPlacedDate) {
		this.orderPlacedDate = orderPlacedDate;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", client=" + client +", product=" + product + ", quantityRequired=" + quantityRequired
				+ ", orderPlacedDate=" + orderPlacedDate + ", deadline=" + deadline + "]";
	}
	
	/**
	 * Checks whether entry is already is present
	 */
	
	public boolean equals(Object ob)
	{
		try
		{
			Order order = (Order) ob;
			if(this.orderId == order.orderId)
				return true;
			return false;
			
		}
		catch(Exception ex)
		{
			return super.equals(ob);
		}
	}
}
