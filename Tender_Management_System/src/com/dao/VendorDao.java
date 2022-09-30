package com.dao;

import java.util.List;

import com.Exception.BidException;
import com.Exception.TenderException;
import com.Exception.VendorException;
import com.bean.Bid;
import com.bean.BidHist;
import com.bean.Tender;
import com.bean.Vendor;

public interface VendorDao {
	
	public Vendor vendorLogin(String username, String password) throws VendorException;
	
	public List<Tender> showTenders() throws TenderException;
	
	public Bid placeBid(Vendor vendor) throws BidException;

	public String bidAcceptence(Vendor vendor);
	
	public List<BidHist> bidHistory(Vendor vendor) throws BidException;
}
