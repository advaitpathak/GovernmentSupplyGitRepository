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

import com.Service.ClientService;
import com.Service.ProductService;
import com.al.dao.ClientDaoImpl;
import com.al.dao.ProductDaoImpl;
import com.al.model.Client;
import com.al.model.Product;

/**
 * Servlet implementation class DeleteClient
 */
@WebServlet("/DeleteClient")
public class DeleteClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger=Logger.getLogger(LoginChecker.class);
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteClient() {
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
			try {
				ClientService clientService = new ClientService();
				HttpSession session = request.getSession();
				String clientIdStr = request.getParameter("deleteClientId");
				Integer clientId = Integer.parseInt(clientIdStr);
				clientService.deleteClient(new ClientDaoImpl().getClient(clientId));
				List<Client> allClientList = clientService.getAllClient();
				session.setAttribute("allClientList", allClientList);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/AllAvailableClients.jsp");
				requestDispatcher.forward(request, response);
			} catch (NumberFormatException | NullPointerException e) {
				// TODO Auto-generated catch block
				logger.info("Failed to delete Client. Invalid ClientId");
				String exceptionName = "Failed to delete Client. Invalid ClientId";
				request.setAttribute( "exceptionName",exceptionName);
				String OriginPage = "AdminPortal.jsp";
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
