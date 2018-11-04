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
import com.al.dao.ClientExistException;
import com.al.dao.ProductExistException;
import com.al.dao.SectionDaoImpl;
import com.al.model.Client;
import com.al.model.Product;

/**
 * Servlet implementation class AddClient
 */
@WebServlet("/AddClient")
public class AddClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getRootLogger();       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getSession(false)==null)
		{	
			request.getSession().invalidate();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginPage.jsp");
			requestDispatcher.include(request, response);
		}
		else
		{
			try {
				HttpSession session = request.getSession();
				Object clientIdObj = session.getAttribute("addClientId");
				Integer clientId = Integer.parseInt(clientIdObj.toString());
				String clientNameStr = request.getParameter("clientName");
				String clientEmailStr = request.getParameter("clientEmail");
				String clientPasswordStr = request.getParameter("clientPassword");
				String clientContactNo = request.getParameter("clientContactNo");

				ClientService clientService = new ClientService();

				clientService.addClient(new Client(clientId, clientNameStr, clientEmailStr, clientPasswordStr, clientContactNo));

				List<Client> allClientList = clientService.getAllClient();
				session.setAttribute("allClientList", allClientList);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/AllAvailableClients.jsp");
				requestDispatcher.include(request, response);
			} catch (NumberFormatException | NullPointerException e) {
				
				logger.info("Failed to add Client. Please enter valid data");
				String exceptionName = "Failed to add Client. Please enter valid data";
				request.setAttribute( "exceptionName",exceptionName);
				String OriginPage = "AdminPortal.jsp";
				request.setAttribute("OriginPage", OriginPage);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ExceptionPage.jsp");
				requestDispatcher.include(request, response);
			} catch (ClientExistException e) {
			
				e.printStackTrace();
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
