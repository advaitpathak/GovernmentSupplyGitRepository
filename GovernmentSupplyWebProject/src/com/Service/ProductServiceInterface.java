package com.Service;

import java.util.List;

import com.al.dao.ProductDao;
import com.al.dao.ProductDaoImpl;
import com.al.dao.ProductExistException;
import com.al.model.Product;
/**
 * 
 * @author Administrator
 *Interface @ProductServiceInterface
 */
public interface ProductServiceInterface {

	ProductDao productdao=new ProductDaoImpl();
	
	Product getProduct(int productId);
	 List<Product> getAllProduct();
	 void addProduct(Product product)throws ProductExistException;
	 void updateProduct(Product product);
	 void deleteProduct(Product product);
	
}
