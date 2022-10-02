package com.usecase.vendor;

import java.util.List;

import com.bean.Tender;
import com.dao.VendorDao;
import com.dao.VendorDaoImpl;
import com.exception.TenderException;

public class GetAllTendersByVendorUsecase {
	
	public void viewTenders() {
		
		VendorDao dao = new VendorDaoImpl();
		
		try {
			
			List<Tender> list = dao.showTenders();
			
			System.out.println("All the current tenders are as follows: \n");
			
			list.forEach(t -> {
				
				System.out.println("Tender Id : " + t.getTenderid());
				System.out.println("Tender Name : " + t.getTendername());
				System.out.println("Base Price : " + t.getBaseprice());
				
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			});
			
		} catch (TenderException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
