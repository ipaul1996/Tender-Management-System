package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.Exception.VendorException;
import com.bean.Vendor;
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

}
