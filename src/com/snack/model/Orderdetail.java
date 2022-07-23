package com.snack.model;

public class Orderdetail {

	private Integer dId;

    private String oId;
    
    private String sName;

	private Integer sId;

    private Integer oNum;

    private Double oMoney;
    
    private Snackinfo snackinfo;
    
	public Snackinfo getSnackinfo() {
		return snackinfo;
	}

	public void setSnackinfo(Snackinfo snackinfo) {
		this.snackinfo = snackinfo;
	}

	public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}
	
    public Integer getoNum() {
        return oNum;
    }

    public void setoNum(Integer oNum) {
        this.oNum = oNum;
    }

    public Double getoMoney() {
        return oMoney;
    }

    public void setoMoney(Double oMoney) {
        this.oMoney = oMoney;
    }
    
    @Override
   	public String toString() {
   		return "Orderdetail [dId=" + dId + ", oId=" + oId + ", sName=" + sName
   				+ ", sId=" + sId + ", oNum=" + oNum + ", oMoney=" + oMoney
   				+ ", snackinfo=" + snackinfo + "]";
   	}

}