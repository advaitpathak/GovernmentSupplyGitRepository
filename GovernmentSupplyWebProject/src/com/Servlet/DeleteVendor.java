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
import com.Service.VendorService;
import com.al.dao.ClientDaoImpl;
import com.al.dao.VendorDaoImpl;
import com.al.model.Client;
import com.al.model.Vendor;

/**
 * Servlet implementation class DeleteVendor
 */
@WebServlet("/DeleteVendor")
public class DeleteVendor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteVendor() {
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
			try {
				VendorService vendorService = new VendorService();
				HttpSession session = request.getSession();
				String vendorIdStr = request.getParameter("deleteVendorId");
				Integer vendorId = Integer.parseInt(vendorIdStr);
				vendorService.deleteVendor(new VendorDaoImpl().getVendor(vendorId));
				List<Vendor> allVendorsList = vendorService.getAllVendors();
				session.setAttribute("allVendorList", allVendorsList);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/AllAvailableVendors.jsp");
				requestDispatcher.forward(request, response);
			} catch (NumberFormatException | NullPointerException e) {
				// TODO Auto-generated catch block
				System.out.println("Failed to delete Vendor. Invalid VendorId");
				String exceptionName = "Failed to delete Vendor. Invalid VendorId";
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
