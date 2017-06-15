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

}
