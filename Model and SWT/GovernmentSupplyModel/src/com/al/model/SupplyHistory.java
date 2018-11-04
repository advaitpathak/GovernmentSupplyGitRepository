package com.al.model;

import java.io.Serializable;

public class SupplyHistory implements Serializable
{
	
	int historyID;
	Quotation quotation;
	Vendor vendor;
	int delay;
	public int getHistoryID() {
		return historyID;
	}
	public void setHistoryID(int historyID) {
		this.historyID = historyID;
	}
	public Quotation getQuotation() {
		return quotation;
	}
	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	public SupplyHistory(int historyID, Quotation quotation, Vendor vendor, int delay) {
		super();
		this.historyID = historyID;
		this.quotation = quotation;
		this.vendor = vendor;
		this.delay = delay;
	}
	public SupplyHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SupplyHistory [historyID=" + historyID + ", quotation=" + quotation + ", vendor=" + vendor + ", delay="
				+ delay + "]";
	}
	public boolean equals(Object ob)
	{
		try
		{
			SupplyHistory supplyHistory = (SupplyHistory) ob;
			if(this.historyID == supplyHistory.historyID)
				return true;
			return false;
			
		}
		catch(Exception ex)
		{
			return super.equals(ob);
		}
	}
}
