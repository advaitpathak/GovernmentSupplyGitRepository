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
import com.al.dao.QuotationDaoImpl;
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
			List<Order> unquotedOrdersList = new ArrayList<>();
			QuotationService quotationService = new QuotationService();
			Object obj = session.getAttribute("vendorId");
			Integer loggedInVendorId = Integer.parseInt(obj.toString());			
			List<Quotation> quotableOrdersForVendorList = quotationService.getQuotableOrdersForVendor(new QuotationDaoImpl().getAllQuotation(), loggedInVendorId);
			System.out.println(quotableOrdersForVendorList);
			System.out.println(allOrdersList);
			for(Quotation quotation : quotableOrdersForVendorList)
			{
			for(Order order : allOrdersList)
			{
					if(quotation.getOrder().getOrderId()!=order.getOrderId()&&quotation.getVendor().getVendorId()==loggedInVendorId)
					{
						unquotedOrdersList.add(order);
					}
			}
			}
//			for(Quotation quotation : quotableOrdersForVendorList)
//			{
//				//quotation.getOrder().getOrderId();
//				List<Quotation> quotationOfVendor = new QuotationService().getQuotationOfVendor(loggedInVendorId);
//				for(Quotation quotation_inner : quotationOfVendor)
//				{
//				int temp_orderId = quotation_inner.getOrder().getOrderId();
//				
//				if(temp_orderId!=orderService.getOrder(temp_orderId).getOrderId())
//				{
//					unquotedOrdersList.add(new OrderService().getOrder(temp_orderId));
//				}
//				}
//			}
			System.out.println(unquotedOrdersList);
			
			
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
