package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Service.OrderService;
import com.Service.ProductService;
import com.al.model.Order;
import com.al.model.Product;


/**
 * Servlet implementation class ListAllProducts
 */
@WebServlet("/ClientPageDetails")
public class ClientPageDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientPageDetails () {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);
		//If session has expired redirect the user to login page
		if(session==null)
		{
			 RequestDispatcher requestDispatcher=request.getRequestDispatcher("LoginPage.jsp");
			 requestDispatcher.include(request, response);
		}
		else
		{
			//Get list of all products and set it as request attribute
			ProductService productService= new ProductService();
			List<Product> allProductList = productService.getAllProduct();
			session.setAttribute("allProductList", allProductList);
			
			//Get list of all orders placed by that particular client and add that list as session attribute
			List<Order> clientOrderList = new ArrayList<>();
			OrderService orderService = new OrderService();
			List<Order> allOrdersList = orderService.getAllOrders();
			Object clientIdObj = session.getAttribute("clientId");
			Integer clientId = Integer.parseInt(clientIdObj.toString());
			for(Order order : allOrdersList)
			{
				if(order.getClient().getClientId()==clientId)
				{
					clientOrderList.add(order);
				}
			}
		
			session.setAttribute("clientOrderList", clientOrderList);
			
			//Redirect the user to GovernmentEmployeePortal.jsp page
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("GovernmentEmployeePortal.jsp");
			requestDispatcher.include(request, response);
			
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
