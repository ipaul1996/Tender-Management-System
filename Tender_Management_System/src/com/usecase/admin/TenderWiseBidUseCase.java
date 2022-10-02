package com.usecase.admin;

import java.util.List;
import java.util.Scanner;

import com.bean.BidTenderVendorDTO;
import com.dao.AdminDao;
import com.dao.AdminDaoImpl;
import com.exception.BidException;

public class TenderWiseBidUseCase {
	
	public  void showBids() {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			System.out.println("Enter tender id : ");
			int ti = sc.nextInt();
		
			AdminDao dao = new AdminDaoImpl();
		
		
			List<BidTenderVendorDTO> list = dao.tenderWiseBid(ti);
			
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			list.forEach(b -> {
				
				System.out.println("Tender Name : " + b.getTendername());
				System.out.println("Vendor Id : " + b.getVendorid());
				System.out.println("Vendor Name : " + b.getVendorname());
				System.out.println("Bid Price : " + b.getBidprice());
				
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				
				
			});
			
			
		} catch (BidException e) {
			
			System.out.println(e.getMessage());
			
		} catch(Exception e) {
			
			System.out.println("Enter valid input");
		}
		
	}

}
