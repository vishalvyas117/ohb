package com.ohb.app.util;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class TokenizerUtil {
	
	public static Set<String> addressTokenizer(String address){
		
		Set<String> addr=new HashSet<String>();
		StringTokenizer stringTokenizer = new StringTokenizer(address, " ");
		while (stringTokenizer.hasMoreElements()) {
			addr.add(stringTokenizer.nextElement().toString());
		}
		return addr;
	}
	
	private static Set<String> getSearchList(String value) {
		Set<String> values= new HashSet<String>();
		String seperator=" ";
		Boolean isFormatException=false;
		
		StringTokenizer tokenizer= new StringTokenizer(value.trim(), seperator);
		while(tokenizer.hasMoreElements()) {
			String suborder=(String)tokenizer.nextElement();
			suborder=suborder.trim();
			suborder=suborder.replaceAll("\\s", "");
			
		}
		if(isFormatException) {
		   System.out.println("Wrong Suborder format. Please provide suborder number.");
		}
		return values;
	}

}
