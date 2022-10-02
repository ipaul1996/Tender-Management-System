package com.usecase.admin;

import java.util.List;

import com.bean.Vendor;
import com.dao.AdminDao;
import com.dao.AdminDaoImpl;
import com.exception.VendorException;

public class GetAllVendorsUseCase {
	
	public void viewVendors() {
		
		AdminDao dao = new AdminDaoImpl();
		
		try {
			
			List<Vendor> list = dao.showVendorList();
			
			
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			list.forEach(v -> {
				System.out.println("Vendor Id : " +  v.getVendorid());
				System.out.println("Vendor Name : " + v.getName());
				System.out.println("Vendor Email : " + v.getEmail());
				System.out.println("Vendor Contact No. : " + v.getContactno());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				
			});
			
		} catch (VendorException e) {
			
			System.out.println(e.getMessage());
		}
		
		
	}

}
