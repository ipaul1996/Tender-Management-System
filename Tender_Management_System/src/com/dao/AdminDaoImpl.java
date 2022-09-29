package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Exception.AdminException;
import com.Exception.TenderException;
import com.Exception.VendorException;
import com.bean.Admin;
import com.bean.Tender;
import com.bean.Vendor;
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

}
