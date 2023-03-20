package com.usecase.admin;

import java.util.Scanner;

import com.bean.Vendor;
import com.dao.AdminDao;
import com.dao.AdminDaoImpl;

public class RegisterVendorUseCase {
	
	public void register() {
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter vendor's email : ");
		String e = sc.nextLine();
		
		System.out.println("Enter vendor's password : ");
		String p = sc.nextLine();
		
		System.out.println("Enter vendor's name : ");
		String n = sc.nextLine();
		
		System.out.println("Enter vendor's contact no. : ");
		String c = sc.nextLine();
		
		Vendor vendorObj = new Vendor();
		
		vendorObj.setEmail(e);
		vendorObj.setPassword(p);
		vendorObj.setName(n);
		vendorObj.setContactno(c);
		
		AdminDao dao = new AdminDaoImpl();
		
		String result = dao.registerVendor(vendorObj);
		
		System.out.println(result);
		
		
	}

}
