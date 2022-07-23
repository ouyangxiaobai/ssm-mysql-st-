package com.snack.model;


public class Snackinfo {
    private Integer sId;

    private String sBatch;

    private String sName;

    private Double sPrice;

    private String sDiscount;

    private String sPictureurl;

    private String sBrand;

    private String sPlace;

    private Integer sImported;

    private Integer sScore;

    private String sPdate;

    private String sQdate;

    private Integer state;

    private String sCreatedate;

    private String sDese;

    private Integer sNumber;
    
    private String sType;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getsBatch() {
        return sBatch;
    }

    public void setsBatch(String sBatch) {
        this.sBatch = sBatch;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Double getsPrice() {
        return sPrice;
    }

    public void setsPrice(Double sPrice) {
        this.sPrice = sPrice;
    }

    public String getsDiscount() {
        return sDiscount;
    }

    public void setsDiscount(String sDiscount) {
        this.sDiscount = sDiscount;
    }

    public String getsPictureurl() {
        return sPictureurl;
    }

    public void setsPictureurl(String sPictureurl) {
        this.sPictureurl = sPictureurl;
    }

    public String getsBrand() {
        return sBrand;
    }

    public void setsBrand(String sBrand) {
        this.sBrand = sBrand;
    }

    public String getsPlace() {
        return sPlace;
    }

    public void setsPlace(String sPlace) {
        this.sPlace = sPlace;
    }

    public Integer getsImported() {
        return sImported;
    }

    public void setsImported(Integer sImported) {
        this.sImported = sImported;
    }

    public Integer getsScore() {
        return sScore;
    }

    public void setsScore(Integer sScore) {
        this.sScore = sScore;
    }

    public String getsPdate() {
        return sPdate;
    }

    public void setsPdate(String sPdate) {
        this.sPdate = sPdate;
    }

    public String getsQdate() {
        return sQdate;
    }

    public void setsQdate(String sQdate) {
        this.sQdate = sQdate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getsCreatedate() {
        return sCreatedate;
    }

    public void setsCreatedate(String sCreatedate) {
        this.sCreatedate = sCreatedate;
    }

    public String getsDese() {
        return sDese;
    }

    public void setsDese(String sDese) {
        this.sDese = sDese;
    }

    public Integer getsNumber() {
        return sNumber;
    }

    public void setsNumber(Integer sNumber) {
        this.sNumber = sNumber;
    }

	@Override
	public String toString() {
		return "Snackinfo [sId=" + sId + ", sBatch=" + sBatch + ", sName="
				+ sName + ", sPrice=" + sPrice + ", sDiscount=" + sDiscount
				+ ", sPictureurl=" + sPictureurl + ", sBrand=" + sBrand
				+ ", sPlace=" + sPlace + ", sImported=" + sImported
				+ ", sScore=" + sScore + ", sPdate=" + sPdate + ", sQdate="
				+ sQdate + ", state=" + state + ", sCreatedate=" + sCreatedate
				+ ", sDese=" + sDese + ", sNumber=" + sNumber + "]";
	}

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}
    
}