package com.snk.fl_solution_v02.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FolderUtil {

	public static String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	
}
