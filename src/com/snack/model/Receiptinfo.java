package com.snack.model;

import java.util.Set;

public class Receiptinfo {
    private Integer rId;

    private String oId;
    
    private String oName;

    private String oPhone;

    private String oAddress;
    
    private Set<Orderdetail> orderdetailSet;
    
    public Receiptinfo(){
    	
    }
    
    public Receiptinfo(Set<Orderdetail> orderdetailSet) {
		super();
		this.setOrderdetailSet(orderdetailSet);
	}

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    public String getoPhone() {
        return oPhone;
    }

    public void setoPhone(String oPhone) {
        this.oPhone = oPhone;
    }

    public String getoAddress() {
        return oAddress;
    }

    public void setoAddress(String oAddress) {
        this.oAddress = oAddress;
    }

	public Set<Orderdetail> getOrderdetailSet() {
		return orderdetailSet;
	}

	public void setOrderdetailSet(Set<Orderdetail> orderdetailSet) {
		this.orderdetailSet = orderdetailSet;
	}

	@Override
	public String toString() {
		return "Receiptinfo [rId=" + rId + ", oId=" + oId + ", oName=" + oName
				+ ", oPhone=" + oPhone + ", oAddress=" + oAddress
				+ ", orderdetailSet=" + orderdetailSet + "]";
	}
	
	
}