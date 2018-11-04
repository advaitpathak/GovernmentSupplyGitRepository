package com.al.dao;

import java.util.List;

import com.al.model.Product;

/**
 * 
 * @author Administrator
 *interface @ProductDao
 */
public interface ProductDao 
{
	String TABLEProduct ="Product",COLproductId="productId",COLproductName= "productName";
	 String COLproductCost = "productCost",COLsectionId = "sectionID", COLproductDetail = "productDetail";
	 
	 Product getProduct(int productId);
	 List<Product> getAllProduct();
	 void addProduct(Product product)throws ProductExistException;
	 void updateProduct(Product product);
	 void deleteProduct(Product product);
}
