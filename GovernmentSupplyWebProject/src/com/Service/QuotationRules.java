package com.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.al.model.Quotation;
import com.al.model.Vendor;

public class QuotationRules {
	QuotationService quotationService = new QuotationService();
	VendorService vendorService = new VendorService();
	
	
	public int rule1_50(List<Quotation> quotationList)
	{	
		List<Quotation> rule1List1 = new ArrayList<Quotation>();
		List<Vendor> vendorRatingList= new ArrayList<Vendor>();
		List<Vendor> MatchingRatingVendorList= new ArrayList<Vendor>();
		List<Quotation> quotationWithSameVendorRating = new ArrayList<Quotation>();
		List<Quotation> MatchingDeliveryQuoteList = new ArrayList<Quotation>();
		List<Vendor>vendorWithSameDeliveryDate = new ArrayList<Vendor>();
		List<Vendor> vendorWithSameEstdDate = new ArrayList<>();
		
		
		
		List<Quotation> lowestQuotedCostFisrt = quotationService.lowestQuotedCostFisrt(quotationList);
		int quoteCost = lowestQuotedCostFisrt.get(0).getQuoteCost();
		for(Quotation q:lowestQuotedCostFisrt)
		{
			if(quoteCost==q.getQuoteCost())
			{
				rule1List1.add(q);
			}
		}
		int list1Size = rule1List1.size();
		if(list1Size==1)//if rule 1 succeeds
		{
			return rule1List1.get(0).getVendor().getVendorId();
		}
		else//if rule1 clashes
		{	
			for(Quotation q:rule1List1)
			{
				 vendorRatingList.add(q.getVendor());
			}
			List<Vendor> bestVendorFirst = vendorService.bestVendorFirst(vendorRatingList);
			int vendorRating = bestVendorFirst.get(0).getVendorRating();
			for(Vendor v:bestVendorFirst)
			{
				if(vendorRating==v.getVendorRating())
				{
					MatchingRatingVendorList.add(v);
				}
			}
			int ratingListSize = MatchingRatingVendorList.size();
			if(ratingListSize==1)//if rule 2 succeeds
			{
				return MatchingRatingVendorList.get(0).getVendorId();
			}
			else//if rule 2 clashes
			{
				
				for(Vendor v:MatchingRatingVendorList)
				{
					for(Quotation q:rule1List1)
					{
						if(q.getVendor().getVendorId()==v.getVendorId())
						{
							quotationWithSameVendorRating.add(q);
						}
					}
				}
				List<Quotation> earliestDelivery = quotationService.earliestDelivery(quotationWithSameVendorRating);
				String estimateDeliveryDate = quotationWithSameVendorRating.get(0).getEstimateDeliveryDate();
				for(Quotation q:earliestDelivery)
				{
					if(estimateDeliveryDate==q.getEstimateDeliveryDate())
					{
						MatchingDeliveryQuoteList.add(q);
					}
				}
				int MatchingDeliveryListSize = MatchingDeliveryQuoteList.size();
				if(MatchingDeliveryListSize==1)//if rule 3 succeeds
				{
					return MatchingDeliveryQuoteList.get(0).getVendor().getVendorId();
				}
				else//if rule 3 clashes
				{
					for(Quotation q:MatchingDeliveryQuoteList)
					{
						vendorWithSameDeliveryDate.add(q.getVendor());
					}
					List<Vendor> oldestVendor = vendorService.oldestVendor(vendorWithSameDeliveryDate);
					String establishedDate = oldestVendor.get(0).getEstablishedDate();
					for(Vendor v:oldestVendor)
					{
						if(establishedDate.compareTo(v.getEstablishedDate())==0)
						{
							vendorWithSameEstdDate.add(v);
						}
					}
					int SizeOfvendorWithsameEstdDate = vendorWithSameEstdDate.size();
					if(SizeOfvendorWithsameEstdDate==1)//if rule 4 succeeds
					{
						return vendorWithSameEstdDate.get(0).getVendorId();
					}
					else//if rule 4 clashes
					{
						//Show vendorWithSameEstdDate on jsp page and return vendor Id of vender selected by Client
						Random random = new Random();
						int nextInt = random.nextInt(vendorWithSameEstdDate.size()-1); 
						return vendorWithSameEstdDate.get(nextInt).getVendorId();
					}
				}
			}
		}
	}
	
	
	public int rule2_30(List<Quotation> quotationList)
	{
		List<Quotation> rule1List1 = new ArrayList<Quotation>();
		List<Vendor> vendorRatingList= new ArrayList<Vendor>();
		List<Vendor> MatchingRatingVendorList= new ArrayList<Vendor>();
		List<Quotation> quotationWithSameVendorRating = new ArrayList<Quotation>();
		List<Quotation> MatchingDeliveryQuoteList = new ArrayList<Quotation>();
		List<Vendor>vendorWithSameDeliveryDate = new ArrayList<Vendor>();
		List<Vendor> vendorWithSameEstdDate = new ArrayList<>();
		
		
			for(Quotation q:quotationList)
			{
				 vendorRatingList.add(q.getVendor());
			}
			List<Vendor> bestVendorFirst = vendorService.bestVendorFirst(vendorRatingList);
			int vendorRating = bestVendorFirst.get(0).getVendorRating();
			for(Vendor v:bestVendorFirst)
			{
				if(vendorRating==v.getVendorRating())
				{
					MatchingRatingVendorList.add(v);
				}
			}
			int ratingListSize = MatchingRatingVendorList.size();
			if(ratingListSize==1)//if rule 2 succeeds
			{
				return MatchingRatingVendorList.get(0).getVendorId();
			}
			else//if rule 2 clashes
			{
				
				for(Vendor v:MatchingRatingVendorList)
				{
					for(Quotation q:rule1List1)
					{
						if(q.getVendor().getVendorId()==v.getVendorId())
						{
							quotationWithSameVendorRating.add(q);
						}
					}
				}
				List<Quotation> earliestDelivery = quotationService.earliestDelivery(quotationWithSameVendorRating);
				String estimateDeliveryDate = quotationWithSameVendorRating.get(0).getEstimateDeliveryDate();
				for(Quotation q:earliestDelivery)
				{
					if(estimateDeliveryDate==q.getEstimateDeliveryDate())
					{
						MatchingDeliveryQuoteList.add(q);
					}
				}
				int MatchingDeliveryListSize = MatchingDeliveryQuoteList.size();
				if(MatchingDeliveryListSize==1)//if rule 3 succeeds
				{
					return MatchingDeliveryQuoteList.get(0).getVendor().getVendorId();
				}
				else//if rule 3 clashes
				{
					for(Quotation q:MatchingDeliveryQuoteList)
					{
						vendorWithSameDeliveryDate.add(q.getVendor());
					}
					List<Vendor> oldestVendor = vendorService.oldestVendor(vendorWithSameDeliveryDate);
					String establishedDate = oldestVendor.get(0).getEstablishedDate();
					for(Vendor v:oldestVendor)
					{
						if(establishedDate.compareTo(v.getEstablishedDate())==0)
						{
							vendorWithSameEstdDate.add(v);
						}
					}
					int SizeOfvendorWithsameEstdDate = vendorWithSameEstdDate.size();
					if(SizeOfvendorWithsameEstdDate==1)//if rule 4 succeeds
					{
						return vendorWithSameEstdDate.get(0).getVendorId();
					}
					else//if rule 4 clashes
					{
						//Show vendorWithSameEstdDate on jsp page and return vendor Id of vender selected by Client
						Random random = new Random();
						int nextInt = random.nextInt(vendorWithSameEstdDate.size()-1); 
						return vendorWithSameEstdDate.get(nextInt).getVendorId();
					}
				}
			}
		
}
	public int rule3_20(List<Quotation> quotationList)
	{
		List<Quotation> quotationWithSameVendorRating = new ArrayList<Quotation>();
		List<Quotation> MatchingDeliveryQuoteList = new ArrayList<Quotation>();
		List<Vendor>vendorWithSameDeliveryDate = new ArrayList<Vendor>();
		List<Vendor> vendorWithSameEstdDate = new ArrayList<>();
		
		
			
				List<Quotation> earliestDelivery = quotationService.earliestDelivery(quotationList);
				String estimateDeliveryDate = quotationWithSameVendorRating.get(0).getEstimateDeliveryDate();
				for(Quotation q:earliestDelivery)
				{
					if(estimateDeliveryDate==q.getEstimateDeliveryDate())
					{
						MatchingDeliveryQuoteList.add(q);
					}
				}
				int MatchingDeliveryListSize = MatchingDeliveryQuoteList.size();
				if(MatchingDeliveryListSize==1)//if rule 3 succeeds
				{
					return MatchingDeliveryQuoteList.get(0).getVendor().getVendorId();
				}
				else//if rule 3 clashes
				{
					for(Quotation q:MatchingDeliveryQuoteList)
					{
						vendorWithSameDeliveryDate.add(q.getVendor());
					}
					List<Vendor> oldestVendor = vendorService.oldestVendor(vendorWithSameDeliveryDate);
					String establishedDate = oldestVendor.get(0).getEstablishedDate();
					for(Vendor v:oldestVendor)
					{
						if(establishedDate.compareTo(v.getEstablishedDate())==0)
						{
							vendorWithSameEstdDate.add(v);
						}
					}
					int SizeOfvendorWithsameEstdDate = vendorWithSameEstdDate.size();
					if(SizeOfvendorWithsameEstdDate==1)//if rule 4 succeeds
					{
						return vendorWithSameEstdDate.get(0).getVendorId();
					}
					else//if rule 4 clashes
					{
						//Show vendorWithSameEstdDate on jsp page and return vendor Id of vender selected by Client
						Random random = new Random();
						int nextInt = random.nextInt(vendorWithSameEstdDate.size()-1); 
						return vendorWithSameEstdDate.get(nextInt).getVendorId();
					}
				}
		}

}
