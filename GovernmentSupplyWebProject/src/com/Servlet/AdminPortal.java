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
import com.Service.OrderService;
import com.Service.ProductService;
import com.Service.QuotationService;
import com.Service.VendorService;
import com.al.model.Client;
import com.al.model.Product;
import com.al.model.Quotation;
import com.al.model.Vendor;

/**
 * Servlet implementation class AdminPortal
 */
@WebServlet("/AdminPortal")
public class AdminPortal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPortal() {
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
			ProductService productService = new ProductService();
			List<Product> allProductList = productService.getAllProduct();
			int productListSize = allProductList.size();
			Product product = allProductList.get(productListSize-1);
			int addProductId = product.getProductId();
			session.setAttribute("addProductId", addProductId+1);

			ClientService clientService = new ClientService();
			List<Client> allClientList = clientService.getAllClient();
			int clientListSize = allClientList.size();
			Client client = allClientList.get(clientListSize-1);
			int addClientId = client.getClientId();
			session.setAttribute("addClientId", addClientId+1);

			VendorService vendorService = new VendorService();
			List<Vendor> allVendorList = vendorService.getAllVendors();
			int vendorListSize = allVendorList.size();
			Vendor vendor = allVendorList.get(vendorListSize-1);
			int addVendorId = vendor.getVendorId();
			session.setAttribute("addVendorId", addVendorId+1);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminPortal.jsp");
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
