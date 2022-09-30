package com.bean;

public class BidTenderVendorDTO {
	
	private int tenderid;
	private String tendername;
	private int vendorid;
	private String vendorname;
	private int bidprice;
	
	public BidTenderVendorDTO() {
		
	}

	public BidTenderVendorDTO(int tenderid, String tendername, int vendorid, String vendorname, int bidprice) {
		super();
		this.tenderid = tenderid;
		this.tendername = tendername;
		this.vendorid = vendorid;
		this.vendorname = vendorname;
		this.bidprice = bidprice;;
	}

	public int getTenderid() {
		return tenderid;
	}

	public void setTenderid(int tenderid) {
		this.tenderid = tenderid;
	}

	public String getTendername() {
		return tendername;
	}

	public void setTendername(String tendername) {
		this.tendername = tendername;
	}

	public int getVendorid() {
		return vendorid;
	}

	public void setVendorid(int vendorid) {
		this.vendorid = vendorid;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public int getBidprice() {
		return bidprice;
	}

	public void setBidprice(int bidprice) {
		this.bidprice = bidprice;;
	}

	@Override
	public String toString() {
		return "BidTenderVendorDTO [tenderid=" + tenderid + ", tendername=" + tendername + ", vendorid=" + vendorid
				+ ", vendorname=" + vendorname + ", baseprice=" + bidprice + "]";
	}
	
	

}
