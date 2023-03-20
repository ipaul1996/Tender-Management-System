package com.bean;

public class BidHist {
	
	private int tenderid;
	private String tendername;
	private int baseprice;
	private int bidprice;
	private int allocatedvendorid;
	
	public BidHist() {
		
	}

	public BidHist(int tenderid, String tendername, int baseprice, int bidprice, int allocatedvendorid) {
		super();
		this.tenderid = tenderid;
		this.tendername = tendername;
		this.baseprice = baseprice;
		this.bidprice = bidprice;
		this.allocatedvendorid = allocatedvendorid;
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

	public int getBaseprice() {
		return baseprice;
	}

	public void setBaseprice(int baseprice) {
		this.baseprice = baseprice;
	}

	public int getBidprice() {
		return bidprice;
	}

	public void setBidprice(int bidprice) {
		this.bidprice = bidprice;
	}

	public int getAllocatedvendorid() {
		return allocatedvendorid;
	}

	public void setAllocatedvendorid(int allocatedvendorid) {
		this.allocatedvendorid = allocatedvendorid;
	}

	@Override
	public String toString() {
		return "BidHist [tenderid=" + tenderid + ", tendername=" + tendername + ", baseprice=" + baseprice
				+ ", bidprice=" + bidprice + ", allocatedvendorid=" + allocatedvendorid + "]";
	}

	
	
}
