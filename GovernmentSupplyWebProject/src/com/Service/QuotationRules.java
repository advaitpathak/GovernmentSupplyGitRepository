package com.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.al.model.Quotation;
import com.al.model.Vendor;
/**
 * 
 * @author Administrator
 *class @QuotationRules
 *provides all the rules to decide which Quote should be selected
 */
public class QuotationRules {
	QuotationService quotationService = new QuotationService();
	VendorService vendorService = new VendorService();
	
	/**
	 * Method @rule1_50(List<Quotation> quotationList)
	 * this method is use to select the vendor to whom 50% of order quantity will be assigned
	 * Priority to select the vendor is as follows
	 * 1.Vendor with Lowest Quote cost
	 * if 1st rule gives more than 1 vendor then
	 * 2.Vendor with Highest ratings
	 * if 2nd rule gives more than 1 vendor then
	 * 3.Vendor with Earliest delivery date
	 * if 3rd rule gives more than 1 vendor then
	 * 4.Vendor with oldest established date
	 * if 4th rule gives more than 1 vendor then
	 * 5.If clash occurs with all above rules then Vendor will be selected randomly
	 
	 * @param quotationList
	 * @return VendorId to whom 50% order quantity will be assigned
	 */
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
	
	/**
	 * Method @rule2_30(List<Quotation> quotationList)
	 * this method is use to select the vendor to whom 30% of order quantity will be assigned
	 * Priority to select the vendor is as follows
	 * 1.Vendor with Highest ratings
	 * if 1st rule gives more than 1 vendor then
	 * 2.Vendor with Earliest delivery date
	 * if 2nd rule gives more than 1 vendor then
	 * 3.Vendor with oldest established date
	 * if 3rd rule gives more than 1 vendor then
	 * 4.If clash occurs with all above rules then Vendor will be selected randomly
	 * @param quotationList
	 * @return VendorId to whom 30% order quantity will be assigned
	 */
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
	/**
	 * Method @rule3_20(List<Quotation> quotationList)
	 * this method is use to select the vendor to whom 20% of order quantity will be assigned
	 * Priority to select the vendor is as follows
	 * 1.Vendor with Earliest delivery date
	 * if 1st rule gives more than 1 vendor then
	 * 2.Vendor with oldest established date
	 * if 2nd rule gives more than 1 vendor then
	 * 3.If clash occurs with all above rules then Vendor will be selected randomly
	 * @param quotationList
	 * @return VendorId to whom 20% order quantity will be assigned
	 */
	public int rule3_20(List<Quotation> quotationList)
	{
		List<Quotation> quotationWithSameVendorRating = new ArrayList<Quotation>();
		List<Quotation> MatchingDeliveryQuoteList = new ArrayList<Quotation>();
		List<Vendor>vendorWithSameDeliveryDate = new ArrayList<Vendor>();
		List<Vendor> vendorWithSameEstdDate = new ArrayList<>();
		
		
			
				List<Quotation> earliestDelivery = quotationService.earliestDelivery(quotationList);
				String estimateDeliveryDate = earliestDelivery.get(0).getEstimateDeliveryDate();
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
