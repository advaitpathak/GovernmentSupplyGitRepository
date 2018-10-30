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

import com.Service.QuotationService;
import com.al.model.Quotation;

/**
 * Servlet implementation class GetVendorQuotes
 */
@WebServlet("/GetVendorQuotes")
public class GetVendorQuotes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetVendorQuotes() {
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
			Object vendorIdObj = session.getAttribute("vendorId");
			Integer vendorId = Integer.parseInt(vendorIdObj.toString());
			QuotationService quotationService = new QuotationService();
			List<Quotation> quotationOfVendorList = quotationService.getQuotationOfVendor(vendorId);
			session.setAttribute("quotationOfVendorList", quotationOfVendorList);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/VendorQuoteDetails.jsp");
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
