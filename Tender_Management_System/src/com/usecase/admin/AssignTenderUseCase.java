package com.usecase.admin;

import java.util.Scanner;

import com.dao.AdminDao;
import com.dao.AdminDaoImpl;

public class AssignTenderUseCase {
	
	public void assign() {
		
		Scanner sc = new Scanner(System.in);
		try {
			
			new GetAllTendersUseCase().viewTenders();
			
			System.out.println("Enter the tenderid that you want to assign to a vendor");
			int tenderid = sc.nextInt();
			
			AdminDao dao = new AdminDaoImpl();
			
			String result = dao.assignTender(tenderid);
			
			System.out.println(result);
			
		} catch(Exception e) {
			
			System.out.println("Enter valid input");
		}
	}

}
