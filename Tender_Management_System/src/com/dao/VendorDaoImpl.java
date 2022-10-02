package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bean.Bid;
import com.bean.BidHist;
import com.bean.Tender;
import com.bean.Vendor;
import com.exception.BidException;
import com.exception.TenderException;
import com.exception.VendorException;
import com.mysql.cj.protocol.Resultset;
import com.utility.DBUtil;

public class VendorDaoImpl implements VendorDao {

	@Override
	public Vendor vendorLogin(String username, String password) throws VendorException {
		
		Vendor vendor = null;
		
		try(Connection conn = DBUtil.provideConnection()) {
			
		   PreparedStatement ps = conn.prepareStatement
				   ("select * from vendor where email = ? and password = ?");
		   
		   ps.setString(1, username);
		   ps.setString(2, password);
		   
		   ResultSet rs = ps.executeQuery();
		   
		   if(rs.next()) {
			   
			   int vi = rs.getInt("vendorid");
			   String e = rs.getString("email");
			   String p = rs.getString("password");
			   String n = rs.getString("name");
			   String c = rs.getString("contactno");
			   
			   vendor = new Vendor(vi, e, p, n, c);
			   
			   
		   } else {
			   
			   throw new VendorException("Invalid username or password");
		   }
			
			
		} catch(SQLException se) {
			
			throw new VendorException(se.getMessage());
			
		}
		
		return vendor;
	}

	@Override
	public List<Tender> showTenders() throws TenderException {
		
		List<Tender> list = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement
					("select tenderid, tendername, baseprice from tender where status = 'Not Allocated' ");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int ti = rs.getInt("tenderid");
				String tn = rs.getString("tendername");
				int bp = rs.getInt("baseprice");
				
				Tender tender = new Tender();
				
				tender.setTenderid(ti);
				tender.setTendername(tn);
				tender.setBaseprice(bp);
				
				list.add(tender);
				
			}
			
			if(list.isEmpty()) {
				
				throw new TenderException("No current tender found.");
				
			}
			
		} catch (SQLException e) {
			
			throw new TenderException(e.getMessage());
		}
		
		
		
		
		
		return list;
	}

	@Override
	public Bid placeBid(Vendor vendor) throws BidException {
		
		Bid bid = null;
		
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			System.out.println("Enter tender id : ");
			int ti = sc.nextInt();
			
			System.out.println("Enter bid price : ");
			int bp = sc.nextInt();
			
			PreparedStatement ps = conn.prepareStatement("insert into bid values(?, ?, ?)");
			
			ps.setInt(1, ti);
			ps.setInt(2, vendor.getVendorid());		
			ps.setInt(3, bp);
			
			int x = ps.executeUpdate();
			
			if(x > 0) {
				
				bid = new Bid(ti, vendor.getVendorid(), bp);
				
			} else {
				
				throw new BidException("Unable to place the bid");
			}
			
			
		} catch (SQLException e) {
			
			throw new BidException(e.getMessage());
			
		} catch(Exception e) {
			
			throw new BidException("Please enter the valid input");
		}
		
		
		
		return bid;
		
	}

	@Override
	public String bidAcceptence(Vendor vendor) {
		
		String message = "Sorry! unable to find the bid";
		
		List<Integer> list = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps1 = conn.prepareStatement("select tenderid, bidprice from bid where vendorid = ?");
			
			ps1.setInt(1, vendor.getVendorid());
			
			ResultSet rs1 = ps1.executeQuery();
			
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			while(rs1.next()) {
				
				int ti = rs1.getInt("tenderid");
				int bp = rs1.getInt("bidprice");
				
				System.out.println("Tender Id : " + ti);
				System.out.println("Bid Price : " + bp);
				
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				
				list.add(ti);
			}
			
			System.out.println("Please enter a valid tender id : ");
			int tenderid = sc.nextInt();
			
			boolean flag1 = false;
			
			for(int i : list) {
				if(tenderid == i) {
					flag1 = true;
				}
			}
			
			if(flag1) {
				
				PreparedStatement ps = conn.prepareStatement("select allocatedvendorid from tender where tenderid = ?");
				ps.setInt(1, tenderid);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					
					int vid = rs.getInt("allocatedvendorid");
					
					if(vid == 0) {
						message = "Bid is Pending";
						
					} else if(vid == vendor.getVendorid()) {
						message = "Bid is Accepted";
						
					} else {
						
						message = "Bid is Rejected";
					}
				}
				
			}
						
		} catch (SQLException e) {
			
			message = e.getMessage();
			
		}  catch(Exception e) {
			
			message = "Please enter valid inputs";
			
		}
		
		
		
		return message;
	}

	@Override
	public List<BidHist> bidHistory(Vendor vendor) throws BidException{
		
		List<BidHist> list = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement
			(" select t.tenderid, t.tendername, t.baseprice, b.bidprice, t.allocatedvendorid from bid b "
					+ "inner join tender t on b.tenderid = t.tenderid and b.vendorid = ? "
					+ "and t.allocatedvendorid is not null");
			
			ps.setInt(1, vendor.getVendorid());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int ti = rs.getInt("tenderid");
				String tn = rs.getString("tendername");
				int basep = rs.getInt("baseprice");
				int bidp = rs.getInt("bidprice");
				int avi = rs.getInt("allocatedvendorid");
				
				BidHist bih = new BidHist(ti, tn, basep, bidp, avi);
				
				list.add(bih);
			}
			
			if(list.isEmpty()) {
				
				throw new BidException("No bid history found");
				
			}
			
		} catch (SQLException e) {
			
			throw new BidException(e.getMessage());
		}
		
		return list;
	}

}
