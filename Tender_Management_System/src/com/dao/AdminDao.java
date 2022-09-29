package com.dao;

import java.util.List;

import com.Exception.AdminException;
import com.Exception.TenderException;
import com.Exception.VendorException;
import com.bean.Admin;
import com.bean.Tender;
import com.bean.Vendor;

public interface AdminDao {
	
	public Admin adminLogin(String username, String password) throws AdminException;
	
	public String registerVendor(Vendor vendor);
	
	public List<Vendor> showVendorList() throws VendorException;
	
	public String createTender(Tender tender); 
	
	public List<Tender> showTenderList() throws TenderException;

}
