package com.bean;

public class Bid {
	
	private int tenderid;
	private int vendorid;
	private int bidprice;
	
	public Bid() {
		
	}

	public Bid(int tenderid, int vendorid, int bidprice) {
		super();
		this.tenderid = tenderid;
		this.vendorid = vendorid;
		this.bidprice = bidprice;
	}

	public int getTenderid() {
		return tenderid;
	}

	public void setTenderid(int tenderid) {
		this.tenderid = tenderid;
	}

	public int getVendorid() {
		return vendorid;
	}

	public void setVendorid(int vendorid) {
		this.vendorid = vendorid;
	}

	public int getBidprice() {
		return bidprice;
	}

	public void setBidprice(int bidprice) {
		this.bidprice = bidprice;
	}

	@Override
	public String toString() {
		return "Bid [tenderid=" + tenderid + ", vendorid=" + vendorid + ", bidprice=" + bidprice + "]";
	}
	
	

}
