package com.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product implements Serializable
{
	private int productId;
	private String productName;
	private int productCost;
	private String productDetails;
	private Section section;
	
	public Product(int productId, String productName, int productCost, String productDetails, Section section) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCost = productCost;
		this.productDetails = productDetails;
		this.section = section;
	}
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
	
}
