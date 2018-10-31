package com.ws.rest;

import java.util.List;



import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.Service.QuotationService;
import com.al.model.Quotation;

@Path ("/Quotations")
public class GetAllQuotations {

	QuotationService quoteService=new QuotationService();
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Quotation> getQuotations()
	{
		 
		return quoteService.getAllQuotation();
	}
	
	
}
