package com.swt;

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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.Service.ServiceClass;
import com.model.Client;
import com.model.Department;
import com.model.Quotation;
import com.model.Quotations;
import com.model.Section;
import com.model.Vendor;
import com.ws.soap.getordersws.GetAllOrders;
import com.ws.soap.getordersws.GetAllOrders_Service;
import com.ws.soap.getordersws.Order;
import com.ws.soap.getproductsws.GetAllProducts;
import com.ws.soap.getproductsws.GetAllProducts_Service;
import com.ws.soap.getproductsws.Product;


/**
 * This class contains functions to create SWT shell and  widgets added to the shell
 * @author Administrator
 *
 */
public class GovernmentSupplySwt extends Shell {

	Shell shell=this;

	/**
	 * Main function to create shell and display 
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			
			Display display =  Display.getDefault(); 
		
			GovernmentSupplySwt shell=new GovernmentSupplySwt(display); 
			
			
		shell.layout();
		shell.open();
		
		while(!shell.isDisposed())
		{
			if(!display.readAndDispatch()) //Read an event from display and dispatch it to the shell
			{
				display.sleep();
			}
		}
		display.dispose();
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
	
}
	
	public GovernmentSupplySwt(Display display) {
		super(display,SWT.SHELL_TRIM);
		createContents();
		
	}

/**
 * This function creates the view to dispaly in swt shell
 * Widgets and their listeners are added 	
 */
private void createContents() {
		
	
	
	
		//Creating object of service class
		ServiceClass serviceClass=new ServiceClass();
		
		/*
		 * Calling the service class functions to retrieve the list to be displayed in shell
		 */
		List<Product> allProducts = serviceClass.soapServiceGetProducts();
		List<Order> allOrders = serviceClass.SoapServiceGetOrders();
		List<Quotation> allQuotations = serviceClass.RestServiceGetQuotations();
		List<Vendor> allVendors = serviceClass.RestServiceGetVendors();
		
		//Widgets used in shell
		setText("Government Supply");
		GridLayout gridLayout=new GridLayout();
		gridLayout.numColumns=4;
		shell.setLayout(gridLayout);
		
		GridData gridData1=new GridData(GridData.FILL_HORIZONTAL);
		gridData1.horizontalAlignment=GridData.FILL;
		gridData1.verticalAlignment=GridData.FILL;
		gridData1.grabExcessHorizontalSpace=true;
		//gridData1.grabExcessVerticalSpace=true;
		
		GridData gridData2=new GridData(GridData.FILL_HORIZONTAL|GridData.FILL_VERTICAL);
		gridData2.horizontalSpan=4;
		gridData2.verticalSpan=4;
		gridData2.horizontalAlignment=GridData.FILL;
		gridData2.verticalAlignment=GridData.FILL;
		gridData2.grabExcessHorizontalSpace=true;
		gridData2.grabExcessVerticalSpace=true;
		
		//Button to view product
		Button viewProducts=new Button(this, 0);
		 viewProducts.setBounds(0,0,100,30);
		 viewProducts.setText("View Products");
		 viewProducts.setLayoutData(gridData1);
		
		 //Button to view orders
		Button viewOrders=new Button(this ,SWT.PUSH);
		 viewOrders.setBounds(150,0,100,30);
		 viewOrders.setText("View Orders");
		 viewOrders.setLayoutData(gridData1);
		 
		//Button to view quotations
		 Button viewQuotations=new Button(this ,SWT.PUSH);
		 viewQuotations.setBounds(300,0,100,30);
		 viewQuotations.setText("View Quotations");
		 viewQuotations.setLayoutData(gridData1);
		 
		 //Button to view vendors
		 Button viewVendors=new Button(this ,SWT.PUSH);
		 viewVendors.setBounds(450,0,100,30);
		 viewVendors.setText("View Vendors");
		 viewVendors.setLayoutData(gridData1);
		 
		 //Table to view produt details
		 Table tableProduct = new Table(this,SWT.BORDER);
		 tableProduct.addDisposeListener(new DisposeListener()
		 {
			 @Override
				public void widgetDisposed(DisposeEvent e) { }
				
		});
			tableProduct.setBounds(0,50,500,400);
			tableProduct.setLinesVisible(true);
			tableProduct.setLayoutData(gridData2);
			tableProduct.setVisible(false);
			
			
		//Table to view order details	
		Table tableOrder = new Table(this,SWT.BORDER);
		tableOrder.addDisposeListener(new DisposeListener(){
			@Override
			public void widgetDisposed(DisposeEvent e) {}
		});
				tableOrder.setBounds(0,50,500,400);
				tableOrder.setLinesVisible(true);
				tableOrder.setVisible(false);
				tableOrder.setLayoutData(gridData2);
				
				
		//Table to view quotation details		
		Table tableQuotation = new Table(this,SWT.BORDER);
		tableQuotation.addDisposeListener(new DisposeListener(){
				@Override
				public void widgetDisposed(DisposeEvent e) {}
			});
				 tableQuotation.setBounds(0,50,500,400);
				 tableQuotation.setLinesVisible(true);
				 tableQuotation.setVisible(false);
				 tableQuotation.setLayoutData(gridData2);
				 
		//Table to view quotation details		
		Table tableVendors = new Table(this,SWT.BORDER);
		tableVendors.addDisposeListener(new DisposeListener()
		{
			@Override
			public void widgetDisposed(DisposeEvent e) {}
		});
		tableVendors.setBounds(0,50,500,400);
		tableVendors.setLinesVisible(true);
		tableVendors.setVisible(false);
		tableVendors.setLayoutData(gridData2); 
			
		//Listener for view product button
			viewProducts.addSelectionListener(new SelectionListener() 
			{
				@Override
				public void widgetSelected(SelectionEvent arg0) {
				
					
					
					tableProduct.setVisible(true);
					TableColumn productId = new TableColumn(tableProduct, SWT.RIGHT);
					productId.setText("Product ID");
					productId.setWidth(100);
					
					TableColumn productName = new TableColumn(tableProduct, SWT.LEFT);
					productName.setText("Product Name");
					productName.setWidth(150);

					TableColumn productCost = new TableColumn(tableProduct, SWT.RIGHT);
					productCost.setText("Product Details");
					productCost.setWidth(100);

					TableColumn departmentName = new TableColumn(tableProduct, SWT.LEFT);
					departmentName.setText("Department name");
					departmentName.setWidth(200);
					
					TableColumn sectionName = new TableColumn(tableProduct, SWT.RIGHT);
					sectionName.setText("Section name");
					sectionName.setWidth(100);
				
					
					
				
					productId.setResizable(true);
					productName.setResizable(true);
					productCost.setResizable(true);
					sectionName.setResizable(true);
					departmentName.setResizable(true);
					tableProduct.setHeaderVisible(true);
					for(Product product:allProducts)
					{
					TableItem item1 = new TableItem(tableProduct, SWT.None);
					item1.setText(new String[] {""+product.getProductId(),""+product.getProductName(),""+product.getProductDetails(),""+product.getSection().getDepartment().getDepartmentName(),""+product.getSection().getSectionName()});
				
				}
					
					
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		 
		 //Listener for view orders button
		 viewOrders.addSelectionListener(new SelectionListener() 
		 {
			
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					
					tableOrder.setVisible(true);
					TableColumn orderId = new TableColumn(tableOrder, SWT.RIGHT);
					orderId.setText("Order ID");
					orderId.setWidth(100);
					
					TableColumn productName = new TableColumn(tableOrder, SWT.RIGHT);
					productName.setText("Product Name");
					productName.setWidth(150);
					
					TableColumn quantitRequired = new TableColumn(tableOrder, SWT.RIGHT);
					quantitRequired.setText("Quantity Requireed");
					quantitRequired.setWidth(100);
					
					TableColumn orderPlacedDate = new TableColumn(tableOrder, SWT.RIGHT);
					orderPlacedDate.setText("Order Placed Date");
					orderPlacedDate.setWidth(100);
					
					TableColumn deadline = new TableColumn(tableOrder, SWT.RIGHT);
					deadline.setText("Deadline");
					deadline.setWidth(100);
					
					tableOrder.setHeaderVisible(true);
					for(Order order:allOrders)
					{
						TableItem item1 = new TableItem(tableOrder, SWT.None);
						item1.setText(new String[] {""+order.getOrderId(),""+order.getProduct().getProductName(),""+order.getQuantityRequired(),""+order.getOrderPlacedDate(),""+order.getDeadline()});
						
					}
					
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		 
		 
		 //Table to view quotations
			viewQuotations.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
						
						tableQuotation.setVisible(true);
						TableColumn quoteId = new TableColumn(tableQuotation, SWT.RIGHT);
						quoteId.setText("Quote Id ");
						quoteId.setWidth(100);
						
						TableColumn productName = new TableColumn(tableQuotation, SWT.LEFT);
						productName.setText("Product Name");
						productName.setWidth(150);

						TableColumn quoteCost = new TableColumn(tableQuotation, SWT.RIGHT);
						quoteCost.setText("Quote Cost");
						quoteCost.setWidth(100);

						TableColumn quoteQuantity = new TableColumn(tableQuotation, SWT.LEFT);
						quoteQuantity.setText("Quote Quantity");
						quoteQuantity.setWidth(200);
						
						TableColumn estimateDeliveryDate = new TableColumn(tableQuotation, SWT.RIGHT);
						estimateDeliveryDate.setText("estimateDeliveryDate");
						estimateDeliveryDate.setWidth(100);
					
						
						
					
						
						tableQuotation.setHeaderVisible(true);
						for(Quotation quotation:allQuotations)
						{
						TableItem item1 = new TableItem(tableQuotation, SWT.None);
						item1.setText(new String[] {""+quotation.getQuoteId(),
								""+quotation.getOrder().getProduct().getProductName(),
								""+quotation.getQuoteCost(),
								""+quotation.getQuotedQuantity(),
								""+quotation.getEstimateDeliveryDate()});
					
					}
						
						
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		
	
	

		//Table to view quotations
		viewVendors.addSelectionListener(new SelectionListener() {
	
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			
			tableVendors.setVisible(true);
			
			TableColumn vendorId = new TableColumn(tableVendors, SWT.RIGHT);
			vendorId.setText("Vendor Id ");
			vendorId.setWidth(100);
			
			TableColumn vendorName = new TableColumn(tableVendors, SWT.LEFT);
			vendorName.setText("Vendor Name");
			vendorName.setWidth(150);

			TableColumn rating = new TableColumn(tableVendors, SWT.RIGHT);
			rating.setText("Vendor Ratings");
			rating.setWidth(100);

			
			
			TableColumn establishedDate = new TableColumn(tableVendors, SWT.RIGHT);
			establishedDate.setText("Established Date");
			establishedDate.setWidth(100);
		
			
			tableVendors.setHeaderVisible(true);
		
			
			tableQuotation.setHeaderVisible(true);
			for(Vendor vendor:allVendors)
			{
			TableItem item1 = new TableItem(tableVendors, SWT.None);
			item1.setText(new String[] {""+vendor.getVendorId(),
					""+vendor.getVendorName(),
					""+vendor.getVendorRating(),
					""+vendor.getEstablishedDate(),
					});
		
		}
			
			
	}
	
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
});

}

	@Override
	protected void checkSubclass(){
		
	}
}
	
	
