package com.model;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * This is model calss for table quotation in database.
 * It has quoteId as primary key of table quotation
 * @author Administrator
 *
 */
@XmlRootElement
public class Quotation implements Serializable
{
	
	
	private int quoteId;
	private Order order;
	private Vendor vendor;
	private int quoteCost;
	private String estimateDeliveryDate;
	private int quotedQuantity;
	
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
	public Quotation() {
		super();
	}
	
	/**
	 * gets quoteId from table Quotation in database
	 * @see #setQuoteId(int)
	 * @return quoteId
	 */
	public int getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}
	
	/**
	 * gets object of order class in com.model 
	 * @see #setOrder(Order)
	 * @return order
	 */
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	/**
	 * gets object of Vendor class in com.model 
	 * @see #setVendor(Vendor)
	 * @return vendor
	 */
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	/**
	 * gets quoteCost from table Quotation in database
	 * @see #setQuoteCost(int)
	 * @return quoteCost
	 */
	public int getQuoteCost() {
		return quoteCost;
	}
	public void setQuoteCost(int quoteCost) {
		this.quoteCost = quoteCost;
	}
	
	/**
	 * gets EstimateDeliveryDate from table Quotation in database
	 * @see #setEstimateDeliveryDate(String)
	 * @return estimateDeliveryDate
	 */
	public String getEstimateDeliveryDate() {
		return estimateDeliveryDate;
	}
	public void setEstimateDeliveryDate(String estimateDeliveryDate) {
		this.estimateDeliveryDate = estimateDeliveryDate;
	}
	
	/**
	 * gets QuotedQuantity from table Quotation in database
	 * @see #setQuotedQuantity(int)
	 * @return quotedQuantity
	 */
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
}