package com.Servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.Service.OrderService;
import com.al.dao.OrderDao;
import com.al.dao.OrderDaoImpl;
import com.al.model.Order;

/**
 * Servlet implementation class PlaceOrder
 */
@WebServlet("/PlaceOrder")
public class PlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger=Logger.getRootLogger();  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		 try {
		HttpSession session=request.getSession(false);
		
		
		//Get the count of products selected and the clientId of the user who is logged in
		Object count = session.getAttribute("Count");
		Object clientIdObj=session.getAttribute("clientId");
		Integer clientId = (Integer) clientIdObj; 
		
		
		Object productIdObj = session.getAttribute("productId");
		Integer productId = (Integer) productIdObj; 
		OrderService orderService=new OrderService();
		Integer countInt=(Integer)count;
		logger.info(countInt);
		
		//Accept the quantity ,Order Placed date and Deadline from the user using parameters
		String parameter = request.getParameter("quantity1");
		logger.info("quantity1"+ parameter);
		for(int i=1;i<=countInt;i++)
		{
			
			//Multiple orders can be placed at same time so,text boxes can accept parameters for more than one order
			//Counter is added to names of textboxes to accept multiple orders at same time
			String quantity="quantity"+i;
			String OrderPlacedDate="OrderPlacedDate"+i;
			String DeadLine="deadLine"+i;
			
			
			String quantityRequired = request.getParameter(quantity);
			String orderPlacedDate = request.getParameter(OrderPlacedDate);
			String deadLine = request.getParameter(DeadLine);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			
			
			Date orderPlacedDateParse;
			Date deadLineDateParse;
			try {
				
			orderPlacedDateParse = sdf.parse(orderPlacedDate);//just for exception handling
			deadLineDateParse = sdf.parse(deadLine);
							
			logger.info(quantityRequired+orderPlacedDate+deadLine);
			 
			//Get the last orderId from database and set the new orderId as plus one of last order entry
			List<Order> allOrders = orderService.getAllOrders();
			int size = allOrders.size()-1;
			int orderId = allOrders.get(size).getOrderId();
			orderId++;
			
			//Place order with user entered inputs
			orderService.getOrderFromUser(orderId, clientId,productId, Integer.parseInt(quantityRequired),orderPlacedDate, deadLine);
			} catch (ParseException e) 
			{
				// TODO Auto-generated catch block
				logger.info("Order con nont be placed  invalid date");
				String exceptionName = "Order can not be placed due to invalid entries";
				request.setAttribute( "exceptionName",exceptionName);
				String OriginPage = "PlaceOrder.jsp";
				request.setAttribute("OriginPage", OriginPage);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ExceptionPage.jsp");
				requestDispatcher.forward(request, response);
			
				
				//should go back to PlaceOrder.jsp
			}
			
		}
		
		//Get list of orders placed by a particular client and set it as request attribute 
		List<Order> orderOfParticularClient = orderService.getOrderOfParticularClient(clientId);
		session.setAttribute("allOrders",orderOfParticularClient);
		
		//To view the placed orders redirect to ViewOrders.jsp page
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("ViewOrders.jsp");
		requestDispatcher.include(request, response);
		 } catch (NumberFormatException | IllegalStateException e) {
				// TODO Auto-generated catch block
				logger.info("Order could'nt be placed  please enter valid entries");
				String exceptionName = "Order can not be placed due to invalid entries";
				request.setAttribute( "exceptionName",exceptionName);
				String OriginPage = "PlaceOrder.jsp";
				request.setAttribute("OriginPage", OriginPage);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ExceptionPage.jsp");
				try {
					requestDispatcher.forward(request, response);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//should go back to PlaceOrder.jsp
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
