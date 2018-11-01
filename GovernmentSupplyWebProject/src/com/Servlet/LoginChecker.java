package com.Servlet;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.Service.AdminService;
import com.Service.ClientService;
import com.Service.VendorService;
import com.al.dao.ClientDaoImpl;
import com.al.dao.VendorDaoImpl;
import com.al.model.Client;
import com.al.model.Vendor;




/**
 * Servlet implementation class LoginChecker
 */
@WebServlet("/LoginChecker")
public class LoginChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger=Logger.getLogger(LoginChecker.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginChecker() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		//Getting the parameters from login.jsp page
				String loginId = request.getParameter("loginId");
				String pwd = request.getParameter("password");
				String type=request.getParameter("type");
				
				
		//Log for checking the user entered loginid an password 
				logger.info("entered userid = "+loginId);
				logger.info("entered password"+pwd);
								
				logger.info("user = "+loginId+"password= "+pwd+ "type=" +type);
				//Integer loginIdInt=Integer.parseInt(loginId);
			
		//Login check for government employee
			 if(type.equalsIgnoreCase("Government Employee"))
			 {
				 Integer loginIdInt=Integer.parseInt(loginId);
				logger.trace("in type gov emp");
				 //Client client=new ClientDaoImpl().getClient(loginIdInt);
				// String password=client.getClientPassword();
				
				ClientService clientService=new ClientService();
				Client client = clientService.getClient(loginIdInt);
				if(client != null)
				{
					String password=client.getClientPassword();
					logger.trace("client password:"+password);
					if(password.equals(pwd))
					{
				  
						logger.trace("gov emp password matched");
						HttpSession session=request.getSession(true);
						session.setAttribute("userType", "Government employee");
						session.setAttribute("client", client);
						session.setAttribute("clientId", loginIdInt);
						RequestDispatcher requestDispatcher=request.getRequestDispatcher("ClientPageDetails");
						requestDispatcher.forward(request, response);
					}
				}
				else  //send it to loginPage.jsp
				{ 
					RequestDispatcher requestDispatcher=request.getRequestDispatcher("LoginPage.jsp");
					response.getWriter().append("Wrong user id OR password Or User Type");
					requestDispatcher.include(request, response);
				}
			 }
		//Login check for vendor
			 else if(type.equalsIgnoreCase("vendor"))
			 {
				 Integer loginIdInt=Integer.parseInt(loginId);
				 logger.trace("in type vendor");
//				 Vendor vendor=new VendorDaoImpl().getVendor(loginIdInt);
//				 String password=vendor.getVendorPassword();
				 
				 VendorService vendorService=new VendorService();
				 Vendor vendor = vendorService.getVendor(loginIdInt);
				 if(vendor !=null)
				 {
					 String password=vendor.getVendorPassword();
					 logger.trace("system passsword "+password);
					 if(password.equals(pwd))
					 {
						 logger.trace("vendor password matched");
						 HttpSession session=request.getSession(true);
						 session.setAttribute("userType","Vendor");
						 session.setAttribute("vendor", vendor);
						 session.setAttribute("vendorId", loginId);
						 RequestDispatcher requestDispatcher=request.getRequestDispatcher("FetchPlacedOrders");
						 requestDispatcher.forward(request, response);
					 }
				 }
				 else
				 {
					  RequestDispatcher requestDispatcher=request.getRequestDispatcher("LoginPage.jsp");
			    	  response.getWriter().append("Wrong user id OR password");
			    	  requestDispatcher.include(request, response);
				 }
			 }
				//Login check for admin
				 else if(type.equalsIgnoreCase("admin"))
				 {
					 logger.trace("in type admin");
					 
					 if(loginId.equals("admin")&&pwd.equals("admin"))
					 {
						 logger.trace("admin password matched");
						 HttpSession session=request.getSession(true);
						 session.setAttribute("userType","Admin");
						 RequestDispatcher requestDispatcher=request.getRequestDispatcher("AdminPortal");
						 requestDispatcher.forward(request, response);
					 }
					 else  //send it to loginPage.html
					 { 
						 RequestDispatcher requestDispatcher=request.getRequestDispatcher("LoginPage.jsp");
						 response.getWriter().append("Wrong user id OR password");
						 requestDispatcher.include(request, response);
					 }
				 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
				
		doGet(request, response);
	}

}
