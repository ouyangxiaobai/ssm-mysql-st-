package com.snack.web;

import java.net.URLEncoder;

import com.snack.utils.DesUtils;


public class Destext {
	  public static void main(String[] args) {
	        try {
	            String requestNo = "e498171c-6976-4c31-8998-8c068866beea";
	            String thirdCode = "WEICHE";
	            String insureFlowCode = "1014100328";
	            String outTradeNo = "WEICHE_0f6bd7656cf74704a493dc96fd868a18";
	            String returnUrl = "http://ti.ttsales.cn/insurance-api/callback-za/signature";
	            String appkey = "a4e4eff375eaaa4f7caef5ccbf88c75e";
	            DesUtils desutil = new DesUtils(appkey);
	            String encryptOutTradeNo = desutil.encrypt(outTradeNo);
	            String encryptInsureFlowCode = desutil.encrypt(insureFlowCode);
	            //fe69a12f99817597bd09fb45d143ef47d59099badcf6ba3b
	            System.out.println(encryptInsureFlowCode);
	            System.out.println(encryptOutTradeNo);

	            //String bb = desutil.decrypt(aa);
	            //System.out.println(bb);
	            String urlString = URLEncoder.encode(returnUrl, "UTF-8");
	            System.out.println(urlString);
	            String url = "https://c-uat.zhongan.com/open/api/signEntry.do?requestNo=" + requestNo + "&thirdCode="
	                    + thirdCode + "&insureFlowCode=" + encryptInsureFlowCode + "&outTradeNo=" + encryptOutTradeNo
	                    + "&returnUrl=" + urlString;
	            System.out.println(url);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
