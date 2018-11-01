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

import com.Service.ProductService;
import com.Service.VendorService;
import com.al.dao.ProductExistException;
import com.al.dao.SectionDaoImpl;
import com.al.dao.VendorExistsException;
import com.al.model.Product;
import com.al.model.Vendor;

/**
 * Servlet implementation class AddVendor
 */
@WebServlet("/AddVendor")
public class AddVendor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddVendor() {
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
			String vendorIdStr = request.getParameter("vendorId");
			Integer vendorId = Integer.parseInt(vendorIdStr);
			String vendorNameStr = request.getParameter("vendorName");
			String vendorRatingStr = request.getParameter("vendorRating");
			Integer vendorRating = Integer.parseInt(vendorRatingStr);
			String establishedDateStr = request.getParameter("establishedDate");
			String vendorPasswordStr = request.getParameter("vendorPassword");
		
		VendorService vendorService = new VendorService();
		try 
		{
			vendorService.addVendor(new Vendor(vendorId, vendorNameStr, vendorRating, establishedDateStr, vendorPasswordStr));
		} 
		catch (VendorExistsException e) 
		{
			e.printStackTrace();
		}
		List<Vendor> allVendorsList = vendorService.getAllVendors();
		session.setAttribute("allVendorList", allVendorsList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/AllAvailableVendors.jsp");
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
