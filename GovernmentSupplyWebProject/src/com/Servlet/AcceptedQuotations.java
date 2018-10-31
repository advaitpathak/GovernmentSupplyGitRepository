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

import com.Service.AcceptedQuotesService;
import com.Service.QuotationSelectionService;
import com.al.model.AcceptedQuotes;

/**
 * Servlet implementation class AcceptedQuotations
 */
@WebServlet("/AcceptedQuotations")
public class AcceptedQuotations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptedQuotations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		String StrOrderID = request.getParameter("OrderId");
		int orderId = Integer.parseInt(StrOrderID);
		QuotationSelectionService quotationSelectionService = new QuotationSelectionService();
		AcceptedQuotesService acceptedQuotesService = new AcceptedQuotesService();
		List<AcceptedQuotes> allAcceptedQuotesList = acceptedQuotesService.getAllAcceptedQuotes();
		for(AcceptedQuotes acceptedQuotes : allAcceptedQuotesList)
		{
			if(acceptedQuotes.getOrder().getOrderId()==orderId)
			{	
				System.out.println(allAcceptedQuotesList);
				session.setAttribute("allAcceptedQuotesList", allAcceptedQuotesList);
				RequestDispatcher requestDispatcher =request.getRequestDispatcher("/AcceptedQuotation.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		System.out.println("orderId= "+orderId);
		quotationSelectionService.quotationSelection(orderId);
		allAcceptedQuotesList = acceptedQuotesService.getAllAcceptedQuotes();
		System.out.println(allAcceptedQuotesList);
		session.setAttribute("allAcceptedQuotesList", allAcceptedQuotesList);
		RequestDispatcher requestDispatcher =request.getRequestDispatcher("/AcceptedQuotation.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
