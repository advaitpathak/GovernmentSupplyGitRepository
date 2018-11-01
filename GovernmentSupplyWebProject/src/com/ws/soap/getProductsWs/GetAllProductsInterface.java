package com.ws.soap.getProductsWs;

import java.util.List;

import com.al.model.Product;

public interface GetAllProductsInterface 
{
	String operationNameParam = "getAllProducts";
	public List<Product> getProducts();
}
