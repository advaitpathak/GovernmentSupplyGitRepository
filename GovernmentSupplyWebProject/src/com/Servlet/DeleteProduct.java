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
import com.al.dao.ProductDaoImpl;
import com.al.dao.ProductExistException;
import com.al.dao.SectionDaoImpl;
import com.al.model.Product;

/**
 * Servlet implementation class DeleteClient
 */
@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProduct() {
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
			System.out.println("IN");
			ProductService productService = new ProductService();
			HttpSession session = request.getSession();
			String productIdStr = request.getParameter("deleteProductId");
			Integer productId = Integer.parseInt(productIdStr);
			productService.deleteProduct(new ProductDaoImpl().getProduct(productId));
			List<Product> allProductList = productService.getAllProduct();
			session.setAttribute("allProductList", allProductList);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/AllAvailableProducts.jsp");
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
