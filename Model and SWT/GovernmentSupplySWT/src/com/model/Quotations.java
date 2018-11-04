package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class contains a function that returns the list of Quotation
 * @author Administrator
 *
 */
@XmlRootElement (name="quotations")
public class Quotations {
	
	private List<Quotation> quotationList=new ArrayList<Quotation>();
	
		
	public List<Quotation> getQuotationList() {
		return quotationList;
	}
	
	@XmlElement (name="quotation")
	public void setQuotationList(List<Quotation> quotationList) {
		this.quotationList = quotationList;
	}
	public void add(Quotation quotation)
	{
		if(quotationList == null)
		{
			quotationList = new ArrayList<>();
		}
		quotationList.add(quotation);
	}
	
	
	 
	
}
