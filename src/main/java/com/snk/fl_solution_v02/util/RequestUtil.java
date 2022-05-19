package com.snk.fl_solution_v02.util;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.snk.fl_solution_v02.object.HMap;

public class RequestUtil {
	
	public static HMap getHashMap(HttpServletRequest request){
		HMap hmap = new HMap();
		Enumeration<?> e = request.getParameterNames();
		
		while(e.hasMoreElements()) {
			String key = (String)e.nextElement();
			if(request.getParameterValues(key).length > 1) {
				hmap.put(key, request.getParameterValues(key));
			}else {
				hmap.put(key, request.getParameter(key));
			}
		}
		return hmap;
	}
	

}
