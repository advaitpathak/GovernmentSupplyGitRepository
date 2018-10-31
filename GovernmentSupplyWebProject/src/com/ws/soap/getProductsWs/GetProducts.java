package com.ws.soap.getProductsWs;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.Service.ProductService;
import com.al.model.Product;

@WebService(name="GetAllProducts", serviceName="GetAllProducts", portName="newPort")
public class GetProducts implements GetAllProductsInterface
{
	@WebMethod(exclude=false, operationName=operationNameParam)
	public List<Product> getProducts()
	{
		ProductService productService = new ProductService();
		List<Product> allProduct = productService.getAllProduct();
		return allProduct;
	}
}
