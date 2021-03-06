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

import com.Service.VendorService;
import com.al.model.Vendor;

/**
 * Servlet implementation class UpdateVendorRanking
 */
@WebServlet("/UpdateVendorRanking")
public class UpdateVendorRanking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateVendorRanking() {
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
			HttpSession session = request.getSession();
			String vendorIdToUpdateStr = request.getParameter("vendorIdToUpdate");
			Integer vendorIdToUpdate = Integer.parseInt(vendorIdToUpdateStr);
			String updatedVendorRatingStr = request.getParameter("updatedVendorRating");
			Integer updatedVendorRating = Integer.parseInt(updatedVendorRatingStr);
			if (updatedVendorRating>5)
			{
				String exceptionName = "Vendor Rating can not be more than 5";
				request.setAttribute( "exceptionName",exceptionName);
				String OriginPage = "AdminPortal.jsp";
				request.setAttribute("OriginPage", OriginPage);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ExceptionPage.jsp");
				requestDispatcher.forward(request, response);
			}
			else{
			VendorService vendorService = new VendorService();
			vendorService.updateVendorRating(updatedVendorRating, vendorService.getVendor(vendorIdToUpdate));
			List<Vendor> allVendorsList = vendorService.getAllVendors();
			session.setAttribute("allVendorList", allVendorsList);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/AllAvailableVendors.jsp");
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
