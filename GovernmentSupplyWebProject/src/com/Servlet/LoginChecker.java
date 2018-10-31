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
				Integer loginIdInt=Integer.parseInt(loginId);
			
		//Login check for government employee
			 if(type.equalsIgnoreCase("Government Employee"))
			 {
				logger.trace("in type gov emp");
				
				ClientService clientService=new ClientService();
				Client client = clientService.getClient(loginIdInt);
				if(client != null)
				{
					String password=client.getClientPassword();
					logger.trace("client password:"+password);
				 //Checking user entered password with database stored password
					if(password.equals(pwd))//If password matches
					{
				  
						logger.trace("gov emp password matched");
				  //create session for that client and set userType,client and clientId as session attributes
						HttpSession session=request.getSession(true);
						session.setAttribute("userType", "Government employee");
						session.setAttribute("client", client);
						session.setAttribute("clientId", loginIdInt);
		    	  //Send control to clientPageDetils
						RequestDispatcher requestDispatcher=request.getRequestDispatcher("ClientPageDetails");
						requestDispatcher.forward(request, response);
				 }
				}
					 
				 else  //If password does not matches send error message and redirect to loginPage.jsp
			      { 
			    	  
			    	  RequestDispatcher requestDispatcher=request.getRequestDispatcher("LoginPage.jsp");
			    	  response.getWriter().append("Wrong user id OR password Or User Type");
			    	  requestDispatcher.include(request, response);
			      }
			 }
		//Login check for vendor
			 else if(type.equalsIgnoreCase("vendor"))
			 {
				 logger.trace("in type vendor");

				 VendorService vendorService=new VendorService();
				 Vendor vendor = vendorService.getVendor(loginIdInt);
				 if(vendor !=null)
				 {
					 String password=vendor.getVendorPassword();
					 logger.trace("system passsword "+password);
					 
					 //checking if entered password is right
					 if(password.equals(pwd))
					 {
						 logger.trace("vendor password matched");
						 HttpSession session=request.getSession(true);
						 
						 //create session and add usertype,vendor and vendorId as session Attribute
						 session.setAttribute("userType","Vendor");
						 session.setAttribute("vendor", vendor);
						 session.setAttribute("vendorId", loginId);
						 RequestDispatcher requestDispatcher=request.getRequestDispatcher("FetchPlacedOrders");
						 requestDispatcher.forward(request, response);
				 }
				 }
			
		      else  //If password is wrong send it to loginPage.html
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

		
				
		doGet(request, response);
	}

}
