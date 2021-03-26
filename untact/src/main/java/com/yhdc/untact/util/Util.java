package com.yhdc.untact.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String getNowDateStr() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		Date time = new Date();
		
		return format1.format(time);
	}
}

