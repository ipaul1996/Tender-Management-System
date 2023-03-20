package com.usecase.admin;

import java.util.List;

import com.bean.Tender;
import com.dao.AdminDao;
import com.dao.AdminDaoImpl;
import com.exception.TenderException;

public class GetAllTendersUseCase {
	
	public void viewTenders() {
		
		AdminDao dao = new AdminDaoImpl();
		
		try {
			
			List<Tender> list = dao.showTenderList();
			
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			list.forEach(t -> {
				System.out.println("Tender Id : " + t.getTenderid());
				System.out.println("Tender Name : " + t.getTendername());
				System.out.println("Base Price : " + t.getBaseprice());
				System.out.println("Tender Status : " + t.getStatus());
				System.out.println("Tender Allocated to : " + t.getAllocatedvendorid());
				
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				
			});
			
			
		} catch (TenderException e) {
			
			System.out.println(e.getMessage());
		}
	}

}
