/*Tested----QUOTATION----PRODUCT----SECTION*/
package com.al.connection;

import java.util.List;

import com.al.dao.DepartmentDao;
import com.al.dao.DepartmentDaoImpl;
import com.al.dao.OrderDao;
import com.al.dao.OrderDaoImpl;
import com.al.dao.ProductDao;
import com.al.dao.ProductDaoImpl;
import com.al.dao.ProductExistException;
import com.al.dao.QuotationExistException;
import com.al.dao.SectionDao;
import com.al.dao.SectionDaoImpl;
import com.al.dao.SectionExistException;
import com.al.dao.VendorDao;
import com.al.dao.VendorDaoImpl;
import com.al.model.Department;
import com.al.model.Order;
import com.al.model.Product;
import com.al.model.Section;
import com.al.model.Vendor;


public class GovernmentSupplyMain
{
	public static void main(String[] args)
	{
		//					QUOTATION 					//
		
//		QuotationDao quotationDao = new QuotationDaoImpl();
//		Quotation quotation = quotationDao.getQuotation(901);
//		System.out.println(quotation+"\n");

//		List<Quotation> allquotationList = quotationDao.getAllQuotation();
//		System.out.println("------------List of All Quotation------------"+"\n");
//		for(Quotation s:allquotationList)
//		{
//			System.out.println(s);
//		}

//		try
//		{	
//			OrderDao orderDao = new OrderDaoImpl();
//			Order order = orderDao.getOrder(10002);
//			VendorDao vendorDao = new VendorDaoImpl();
//			Vendor vendor = vendorDao.getVendor(702);
//			Quotation quote = new Quotation(903, order, vendor, 60000, "2018-03-10", 80);
//			
//			quotationDao.addQuotation(quote);;
//		} 
//		catch (QuotationExistException e) 
//		{
//			e.printStackTrace();
//		}
//		System.out.println("----------------Quotation added Successfully----------");\
		
//		Quotation quotation = quotationDao.getQuotation(901);
//		quotation.setQuoteCost(85000);
//		quotationDao.updateQuotation(quotation);		
//		
//		System.out.println("newly updated quotation");
//		quotation = quotationDao.getQuotation(901);
//		System.out.println(quotation);

//		Quotation quotation = quotationDao.getQuotation(901);
//		quotationDao.deleteQuotation(quotation);		
//		
//		System.out.println("quotation deleted");

		
						//					PRODUCT 					//

		
//		ProductDao productDao = new ProductDaoImpl();
//		Product product = productDao.getProduct(1001);
//		System.out.println(product+"\n");

//		List<Product> allproductList = productDao.getAllProduct();
//		System.out.println("------------List of All Product------------"+"\n");
//		for(Product s:allproductList)
//		{
//			System.out.println(s);
//		}

//		try
//		{	
//			SectionDao sectionDao = new SectionDaoImpl();
//			Section section = sectionDao.getSection(202);
//			Product product = new Product(1003, "Glass", 1000, "10mm thick", section);
//			
//			productDao.addProduct(product);
//		} 
//		catch (ProductExistException e) 
//		{
//			e.printStackTrace();
//		}
//		System.out.println("----------------Product added Successfully----------");

//		Product product = productDao.getProduct(1003);
//		product.setProductCost(1500);
//		productDao.updateProduct(product);	
//		
//		System.out.println("newly updated product");
//		product = productDao.getProduct(1003);
//		System.out.println(product);

//		Product product = productDao.getProduct(1003);
//		productDao.deleteProduct(product);;		
//		
//		System.out.println("product deleted");


		//					SECTION						//
		
//		SectionDao sectionDao = new SectionDaoImpl();
//		Section section = sectionDao.getSection(201);
//		System.out.println(section+"\n");

//		List<Section> allsectionList = sectionDao.getAllSection();
//		System.out.println("------------List of All Section------------"+"\n");
//		for(Section s:allsectionList)
//		{
//			System.out.println(s);
//		}
		
//		try
//		{	
//			DepartmentDao departmentDao = new DepartmentDaoImpl();
//			Department department = departmentDao.getDepartment(502);
//			Section section = new Section(203, "XYZ", department);
//			
//			sectionDao.addSection(section);
//		} 
//		catch (SectionExistException e) 
//		{
//			e.printStackTrace();
//		}
//		System.out.println("----------------Section added Successfully----------");

//		Section section = sectionDao.getSection(203);
//		sectionDao.deleteSection(section);		
//		
//		System.out.println("section deleted");
		
		
	}
}
