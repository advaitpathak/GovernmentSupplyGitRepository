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
			String clientIdStr = request.getParameter("clientId");
			Integer clientId = Integer.parseInt(clientIdStr);
			String clientNameStr = request.getParameter("clientName");
			String clientEmailStr = request.getParameter("clientEmail");
			String clientPasswordStr = request.getParameter("clientPassword");
			String clientContactNo = request.getParameter("clientContactNo");
		
		ClientService clientService = new ClientService();
		try 
		{
			clientService.addClient(new Client(clientId, clientNameStr, clientEmailStr, clientPasswordStr, clientContactNo));
		} 
		catch (ClientExistException e) 
		{
			e.printStackTrace();
		}
		List<Client> allClientList = clientService.getAllClient();
		session.setAttribute("allClientList", allClientList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/AllAvailableClients.jsp");
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
