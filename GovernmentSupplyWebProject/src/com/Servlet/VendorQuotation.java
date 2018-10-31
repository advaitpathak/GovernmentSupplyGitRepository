package com.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.Service.OrderService;
import com.Service.QuotationService;
import com.Service.VendorService;
import com.al.dao.QuotationDao;
import com.al.dao.QuotationDaoImpl;
import com.al.model.Order;
import com.al.model.Quotation;
import com.al.model.Vendor;

/**
 * Servlet implementation class VendorQuotation
 */
@WebServlet("/VendorQuotation")
public class VendorQuotation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendorQuotation() {
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
			QuotationService quotationService = new QuotationService();
			OrderService orderService = new OrderService();
			List<Quotation> allQuotationList = quotationService.getAllQuotation();
			int size = allQuotationList.size();
			Quotation quotation = allQuotationList.get(size-1);
			int quoteId = quotation.getQuoteId();
			session.setAttribute("quoteId", quoteId+1); 
			
			String[] parameter = request.getParameterValues("Quote");
				
			Integer parameterInt = Integer.parseInt(parameter[0]);
			Order order = orderService.getOrder(parameterInt);
			session.setAttribute("order", order);
			int quoteQuantity = order.getQuantityRequired();
			session.setAttribute("quoteQuantity", quoteQuantity);
			Object vendorIdObj = session.getAttribute("vendorId");
			String vendorIdObjStr = vendorIdObj.toString();
			Integer vendorId = Integer.parseInt(vendorIdObjStr);
			VendorService vendorService = new VendorService();
			Vendor vendor = vendorService.getVendor(vendorId);
			
			if(vendorService.vendorEligibility(vendor))
			{
				for(Quotation quotation_inner : allQuotationList)
				{
					if(quotation_inner.getOrder().getOrderId()==order.getOrderId()&&quotation.getVendor().getVendorId()==vendorId)
					{
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/QuoteAlreadyPlaced.jsp");
						requestDispatcher.forward(request, response);
						return;
					}
				}
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/SetQuotation.jsp");
				requestDispatcher.forward(request, response);
			}
			else
			{
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/VendorPortal.jsp");
				requestDispatcher.forward(request, response);
				//System.out.println("Not eligible");
			}			
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
