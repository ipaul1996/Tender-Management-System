package com.usecase.vendor;

import com.bean.Bid;
import com.bean.Vendor;
import com.dao.VendorDao;
import com.dao.VendorDaoImpl;
import com.exception.BidException;

public class PlaceBidUseCase {
	
	public void placingBid(Vendor vendor) {
		
		VendorDao dao = new VendorDaoImpl();
		
		try {
			
			Bid bid = dao.placeBid(vendor);
			
			if(bid != null) {
				
				System.out.println("Bid has been placed successfully..!");
				
			} 
						
			
		} catch (BidException e) {
			
			System.out.println(e.getMessage());
		}
	}

}
