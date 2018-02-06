package com.cbec.b2b.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class Util {
	public static String createUuid() {
		String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");
		return uuid;
	}

	public static String getMD5(String str) {
		return Hashing.md5().newHasher().putString(str, Charsets.UTF_8).hash().toString();
	}
	
    public final static String formatUnixDateToDate(long unixDate){
    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return sd.format(new Date(unixDate));
    }

    public final static String formatDateToString(Date date,String format){
    	SimpleDateFormat sd = new SimpleDateFormat(format);
    	return sd.format(date);
    }
    
    public final static String formatDateToDay(){
    	Date date = new Date();
    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    	return sd.format(date);
    }
    
    public final static String formatDateToYestoday(){
    	Date date = new Date();
    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    	return sd.format(new Date(date.getTime() - 1 * 24 * 60 * 60 * 1000));
    }
    
    public final static long formatDateToUnixDate(String date){
    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long reDate = 0l;
    	try {
			reDate = sd.parse(date).getTime();
		} catch (ParseException e) {
			 e.printStackTrace();
		}
    	return reDate;
    }
}
