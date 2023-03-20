package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bean.Admin;
import com.bean.Bid;
import com.bean.BidTenderVendorDTO;
import com.bean.Tender;
import com.bean.Vendor;
import com.exception.AdminException;
import com.exception.BidException;
import com.exception.TenderException;
import com.exception.VendorException;
import com.usecase.admin.GetAllTendersUseCase;
import com.utility.DBUtil;

public class AdminDaoImpl implements AdminDao {

	@Override
	public Admin adminLogin(String username, String password) throws AdminException{
		
		Admin admin = null;
		
		try(Connection conn = DBUtil.provideConnection()) {
			
		   PreparedStatement ps = conn.prepareStatement
				   ("select * from admin where email = ? and password = ?");
		   
		   ps.setString(1, username);
		   ps.setString(2, password);
		   
		   ResultSet rs = ps.executeQuery();
		   
		   if(rs.next()) {
			   
			   String e = rs.getString("email");
			   String p = rs.getString("password");
			   String n = rs.getString("name");
			   
			   admin = new Admin(e, p, n);
			   
			   
		   } else {
			   
			   throw new AdminException("Invalid username or password");
		   }
			
			
		} catch(SQLException se) {
			
			throw new AdminException(se.getMessage());
			
		}
		
		return admin;
	}

	@Override
	public String registerVendor(Vendor vendor) {
		
		String message = "Sorry! unable to register the vendor";
			
			try(Connection conn = DBUtil.provideConnection()) {
				
			PreparedStatement ps = conn.prepareStatement
					("insert into vendor(email, password, name, contactno) values(?, ?, ?, ?)");
			
			ps.setString(1, vendor.getEmail());
			ps.setString(2, vendor.getPassword());
			ps.setString(3, vendor.getName());
			ps.setString(4, vendor.getContactno());
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				message = "Vendor has registered successfully...!";
			}				
				
			} catch (SQLException se) {
				
				message = se.getMessage();
				
			}
		
		
		return message;
	}

	@Override
	public List<Vendor> showVendorList() throws VendorException {
		
		List<Vendor> vendorlist = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from vendor");
			
			ResultSet rs =  ps.executeQuery();
			
			while(rs.next()) {
				
				int vid = rs.getInt("vendorid");
				String em = rs.getString("email");
				String n = rs.getString("name");
				String cn = rs.getString("contactno");
				
				Vendor vendorObj = new Vendor();
				
				vendorObj.setVendorid(vid);
				vendorObj.setEmail(em);
				vendorObj.setName(n);
				vendorObj.setContactno(cn);
				
				vendorlist.add(vendorObj);
			}
			
			if(vendorlist.isEmpty()) {
				
				throw new VendorException("No vedors found in the database");
			}
			
			
		} catch (SQLException se) {
			
			throw new VendorException(se.getMessage());
			
		}
		
		
		return vendorlist;
	}

	@Override
	public String createTender(Tender tender) {
		
		String message = "Unable to create the tender";
		
		tender.setStatus("Not Allocated");
		
		try (Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement
					("insert into tender(tendername, baseprice, status) values(?, ?, ?)");
			
			ps.setString(1, tender.getTendername());
			ps.setInt(2, tender.getBaseprice());
			ps.setString(3, tender.getStatus());
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				message = "Tender has added";
			}
			
		} catch (SQLException e) {
			
			message = e.getMessage();
		} 
		
		
		return message;
	}

	@Override
	public List<Tender> showTenderList() throws TenderException {
		
		List<Tender> list = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from tender");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int ti = rs.getInt("tenderid");
				String tn = rs.getString("tendername");
				int bp = rs.getInt("baseprice");
				String st = rs.getString("status");
				int vi = rs.getInt("allocatedvendorid");
				
				Tender tender = new Tender(ti, tn, bp, st, vi);
				
				list.add(tender);
				
			}
			
			if(list.isEmpty()) {
				
				throw new TenderException("No tender to show");
			}
			
		} catch (SQLException e) {
			
			throw new TenderException(e.getMessage());
			
		}
		
		return list;
	}

	@Override
	public List<BidTenderVendorDTO> tenderWiseBid(int tenderid) throws BidException {
		
		List<BidTenderVendorDTO> list = new ArrayList<>();
				
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps =  conn.prepareStatement(" select t.tenderid, t.tendername, b.vendorid, v.name, "
					+ "b.bidprice from tender t inner join vendor v inner join bid b "
					+ "on t.tenderid = b.tenderid and b.vendorid = v.vendorid where b.tenderid = ?");
			
			ps.setInt(1, tenderid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int ti = rs.getInt("tenderid");
				String tn = rs.getString("tendername");
				int vi = rs.getInt("vendorid");
				String vn = rs.getString("name");
				int bp = rs.getInt("bidprice");
				
				BidTenderVendorDTO btvd = new BidTenderVendorDTO(ti, tn, vi, vn, bp);
				
				list.add(btvd);
										
			}
			
			if(list.isEmpty()) {
				
				throw new BidException("No bid record found for the specified tenderid");
			}
			
		} catch (SQLException e) {
			
			throw new BidException(e.getMessage());
		}
		
		
		return list;
	}

	@Override
	public String assignTender(int tenderid) {
		
		String message = "Unable to assign the tender";
		
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps0 = conn.prepareStatement
					("select status from tender where tenderid = ?");
			ps0.setInt(1, tenderid);
			
			ResultSet rs0 = ps0.executeQuery();
			
			if(rs0.next()) {
				
				String St = rs0.getString("status");
				
				
				if(St.equals("Not Allocated")) {
					
							PreparedStatement ps1 = conn.prepareStatement
							("select tenderid, vendorid, min(bidprice) from bid  where tenderid = ? group by tenderid");
							
							ps1.setInt(1, tenderid);
							
							ResultSet rs1 = ps1.executeQuery();
							
							if(rs1.next()) {
								
								int vi = rs1.getInt("vendorid");
								
								PreparedStatement ps2 = conn.prepareStatement
										("update tender set status = 'Allocated', allocatedvendorid = ? where tenderid = ?");
								
								ps2.setInt(1, vi);
								ps2.setInt(2, tenderid);
								
								int x = ps2.executeUpdate();
								
								PreparedStatement ps3 = conn.prepareStatement("select name from vendor where vendorid = ?");
								ps3.setInt(1, vi);
								
								ResultSet rs3 = ps3.executeQuery();
								
								String name = null;
								
								if(rs3.next()) {
									
									 name = rs3.getString("name");
								}
								
								if(x > 0) {
									message = "The tender has been assigned to a vendor."
											+ "\nAssigned vendor's id is : " + vi 
											+ "\nAssigned vendor's name is : " + name;
								} 
							}
					
				} else {
					
					message = "The tender is already allocated to a vendor";
				}
			}
			
			
			
		} catch (SQLException e) {
			
			message = e.getMessage();

		}
 		
		return message;
	}

}
