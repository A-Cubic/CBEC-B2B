package com.cbec.b2b.common;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cbec.b2b.response.ResponseHeader;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class Util {
	private static final Logger logger = LogManager.getLogger(Util.class);
	
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
    
    public static String curDate(String pattern) {  
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
        return sdf.format(new Date());  
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
    
    public static void responseResult(HttpServletResponse response,String code,String msg) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setHeader("code", code);
        response.setHeader("msg", msg);
//        response.setStatus(200);
        
        try {
        	response.getWriter().write(new ResponseHeader(code,msg).toString());
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
    public static void responseResultSuccess(HttpServletResponse response) {
    	   response.setHeader("code", "0");
           response.setHeader("msg", "success");
    }
    
    /**
    * 验证邮箱
    *
    * @param email
    * @return
    */
   public static boolean checkEmail(String email) {
       boolean flag = false;
       try {
           String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
           Pattern regex = Pattern.compile(check);
           Matcher matcher = regex.matcher(email);
           flag = matcher.matches();
       } catch (Exception e) {
           flag = false;
       }
       return flag;
   }

   /**
    * 验证手机号码，11位数字，1开通，第二位数必须是3456789这些数字之一 *
    * @param mobileNumber
    * @return
    */
   public static boolean checkMobileNumber(String mobileNumber) {
       boolean flag = false;
       try {
           // Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
           Pattern regex = Pattern.compile("^1[345789]\\d{9}$");
           Matcher matcher = regex.matcher(mobileNumber);
           flag = matcher.matches();
       } catch (Exception e) {
           e.printStackTrace();
           flag = false;

       }
       return flag;
   }
   
     
   /** 随机6位数 */  
   public static String randomCode() {  
       Integer res = (int)((Math.random()*9+1)*100000);  
       return res+"";  
   }  
   
   public static void main(String[] args) {
	   System.out.println(randomCode());
   }
    
}
