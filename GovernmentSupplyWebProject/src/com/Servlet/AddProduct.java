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

import com.Service.ProductService;
import com.al.dao.ProductExistException;
import com.al.dao.SectionDaoImpl;
import com.al.model.Product;


/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getRootLogger();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
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
				Object productIdObj = session.getAttribute("addProductId");
				Integer productId = Integer.parseInt(productIdObj.toString());
				String productNameStr = request.getParameter("productName");
				String productCostStr = request.getParameter("productCost");
				Integer productCost = Integer.parseInt(productCostStr);
				String sectionIdStr = request.getParameter("sectionId");
				Integer sectionId = Integer.parseInt(sectionIdStr);
				String productDetailStr = request.getParameter("productDetail");

				ProductService productService = new ProductService();
				try 
				{
					productService.addProduct(new Product(productId, productNameStr, productCost, productDetailStr, new SectionDaoImpl().getSection(sectionId)));
				} 
				catch (ProductExistException e) 
				{
					e.printStackTrace();
				}
				List<Product> allProductList = productService.getAllProduct();
				session.setAttribute("allProductList", allProductList);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/AllAvailableProducts.jsp");
				requestDispatcher.forward(request, response);
			} catch (NumberFormatException | NullPointerException e) {
	
				logger.info("Failed to add Product. Please enter valid data");
				String exceptionName = "Failed to add Product. Please enter valid data";
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
