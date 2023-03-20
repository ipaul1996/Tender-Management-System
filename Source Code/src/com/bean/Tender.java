package com.bean;

public class Tender {
	
	
	private int tenderid;
	private String tendername;
	private int baseprice; 
	private String status;
	private int allocatedvendorid;
	
	public Tender() {
		
	}
	
	public Tender(int tenderid, String tendername, int baseprice, String status, int allocatedvendorid) {
		super();
		this.tenderid = tenderid;
		this.tendername = tendername;
		this.baseprice = baseprice;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAllocatedvendorid() {
		return allocatedvendorid;
	}

	public void setAllocatedvendorid(int allocatedvendorid) {
		this.allocatedvendorid = allocatedvendorid;
	}

	@Override
	public String toString() {
		return "Tender [tenderid=" + tenderid + ", tendername=" + tendername + ", baseprice=" + baseprice + ", status="
				+ status + ", allocatedvendorid=" + allocatedvendorid + "]";
	}
	
	
	
	

	


}
