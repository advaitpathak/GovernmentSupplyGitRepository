package com.al.model;

import java.io.Serializable;
/**
 * 
 * @author Administrator
 *@Product class is a model class used to store accepted quotes in objects
 *productId is a primary key for particular key associated for particular product
 *productName is Name for particular product 
 *productCost is used to store Cost for a product
 *productDetails is used store details of product
 *section is a object of class @Section is used to shows which section contains the product 
 *
 */
public class Product implements Serializable
{
	private int productId;
	private String productName;
	private int productCost;
	private String productDetails;
	private Section section;
	
	/**
	 * Product is a parameterized constructor with following parameters
	 * @param productId acceptedQuoteId is a unique id(primary key) in the database
	 * @param productName
	 * @param productCost
	 * @param productDetails
	 * @param section is object of @Section
	 */
	public Product(int productId, String productName, int productCost, String productDetails, Section section) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCost = productCost;
		this.productDetails = productDetails;
		this.section = section;
	}
	
	/**
	 * Default constructor
	 */

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductCost() {
		return productCost;
	}
	public void setProductCost(int productCost) {
		this.productCost = productCost;
	}
	public String getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productCost=" + productCost
				+ ", productDetails=" + productDetails + ", section=" + section + "]";
	}
	
	/**
	 * Checks whether entry is already is present
	 */
	public boolean equals(Object ob)
	{
		try
		{
			Product product = (Product) ob;
			if(this.productId == product.productId)
				return true;
			return false;
		}
		catch(Exception ex)
		{
			return super.equals(ob);
		}
	}
}
