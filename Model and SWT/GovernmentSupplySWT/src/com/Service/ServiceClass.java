package com.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.model.Client;
import com.model.Department;
import com.model.Quotation;
import com.model.Quotations;
import com.model.Section;
import com.model.Vendor;
import com.model.Vendors;
import com.ws.soap.getordersws.GetAllOrders;
import com.ws.soap.getordersws.GetAllOrders_Service;
import com.ws.soap.getordersws.Order;
import com.ws.soap.getproductsws.GetAllProducts;
import com.ws.soap.getproductsws.GetAllProducts_Service;
import com.ws.soap.getproductsws.Product;

/**
 * 
 * @author Administratoris  
 * This is a service class that contain functions that consume web services.
 * 
 */
public class ServiceClass {
	
	/**
	 * This fuction will connect with rest service using url .
	 * It converts the xml string received from web service into the list of quotation.  
	 * @return list of quotations 
	 */
	public List<Quotation> RestServiceGetQuotations()
	{
		try {

			//Get result from web service url
			URL url = new URL("http://localhost:8080/GovernmentSupplyWebProject/rest/Quotations/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn .setRequestProperty("Content-Type", "application/json");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
		
			String output;
			output=br.readLine();
			StringReader sr = new StringReader(output);
			
			//Convert the received xml string to java object
			JAXBContext jaxbContext;
			Class[] classes= {Quotation.class, Order.class, Client.class,Department.class,Product.class,Section.class,Vendor.class};
			jaxbContext = JAXBContext.newInstance(Quotations.class);
				
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Quotations quotations=(Quotations)unmarshaller.unmarshal(sr);
			List<Quotation> quotationList = quotations.getQuotationList();
			return quotationList;	
		}catch (JAXBException e) {
			
			e.printStackTrace();
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (ProtocolException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
				
	}
	
	
	/**
	 * This function uses the rest service GetVendors using its url.
	 * The web service returns the list of vendors in form of xml string which is converted to list of objects.
	 * @return List of vendors
	 */
	public List<Vendor> RestServiceGetVendors()
	{
		try {

			//Get result from web service url
			URL url = new URL("http://localhost:8080/GovernmentSupplyWebProject/rest/Vendors/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn .setRequestProperty("Content-Type", "application/json");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
		
			String output;
			output=br.readLine();
			StringReader sr = new StringReader(output);
			
			//Convert the received xml string to java object
			JAXBContext jaxbContext;
			jaxbContext = JAXBContext.newInstance(Vendors.class);
				
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Vendors vendors=(Vendors)unmarshaller.unmarshal(sr);
			List<Vendor> vendorList = vendors.getVendorList();
			return vendorList;	
			
		}catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
		return null;
				
	}
	
	
	/**
	 * This function consumes the soap web serviec Get products
	 * @return lit of products
	 */
	public List<Product> soapServiceGetProducts()
	{
		GetAllProducts_Service getAllProducts_Service = new GetAllProducts_Service();
		GetAllProducts productPort = getAllProducts_Service.getNewPort();
		List<Product> allProducts = productPort.getAllProducts();
		return allProducts;
	}
	
	/**
	 * This function consumes the soap web serviec Get orders
	 * @return lit of orders
	 */
	public List<Order> SoapServiceGetOrders()
	{
		GetAllOrders_Service getAllOrders_Service = new GetAllOrders_Service();
		GetAllOrders orderPort = getAllOrders_Service.getNewPort();
		List<Order> allOrders = orderPort.getAllOrders();
		return allOrders;
	}

}
