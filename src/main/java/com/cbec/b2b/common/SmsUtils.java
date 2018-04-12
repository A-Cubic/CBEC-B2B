package com.cbec.b2b.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SmsUtils {

	private static final Logger logger = LogManager.getLogger(SmsUtils.class);
	
	/**
	 * 发送短信
	 * 
	 * @param phoneNum
	 *            接收手机号
	 * @param code
	 *            验证码
	 */
	public void sendRegisterCode(String phoneNum, String code) {
		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			// String code = URLEncoder.encode("#code#=333242", "GBK").toLowerCase();
			// //输出%C4%E3%BA%C3
			URL url = new URL("http://v.juhe.cn/sms/send?mobile=" + phoneNum + "&tpl_id=68600&tpl_value=%23code%23%3d"
					+ code + "&key=7c21d791256af1ffdd85375c64846358");
			URLConnection urlConnection = url.openConnection();
			urlConnection.setAllowUserInteraction(false);
			isr = new InputStreamReader(url.openStream());
			br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			logger.error("发送短信失败，原因："+e.getMessage());
		} finally {
			try {
				if (isr != null) {
					isr.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				logger.error("发送短信时，关闭流失败，原因："+e.getMessage());
			}

		}
	}
	/**
	 * 审核通过时的提示邮件
	 * 
	 * @param email 接收的邮箱地址 【注册人】
	 *
	 */
	public void sendRegisterSuccess(String phoneNum) {
		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			// String code = URLEncoder.encode("#code#=333242", "GBK").toLowerCase();
			// //输出%C4%E3%BA%C3
			URL url = new URL("http://v.juhe.cn/sms/send?mobile=" + phoneNum + "&tpl_id=68761&key=7c21d791256af1ffdd85375c64846358");
			URLConnection urlConnection = url.openConnection();
			urlConnection.setAllowUserInteraction(false);
			isr = new InputStreamReader(url.openStream());
			br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			logger.error("发送短信失败，原因："+e.getMessage());
		} finally {
			try {
				if (isr != null) {
					isr.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				logger.error("发送短信时，关闭流失败，原因："+e.getMessage());
			}
		}
	}
	/**
	 * 审核失败时的提示邮件
	 * 
	 * @param email 接收的邮箱地址 【注册人】
	 * @param content 失败信息
	 */
	public void sendRegisterFail(String phoneNum) {
		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			// String code = URLEncoder.encode("#code#=333242", "GBK").toLowerCase();
			// //输出%C4%E3%BA%C3
			URL url = new URL("http://v.juhe.cn/sms/send?mobile=" + phoneNum + "&tpl_id=68762&key=7c21d791256af1ffdd85375c64846358");
			URLConnection urlConnection = url.openConnection();
			urlConnection.setAllowUserInteraction(false);
			isr = new InputStreamReader(url.openStream());
			br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			logger.error("发送短信失败，原因："+e.getMessage());
		} finally {
			try {
				if (isr != null) {
					isr.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				logger.error("发送短信时，关闭流失败，原因："+e.getMessage());
			}
		}
	}
}
