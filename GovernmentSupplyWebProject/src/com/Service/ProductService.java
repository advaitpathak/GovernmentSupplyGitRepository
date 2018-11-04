package com.Service;

import java.util.List;

import com.al.dao.ProductExistException;
import com.al.model.Product;
/**
 * 
 * @author Administrator
 *Class @ProductService provides service layer functionalities for Product table
 */
public class ProductService implements ProductServiceInterface {
/**
 *Method @Product getProduct(int productId)
 *@return Product wit productId from table Order through ProductDao
 */
	@Override
	public Product getProduct(int productId) {
		// TODO Auto-generated method stub
		return productdao.getProduct(productId);
	}
/**
 * Method @List<Product> getAllProduct()
 * fetches all Product from Product table through ProductDao 
 * @return List of Product 
 */
	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productdao.getAllProduct();
	}
/**
 * Method @addProduct(Product product)
 * Adds new Product into Product table through ProductDao
 */
	@Override
	public void addProduct(Product product) throws ProductExistException {
		// TODO Auto-generated method stub
			productdao.addProduct(product);
	}
/**
 * Method @updateProduct(Product product)
 * Update details of Product into Product table through ProducDao
 */
	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		productdao.updateProduct(product);
	}
/**
* Method @deleteProduct(Product product)
* Deletes Product from Product table through ProducDao
*/
	@Override
	public void deleteProduct(Product product) {
		// TODO Auto-generated method stub
		productdao.deleteProduct(product);
	}

}
