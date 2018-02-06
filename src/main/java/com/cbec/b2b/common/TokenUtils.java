package com.cbec.b2b.common;

public class TokenUtils {

	/**
     * 签名秘钥
     */
    public static final String SECRET = "CBEC-B2B";
    
    /**
     * 生成token
     * @param id 传入userName
     * @return
     */
    public static String createToken(String id){
    	String md5 = Util.getMD5(SECRET + id + SECRET);
        return Util.getMD5(md5 + System.currentTimeMillis());
    }

}
