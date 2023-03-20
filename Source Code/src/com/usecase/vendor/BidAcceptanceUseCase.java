package com.usecase.vendor;

import com.bean.Vendor;
import com.dao.VendorDao;
import com.dao.VendorDaoImpl;

public class BidAcceptanceUseCase {
	
	public void status(Vendor vendor) {
		
		VendorDao dao = new VendorDaoImpl();
		
		String result = dao.bidAcceptence(vendor);
		
		System.out.println(result);
	}

}
