package com.usecase.admin;

import java.util.Scanner;

import com.bean.Tender;
import com.dao.AdminDao;
import com.dao.AdminDaoImpl;

public class CreateTenderUseCase {
	
	public void create() {
		
		Scanner sc = new Scanner(System.in);
		
		try {
		System.out.println("Enter tender name : ");
		String tn = sc.nextLine();
		
		System.out.println("Enter base price : ");
		int bp = sc.nextInt();
		
		Tender tender = new Tender();
		
		tender.setTendername(tn);
		tender.setBaseprice(bp);
		
		
		AdminDao dao = new AdminDaoImpl();
		
		String result = dao.createTender(tender);
		
		System.out.println(result);
		
		} catch(Exception e) {
			
			System.out.println("Enter valid input");
		}
		
	}

}
