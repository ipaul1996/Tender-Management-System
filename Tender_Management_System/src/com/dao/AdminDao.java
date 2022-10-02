package com.dao;

import java.util.List;

import com.bean.Admin;
import com.bean.BidTenderVendorDTO;
import com.bean.Tender;
import com.bean.Vendor;
import com.exception.AdminException;
import com.exception.BidException;
import com.exception.TenderException;
import com.exception.VendorException;

public interface AdminDao {
	
	public Admin adminLogin(String username, String password) throws AdminException;
	
	public String registerVendor(Vendor vendor);
	
	public List<Vendor> showVendorList() throws VendorException;
	
	public String createTender(Tender tender); 
	
	public List<Tender> showTenderList() throws TenderException;
	
	public List<BidTenderVendorDTO> tenderWiseBid(int tenderid) throws BidException;

	public String assignTender(int tenderid);
}
