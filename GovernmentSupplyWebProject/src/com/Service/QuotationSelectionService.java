package com.Service;
import java.util.ArrayList;
import java.util.List;

import com.al.dao.OrderDao;
import com.al.dao.OrderDaoImpl;
import com.al.dao.QuotationDao;
import com.al.dao.QuotationDaoImpl;
import com.al.dao.VendorDao;
import com.al.dao.VendorDaoImpl;
import com.al.model.Order;
import com.al.model.Quotation;
import com.al.model.Vendor;

public class QuotationSelectionService
{
	public void quotationSelection(int orderId)
	{	
		List<Quotation> MatchingQuotationList=new ArrayList<Quotation>();
		QuotationDao quotationDao=new QuotationDaoImpl();
		List<Quotation> allQuotation = quotationDao.getAllQuotation();
		for(Quotation q:allQuotation)
		{
			if(orderId == q.getOrder().getOrderId())
			{
				MatchingQuotationList.add(q);
			}
		}
		QuotationRules quotationRules=new QuotationRules();
		int order50_vendorId = quotationRules.rule1_50(MatchingQuotationList);
		int order30_vendorId = quotationRules.rule2_30(MatchingQuotationList);
		int order20_vendorId = quotationRules.rule3_20(MatchingQuotationList);
		OrderDao orderDao = new OrderDaoImpl();
		Order order = orderDao.getOrder(orderId);
		int quantityRequired = order.getQuantityRequired();
		if(order50_vendorId==order30_vendorId && order50_vendorId==order20_vendorId)
			{
				//assign 100 order quantity to that vendor
			}
		else if(order50_vendorId==order30_vendorId)
			{
				//assign 80 % order quantity to that vendor
				//assign 20% order quantity to order20_vendor
			}
		else if(order50_vendorId==order20_vendorId)
			{
				//assign 70% order quantity to that vendor
				//assign 30% order Quantity to order30_vendor
			}
		else if(order30_vendorId==order20_vendorId)
			{
				//assign 50% order to that vendor
				//assign 50% order to order50_vendor
			}
		else
			{
				//assign 50% order quantity to order50_vendor
				//assign 30% order Quantity to order30_vendor
				//assign 20% order quantity to order20_vendor
			}

	}
}
