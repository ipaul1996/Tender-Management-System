package com.app;

import java.util.Scanner;

import com.bean.Admin;
import com.bean.Vendor;
import com.usecase.admin.AdminLoginUseCase;
import com.usecase.admin.AssignTenderUseCase;
import com.usecase.admin.CreateTenderUseCase;
import com.usecase.admin.GetAllTendersUseCase;
import com.usecase.admin.GetAllVendorsUseCase;
import com.usecase.admin.RegisterVendorUseCase;
import com.usecase.admin.TenderWiseBidUseCase;
import com.usecase.vendor.BidAcceptanceUseCase;
import com.usecase.vendor.BidHistoryUseCase;
import com.usecase.vendor.GetAllTendersByVendorUsecase;
import com.usecase.vendor.VendorLoginUseCase;
import com.usecase.vendor.PlaceBidUseCase;

public class Options {
	
	public static void selectOption() {
		
		try {
			
			System.out.println("\nPlease select an option to continue : ");
			System.out.println("\n1. Admin login \n2. Vendor login \n3. Exit Application");
			
			Scanner sc = new Scanner(System.in);
			int choice1 = sc.nextInt();
			
			boolean flag = true;
			
			if(choice1 == 1) {
				
					Admin admin = new AdminLoginUseCase().login();
					
					
					if(admin != null) {
																		
							while(flag) {
									
									System.out.println("\n1. Register a vendor \n2. View Vendors "
										+ "\n3. Create a tender \n4. View all tenders "
										+ "\n5. View all bids of a tender "
										+ "\n6. Assign tender to a vendor  \n7. Logout");
														
									int choice2 = sc.nextInt();
									
									switch(choice2) {
									
										case 1: 							
											new RegisterVendorUseCase().register();
											break;
										case 2:
											new GetAllVendorsUseCase().viewVendors();
											break;
										case 3:
											new CreateTenderUseCase().create();
											break;
										case 4: 
											new GetAllTendersUseCase().viewTenders();
											break;
										case 5:
											new TenderWiseBidUseCase().showBids();
											break;
										case 6:
											new AssignTenderUseCase().assign();
											break;
										default:
											Options.selectOption();
											flag = false;
											break;									
									}
							}
					
				  } else {
					
					  Options.selectOption();
					  
				  }
					
			} else if(choice1 == 2){
				
				Vendor vendor = new VendorLoginUseCase().login();
				
						if(vendor != null) {
							
							
							while(flag) {
								
									System.out.println("\n1. View all current tenders  \n2. Place a bid against a tender "
										+ "\n3. View Status of a bid   \n4. View own bid history "
										+ "\n5. Logout");
														
									int choice3 = sc.nextInt();
									
									switch(choice3) {
									
									case 1: 							
										new GetAllTendersByVendorUsecase().viewTenders();
										break;
									case 2:
										new PlaceBidUseCase().placingBid(vendor);
										break;
									case 3:
										new BidAcceptanceUseCase().status(vendor);
										break;
									case 4: 
										new BidHistoryUseCase().getBidHistory(vendor);
										break;
									default:
										Options.selectOption();
										flag = false;
										break;									
								}
									
								
							}
							
							
							
						} else {
							
							Options.selectOption();
							
						}
				
				
				
			} else {
				
				return;
				
			}
			
			
		} catch (Exception e) {
			
			System.out.println(e);
		}
		
	}

}
