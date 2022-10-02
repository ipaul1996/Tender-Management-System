package com.usecase.vendor;

import java.util.List;

import com.bean.BidHist;
import com.bean.Vendor;
import com.dao.VendorDao;
import com.dao.VendorDaoImpl;
import com.exception.BidException;

public class BidHistoryUseCase {
	
	public  void getBidHistory(Vendor vendor) {
		
		VendorDao dao = new VendorDaoImpl();
		
		
		try {
			
			List<BidHist> list = dao.bidHistory(vendor);
			
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			list.forEach(h -> {
				System.out.println("Tender Id : " + h.getTenderid());
				System.out.println("Tender Name : " + h.getTendername());
				System.out.println("Base Price : " + h.getBaseprice());
				System.out.println("Bid Price : " + h.getBidprice());
				
				if(h.getAllocatedvendorid() == vendor.getVendorid()) {
					
					System.out.println("Bid Status : Accepted");
					
				} else {
					
					System.out.println("Bid Status : Rejected");
				}
				
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				
			});
			
		} catch (BidException e) {
			
			System.out.println(e.getMessage());
		}
	}

}
