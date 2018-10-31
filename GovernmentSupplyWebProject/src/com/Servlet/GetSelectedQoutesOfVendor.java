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
import com.Service.QuotationService;
import com.al.model.AcceptedQuotes;
import com.al.model.Quotation;

/**
 * Servlet implementation class GetSelectedQoutesOfVendor
 */
@WebServlet("/GetSelectedQoutesOfVendor")
public class GetSelectedQoutesOfVendor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSelectedQoutesOfVendor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		Object vendorIdObj = session.getAttribute("vendorId");
		Integer vendorId = Integer.parseInt(vendorIdObj.toString());
		AcceptedQuotesService acceptedQuotesService = new AcceptedQuotesService();
		List<AcceptedQuotes> allAcceptedQuotes = acceptedQuotesService.getAllAcceptedQuotes();
		List<AcceptedQuotes> listOfQuotesWithMatchingVendor = new ArrayList<AcceptedQuotes>();
		for(AcceptedQuotes acceptedQuotes:allAcceptedQuotes)
		{
			if(acceptedQuotes.getVendor().getVendorId()==vendorId)
			{
				listOfQuotesWithMatchingVendor.add(acceptedQuotes);
			}
		}
		session.setAttribute("allAcceptedQuotesList", listOfQuotesWithMatchingVendor);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/AcceptedQuotation.jsp");
		requestDispatcher.include(request, response);

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
