package com.al.model;

/**
 * 
 * @author Administrator
 * @AcceptedQuotes class is a model class used to store accepted quotes in objects Id
 * Having members  acceptedQupteId is a primary key in a database
 * order is an object of class @Order 
 * vendor is an object of class @vendor
 * quantity is quantity that is to be given by vendor and it is decided through rules
 * total cost gives over all cost to be paid for the accepted quotes
 */
public class AcceptedQuotes
{
	private int acceptedQuoteId;
	private Order order;
	private Vendor vendor;
	private int quantity;
	private int totalCost;
	
	/**
	 * Default constructor
	 */
	public AcceptedQuotes() 
	{
		super();
	}
	
	/**
	 * AcceptedQuotes is a parameterized constructor with following parameters
	 * @param acceptedQuoteId is a unique id(primary key) in the database
	 * @param order is a object of class @Order
	 * @param vendor is a object of class @Vendor
	 * @param quantity of order
	 * @param totalCost of order
	 */
	public AcceptedQuotes(int acceptedQuoteId, Order order, Vendor vendor, int quantity, int totalCost) 
	{
		super();
		this.acceptedQuoteId = acceptedQuoteId;
		this.order = order;
		this.vendor = vendor;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}
	
	/**
	 * 
	 * @return accepted quotation Id which is a primary key
	 * @see AcceptedQuotes#setAcceptedQuoteId(int)s
	 */
	public int getAcceptedQuoteId() {
		return acceptedQuoteId;
	}
	
	/**
	 * 
	 * @param acceptedQuoteId is an integer id which is used to get particular accepted quote details
	 * @see AcceptedQuotes#setAcceptedQuoteId(int)
	 */
	public void setAcceptedQuoteId(int acceptedQuoteId) {
		this.acceptedQuoteId = acceptedQuoteId;
	}
	
	/**
	 * 
	 * @return order object
	 */
	public Order getOrder() {
		return order;
	}
	
	/**
	 * 
	 * @param order is an object which holds details of a particular order
	 * is used to set order in accepted quotes
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	
	/**
	 * 
	 * @return vendor object having details of a vendor
	 */
	public Vendor getVendor() {
		return vendor;
	}
	/**
	 * 
	 * @param vendor is an object which holds details of a particular vendor
	 * is used to set vendor in accepted quotes
	 */
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	/**
	 * 
	 * @return quantity to be provided by vendor
	 * @see AcceptedQuotes#setQuantity(int)
	 */
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * 
	 * @return TotalCost 
	 * @see AcceptedQuotes#setTotalCost(int)
	 */
	public int getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	
	/**
	 * toString() method 
	 */
	@Override
	public String toString() {
		return "AcceptedQuotes [acceptedQuoteId=" + acceptedQuoteId + ", order=" + order + ", vendor=" + vendor
				+ ", quantity=" + quantity + ", totalCost=" + totalCost + "]";
	}
	public boolean equals(Object ob)
	{
		try
		{
			AcceptedQuotes acceptedQuotes = (AcceptedQuotes) ob;
			if(this.acceptedQuoteId == acceptedQuotes.acceptedQuoteId)
				return true;
			return false;
			
		}
		catch(Exception ex)
		{
			return super.equals(ob);
		}
	}
}
