package com.Servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getSession(false)==null)
		{	
			request.getSession().invalidate();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginPage.jsp");
			requestDispatcher.include(request, response);
		}
		else
		{
			try{
			
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
			Object quotedQuantityObj = session.getAttribute("quoteQuantity");
			Integer quotedQuantity = Integer.parseInt(quotedQuantityObj.toString());
			String estimatedDeliveryDate = request.getParameter("estimatedDeliveryDate");
			Date estimatedDeliveryDateParse;
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			sdf.setLenient(false);
			estimatedDeliveryDateParse = sdf.parse(estimatedDeliveryDate);//Just for exception handling
		   
						
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
			}
			} catch (NumberFormatException |ParseException e) {
				// TODO Auto-generated catch block
				String exceptionName = "Quotation can not be placed due to invalid entries";
				request.setAttribute( "exceptionName",exceptionName);
				String OriginPage = "SetQuotation.jsp";
				request.setAttribute("OriginPage", OriginPage);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ExceptionPage.jsp");
				requestDispatcher.forward(request, response);
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
