package com.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.Service.ProductService;
import com.al.model.Product;

/**
 * Servlet  implementation class SelectedProducts
 */
@WebServlet("/SelectedProducts")
public class SelectedProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger=Logger.getRootLogger();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectedProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	try{
		HttpSession session = request.getSession();
		ProductService productService=new ProductService();
		//Get the selected Products from checkbox 
		String[] selectedValues = request.getParameterValues("checkboxGroup");
	 	
	
		//Add selected products to a list of products
			List<Product> selectedProducts=new ArrayList<Product>();
			for(int i=0;i<selectedValues.length;i++)
			{
				Integer selectedProductId=Integer.parseInt(selectedValues[i]);
				Product productObj = productService.getProduct(selectedProductId);
				selectedProducts.add(productObj);
				
			}
		//Set the list of selected products as a request attribute 
		session.setAttribute("selectedProductsList",selectedProducts);
		
		//Redirect the user to placeOrder.jsp page
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("PlaceOrder.jsp");
		requestDispatcher.include(request, response);
	}
	catch(NumberFormatException | NullPointerException e)
	{
		logger.info("Please select at least one order");
		//should go back to GovernmentEmployeePortal.jsp
		String exceptionName = "Please select at least one order";
		request.setAttribute( "exceptionName",exceptionName);
		String OriginPage = "GovernmentEmployeePortal.jsp";
		request.setAttribute("OriginPage", OriginPage);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ExceptionPage.jsp");
		requestDispatcher.forward(request, response);
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
