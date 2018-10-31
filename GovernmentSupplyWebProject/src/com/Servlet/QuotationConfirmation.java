package com.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Service.QuotationService;
import com.al.model.Order;
import com.al.model.Quotation;

/**
 * Servlet implementation class QuotationConfirmation
 */
@WebServlet("/QuotationConfirmation")
public class QuotationConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuotationConfirmation() {
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
			
			//Get the QuoteId,Order and VendorId for adding a quotation 
			Object quoteIdObj = session.getAttribute("quoteId");
			Integer quoteId = (Integer) quoteIdObj;
			Object orderObj = session.getAttribute("order");
			Order order = (Order) orderObj;
			int orderId = order.getOrderId();
			Object vendorIdObj = session.getAttribute("vendorId");
			String string = vendorIdObj.toString();
			Integer vendorId = Integer.parseInt(string);
			
			//Get the quotedCost,quotedQuantity ,Estimated Delivery from the vendor as parameters
			String quotedCostStr = request.getParameter("quotedCost");
			Integer quotedCost = Integer.parseInt(quotedCostStr);
			String quotedQuantityStr = request.getParameter("quotedQuantity");
			Integer quotedQuantity = Integer.parseInt(quotedQuantityStr);
			String estimatedDeliveryDate = request.getParameter("estimatedDeliveryDate");
						
			QuotationService quotationService = new QuotationService();
			
			
			boolean checkEstimateDate = quotationService.checkEstimateDate(estimatedDeliveryDate,order.getDeadline());
			
			if(checkEstimateDate)
			{
				quotationService.addQuotation(quoteId, orderId, vendorId, quotedCost, estimatedDeliveryDate, quotedQuantity);
				
				Quotation confirmedQuotation = quotationService.getQuotation(quoteId);
				session.setAttribute("confirmedQuotation", confirmedQuotation);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/QuotationPlaced.jsp");
				requestDispatcher.forward(request, response);				
			}
			else
			{
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/SetQuotation.jsp");
				requestDispatcher.forward(request, response);
				System.out.println("Date entered is after deadline");
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
