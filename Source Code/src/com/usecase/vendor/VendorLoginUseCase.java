package com.usecase.vendor;

import java.util.Scanner;

import com.bean.Vendor;
import com.dao.VendorDao;
import com.dao.VendorDaoImpl;
import com.exception.VendorException;

public class VendorLoginUseCase {
	
	public Vendor login() {
		
		Vendor vendor = null;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter username : ");
		String uname = sc.nextLine();
		
		System.out.println("Enter password : ");
		String pass = sc.nextLine();
		
		VendorDao dao = new VendorDaoImpl();
		
		try {
			
			vendor = dao.vendorLogin(uname, pass);
			System.out.println("\n-: Welcome " + vendor.getName() + " :-");
			
		} catch (VendorException e) {
			
			System.out.println(e.getMessage());
		}
		
		
		return vendor;
	}

}
