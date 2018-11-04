package com.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order implements Serializable
{
	private int orderId;
	private Product product;
	private Client client;
	private int quantityRequired;
	private String orderPlacedDate;
	private String deadline;
	
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
	public Order() {
		super();
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
}
