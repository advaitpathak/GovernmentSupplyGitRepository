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

import com.Service.AcceptedQuotesService;
import com.Service.OrderService;
import com.Service.QuotationService;
import com.al.model.AcceptedQuotes;
import com.al.model.Order;
import com.al.model.Quotation;

/**
 * Servlet implementation class FetchPlacedOrders
 */
@WebServlet("/FetchPlacedOrders")
public class FetchPlacedOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchPlacedOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getSession(false)==null)
		{	
			request.getSession().invalidate();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginPage.jsp");
			requestDispatcher.include(request, response);
		}
		else
		{
			HttpSession session = request.getSession();
			OrderService orderService = new OrderService();
			List<Order> allOrdersList = orderService.getAllOrders();
			List<Order> quotedOrderList = new ArrayList<Order>();
			List<Order> unquotedOrderList = new ArrayList<Order>();
			Object obj = session.getAttribute("vendorId");
			Integer vendorId = Integer.parseInt(obj.toString());
			QuotationService quotationService = new QuotationService();
			List<Quotation> allQuotation = quotationService.getAllQuotation();
			for(Quotation quotation:allQuotation)
			{
				if(quotation.getVendor().getVendorId()==vendorId)
				{
					quotedOrderList.add(quotation.getOrder());
				}
			}
			for(Order order: quotedOrderList)
			{
				System.out.println("in for loop");
				System.out.println(order);
				System.out.println(allOrdersList.indexOf(order));
			}
			System.out.println(quotedOrderList);
			System.out.println(allOrdersList);
			
			session.setAttribute("allOrdersList", allOrdersList);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/VendorPortal.jsp");
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
