package com.snack.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import com.snack.model.domain.DoSnackType;

public class TypeUtil {
	private static ResourceBundle resources = ResourceBundle.getBundle("type");
	public static Map<Integer, String> getAllType(){
		Map<Integer, String> ret = new HashMap<Integer, String>();
		Enumeration<String> keys = resources.getKeys();
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			try {
				ret.put(Integer.valueOf(key), new String(resources.getString(key).getBytes("ISO-8859-1"),"UTF-8"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}
	public static List<DoSnackType> getAllDoType(){
		List<DoSnackType> ret = new ArrayList<DoSnackType>();
		Map<Integer, String> allType = getAllType();
		for(Entry<Integer, String> type:allType.entrySet()){
			DoSnackType doSnackType = new DoSnackType();
			doSnackType.setType(type.getKey());
			doSnackType.setSnackName(type.getValue());
			ret.add(doSnackType);
		}
		return ret;
	}
}
