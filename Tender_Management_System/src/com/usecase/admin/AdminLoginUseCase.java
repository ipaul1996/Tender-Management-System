package com.usecase.admin;

import java.util.Scanner;

import com.Exception.AdminException;
import com.bean.Admin;
import com.dao.AdminDao;
import com.dao.AdminDaoImpl;

public class AdminLoginUseCase {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter username : ");
		String u = sc.nextLine();
		
		System.out.println("Enter password : ");
		String p = sc.nextLine();
		
		AdminDao dao = new AdminDaoImpl();
		
		try {
			
			Admin admin = dao.adminLogin(u, p);			
			System.out.println("\n-: Welcome " + admin.getName() + " :-");
			
		} catch (AdminException ae) {
			System.out.println(ae.getMessage());
		}
	}
}
