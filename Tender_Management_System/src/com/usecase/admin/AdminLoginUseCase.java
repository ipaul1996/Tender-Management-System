package com.usecase.admin;

import java.util.Scanner;

import com.bean.Admin;
import com.dao.AdminDao;
import com.dao.AdminDaoImpl;
import com.exception.AdminException;

public class AdminLoginUseCase {

	public Admin login() {
		
		Admin admin = null;
		
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter username : ");
			String u = sc.nextLine();
			
			System.out.println("Enter password : ");
			String p = sc.nextLine();
			
			AdminDao dao = new AdminDaoImpl();
						
			admin = dao.adminLogin(u, p);		
			
			System.out.println("\n-: Welcome " + admin.getName() + " :-");
			
		} catch (AdminException ae) {
			System.out.println(ae.getMessage());
		} 
		
		return admin;
	}
}
