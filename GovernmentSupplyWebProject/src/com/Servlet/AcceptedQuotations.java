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

import org.apache.log4j.Logger;

import com.Service.AcceptedQuotesService;
import com.Service.QuotationSelectionService;
import com.al.model.AcceptedQuotes;

/**
 * Servlet implementation class AcceptedQuotations
 */
@WebServlet("/AcceptedQuotations")
public class AcceptedQuotations extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger=Logger.getRootLogger();  
       
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
		HttpSession session = request.getSession(false);
		
		if(session==null)
		{
			 RequestDispatcher requestDispatcher=request.getRequestDispatcher("Login.jsp");
			 requestDispatcher.include(request, response);
		}
		else
		{
			//Get the orderId of order for which accepted Quotes are to be viewed
			String StrOrderID = request.getParameter("OrderId");
			int orderId = Integer.parseInt(StrOrderID);
			
			QuotationSelectionService quotationSelectionService = new QuotationSelectionService();
			AcceptedQuotesService acceptedQuotesService = new AcceptedQuotesService();
			
			//Get the list of all the quotes accepted
			List<AcceptedQuotes> allAcceptedQuotesList = acceptedQuotesService.getAllAcceptedQuotes();
			for(AcceptedQuotes acceptedQuotes : allAcceptedQuotesList)
			{
				//Get the accepted quotes of entered orderId
				if(acceptedQuotes.getOrder().getOrderId()==orderId)
				{	
					logger.info(allAcceptedQuotesList);
					session.setAttribute("allAcceptedQuotesList", allAcceptedQuotesList);
					RequestDispatcher requestDispatcher =request.getRequestDispatcher("/AcceptedQuotation.jsp");
					requestDispatcher.forward(request, response);
			}
		}
		
		logger.info("orderId= "+orderId);
		quotationSelectionService.quotationSelection(orderId);
		allAcceptedQuotesList = acceptedQuotesService.getAllAcceptedQuotes();
		logger.info(allAcceptedQuotesList);
		session.setAttribute("allAcceptedQuotesList", allAcceptedQuotesList);
		RequestDispatcher requestDispatcher =request.getRequestDispatcher("/AcceptedQuotation.jsp");
		requestDispatcher.forward(request, response);
		}
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			System.out.println("no quotes are received for this order");
			String exceptionName = "No quotes are received for this order";
			request.setAttribute( "exceptionName",exceptionName);
			String OriginPage = "GovernmentEmployeePortal.jsp";
			request.setAttribute("OriginPage", OriginPage);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ExceptionPage.jsp");
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
