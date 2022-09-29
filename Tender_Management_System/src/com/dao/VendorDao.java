package com.dao;

import com.Exception.VendorException;
import com.bean.Vendor;

public interface VendorDao {
	
	public Vendor vendorLogin(String username, String password) throws VendorException;

}
