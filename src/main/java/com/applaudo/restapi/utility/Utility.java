package com.applaudo.restapi.utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Utility {

	private final static SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static java.sql.Timestamp parseTimestamp(String timestamp) {
	    try {
	        return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
	    } catch (Exception e) {
	        throw new IllegalArgumentException(e);
	    }
	    }
}
