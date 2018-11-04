package com.al.model;

import java.io.Serializable;
import java.sql.Date;
/**
 * 
 * @author Administrator
 *@Quotation class is a model class used to store accepted quotes in objects
 *quoteId is a primary key for a quotation given by vendor
 *order is a object of class @Order
 *vendor is a object of class @Vendor
 *quoteCost is cost given by vendor to a order 
 *estimatedDeliveryDate is date given vendor to what date he will provide the required order
 *quotedQuantity is quantity of product which vendor can give. It is default set to what is required for client vendor should be able to proviude full quantity
 *
 */
public class Quotation implements Serializable
{
	private int quoteId;
	private Order order;
	private Vendor vendor;
	private int quoteCost;
	private String estimateDeliveryDate;
	private int quotedQuantity;
	
/**
 * Quotation is a parameterized constructor with following parameters
 * @param quoteId
 * @param order
 * @param vendor
 * @param quoteCost
 * @param estimateDeliveryDate
 * @param quotedQuantity
 */
	public Quotation(int quoteId, Order order, Vendor vendor, int quoteCost, String estimateDeliveryDate,
			int quotedQuantity) {
		super();
		this.quoteId = quoteId;
		this.order = order;
		this.vendor = vendor;
		this.quoteCost = quoteCost;
		this.estimateDeliveryDate = estimateDeliveryDate;
		this.quotedQuantity = quotedQuantity;
	}
	/**
	 * Default constructor
	 */
	public Quotation() {
		super();
	}
	public int getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public int getQuoteCost() {
		return quoteCost;
	}
	public void setQuoteCost(int quoteCost) {
		this.quoteCost = quoteCost;
	}
	public String getEstimateDeliveryDate() {
		return estimateDeliveryDate;
	}
	public void setEstimateDeliveryDate(String estimateDeliveryDate) {
		this.estimateDeliveryDate = estimateDeliveryDate;
	}
	public int getQuotedQuantity() {
		return quotedQuantity;
	}
	public void setQuotedQuantity(int quotedQuantity) {
		this.quotedQuantity = quotedQuantity;
	}
	@Override
	public String toString() {
		return "Quotation [quoteId=" + quoteId + ", order=" + order + ", vendor=" + vendor + ", quoteCost=" + quoteCost
				+ ", estimateDeliveryDate=" + estimateDeliveryDate + ", quotedQuantity=" + quotedQuantity + "]";
	}
	
	/**
	 * Checks whether entry is already is present
	 */
	public boolean equals(Object ob)
	{
		try
		{
			Quotation quotation = (Quotation) ob;
			if(this.quoteId == quotation.quoteId)
				return true;
			return false;
			
		}
		catch(Exception ex)
		{
			return super.equals(ob);
		}
	}
}