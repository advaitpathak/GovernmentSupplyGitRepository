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
		System.out.println("orderID in service= "+orderId);
		AcceptedQuotesService acceptedQuotesService = new AcceptedQuotesService();
		List<Quotation> MatchingQuotationList=new ArrayList<Quotation>();
		QuotationDao quotationDao=new QuotationDaoImpl();
		List<Quotation> allQuotation = quotationDao.getAllQuotation();
		for(Quotation q:allQuotation)
		{
			if(orderId == q.getOrder().getOrderId())
			{
				MatchingQuotationList.add(q);
				System.out.println(q);
			}
		}
		QuotationRules quotationRules=new QuotationRules();
		int order50_vendorId = quotationRules.rule1_50(MatchingQuotationList);
		System.out.println("order50_vendorId= " +order50_vendorId);
		int order30_vendorId = quotationRules.rule2_30(MatchingQuotationList);
		System.out.println("order30_vendorId= " +order30_vendorId);
		int order20_vendorId = quotationRules.rule3_20(MatchingQuotationList);
		System.out.println("order20_vendorId= " +order20_vendorId);
		OrderDao orderDao = new OrderDaoImpl();
		Order order = orderDao.getOrder(orderId);
		int quantityRequired = order.getQuantityRequired();
		int quoteCost;
		if(order50_vendorId==order30_vendorId && order50_vendorId==order20_vendorId)
			{
				//assign 100 order quantity to that vendor
//				int quoteCost = MatchingQuotationList.get(order50_vendorId).getQuoteCost();
//				quoteCost=MatchingQuotationList.get
			for(Quotation q:MatchingQuotationList)
			{
				if(order50_vendorId == q.getVendor().getVendorId())
				{
					quoteCost = q.getQuoteCost();
					acceptedQuotesService.addAcceptedQuote(orderId, order50_vendorId, quantityRequired, quoteCost);
				}
			}
				
			}
		else if(order50_vendorId==order30_vendorId)
			{
				//assign 80 % order quantity to that vendor
//				int quoteCost = MatchingQuotationList.get(order50_vendorId).getQuoteCost();
			for(Quotation q:MatchingQuotationList)
			{
				if(order50_vendorId == q.getVendor().getVendorId())
				{
					quoteCost = q.getQuoteCost();
					acceptedQuotesService.addAcceptedQuote(orderId, order50_vendorId, (quantityRequired-(quantityRequired*20/100)), (quoteCost-(quoteCost*20/100)));
				}
			}
				
				//assign 20% order quantity to order20_vendor
//				quoteCost = MatchingQuotationList.get(order20_vendorId).getQuoteCost();
				for(Quotation q:MatchingQuotationList)
				{
					if(order20_vendorId == q.getVendor().getVendorId())
					{
						quoteCost = q.getQuoteCost();
						acceptedQuotesService.addAcceptedQuote(orderId, order20_vendorId, (quantityRequired*20/100), (quoteCost*20/100));
					}
				}
				
			}
		else if(order50_vendorId==order20_vendorId)
			{
				//assign 70% order quantity to that vendor
//				int quoteCost = MatchingQuotationList.get(order50_vendorId).getQuoteCost();
			for(Quotation q:MatchingQuotationList)
			{
				if(order50_vendorId == q.getVendor().getVendorId())
				{
					quoteCost = q.getQuoteCost();
					acceptedQuotesService.addAcceptedQuote(orderId, order50_vendorId, (quantityRequired-(quantityRequired*30/100)), (quoteCost-(quoteCost*30/100)));
				}
			}
				
				//assign 30% order Quantity to order30_vendor
//				quoteCost = MatchingQuotationList.get(order30_vendorId).getQuoteCost();
				for(Quotation q:MatchingQuotationList)
				{
					if(order30_vendorId == q.getVendor().getVendorId())
					{
						quoteCost = q.getQuoteCost();
						acceptedQuotesService.addAcceptedQuote(orderId, order30_vendorId, (quantityRequired*30/100), (quoteCost*30/100));
					}
				}
				
			}
		else if(order30_vendorId==order20_vendorId)
			{
			System.out.println("I am in right if");
				//assign 50% order to that vendor
//				int quoteCost = MatchingQuotationList.get(order30_vendorId).getQuoteCost();
			for(Quotation q:MatchingQuotationList)
			{
				if(order30_vendorId == q.getVendor().getVendorId())
				{
					System.out.println("I am in for loop");
					quoteCost = q.getQuoteCost();
					acceptedQuotesService.addAcceptedQuote(orderId, order30_vendorId, (quantityRequired-(quantityRequired*50/100)), (quoteCost-(quoteCost*50/100)));
				}
			}
				
				//assign 50% order to order50_vendor
//				quoteCost = MatchingQuotationList.get(order50_vendorId).getQuoteCost();
				for(Quotation q:MatchingQuotationList)
				{
					if(order50_vendorId == q.getVendor().getVendorId())
					{
						quoteCost = q.getQuoteCost();
						acceptedQuotesService.addAcceptedQuote(orderId, order50_vendorId, (quantityRequired*50/100), (quoteCost*50/100));
					}
				}
				
			}
		else
			{
				//assign 50% order quantity to order50_vendor
//				int quoteCost = MatchingQuotationList.get(order50_vendorId).getQuoteCost();
			for(Quotation q:allQuotation)
			{
				if(order50_vendorId == q.getVendor().getVendorId())
				{
					quoteCost = q.getQuoteCost();
					acceptedQuotesService.addAcceptedQuote(orderId, order50_vendorId, (quantityRequired-(quantityRequired*30/100)-(quoteCost*20/100)), (quoteCost-(quantityRequired*30/100)-(quantityRequired*20/100)));
				}
			}
				
				//assign 30% order Quantity to order30_vendor
//				quoteCost = MatchingQuotationList.get(order30_vendorId).getQuoteCost();
				for(Quotation q:MatchingQuotationList)
				{
					if(order30_vendorId == q.getVendor().getVendorId())
					{
						quoteCost = q.getQuoteCost();
						acceptedQuotesService.addAcceptedQuote(orderId, order30_vendorId, (quantityRequired*30/100), (quoteCost*30/100));
					}
				}
				
				//assign 20% order quantity to order20_vendor
//				quoteCost = MatchingQuotationList.get(order20_vendorId).getQuoteCost();
				for(Quotation q:MatchingQuotationList)
				{
					if(order20_vendorId == q.getVendor().getVendorId())
					{
						quoteCost = q.getQuoteCost();
						acceptedQuotesService.addAcceptedQuote(orderId, order20_vendorId, (quantityRequired*20/100), (quoteCost*20/100));
					}
				}
				
			}

	}
}
