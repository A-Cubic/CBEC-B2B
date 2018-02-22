package com.cbec.b2b.common;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	@Autowired
	JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String username;

	/**
	 * 发送邮件
	 * 
	 * @param title
	 *            标题
	 * @param titleWithName
	 *            是否在标题后添加 名称
	 * @param content
	 *            内容
	 * @param contentWithName
	 *            是否在内容后添加 名称
	 * @param email
	 *            接收者的邮箱【注册人】
	 */
	@Async
	private void sendNormalEmail(String title, boolean titleWithName, String content, boolean contentWithName,
			String email) {
		String dName = "流连优选";
		MimeMessage mimeMessage = null;
		try {
			mimeMessage = javaMailSender.createMimeMessage();// 创建要发送的信息
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(new InternetAddress(username, dName, "UTF-8")); // 设置 谁发送的
			helper.setTo(email);// 发给谁 【接收者的邮箱】
			title = titleWithName ? title + "-" + dName : title; // 标题内容
			helper.setSubject(title);// 发送邮件的辩题
			if (contentWithName) {
				content += "<p style='text-align:right'>" + dName + "</p>";
				content += "<p style='text-align:right'>" + Util.curDate("yyyy-MM-dd HH:mm:ss") + "</p>";
			}
			helper.setText(content, true);// 发送的内容 是否为html
		} catch (Exception e) {
			e.printStackTrace();
		}
		javaMailSender.send(mimeMessage);
	}

	/**
	 * 发送 注册时的验证码
	 * 
	 * @param email
	 *            接收者的邮箱【注册人】
	 * @param code
	 *            验证码
	 */
	public void sendRegisterCode(String email, String code) {
		StringBuffer sb = new StringBuffer();
		sb.append("<h2>" + email + ",您好！<h2>").append("<p>此次注册的验证码是：<b style='color:red'>" + code + "</b>，有效期为1小时。</p>");
		sendNormalEmail("【验证码】", true, sb.toString(), true, email);
	}

	/**
	 * 注册成功时的提示邮件
	 * 
	 * @param email
	 *            接收的邮箱地址 【注册人】
	 * @param pwd
	 *            初始密码
	 * @param url
	 *            登陆地址
	 */
	public void sendRegisterSuc(final String email, String pwd, String url) {
		final StringBuffer sb = new StringBuffer();
		sb.append("<h3>恭喜您，注册成功！</h3>").append("<h2>初始化密码是：<b style='color:#F00'>").append(pwd)
				.append("</b>，请不要告诉任何人！</h2>").append("请及时<a href='").append(url).append("'>登陆网站</a>修改密码。");
		new Thread(new Runnable() {
			@Override
			public void run() {
				sendNormalEmail("【注册成功】", true, sb.toString(), true, email);
			}
		}).start();
	}

	/**
	 * 注册成功时的提示邮件
	 * 
	 * @param email
	 *            接收的邮箱地址 【注册人】
	 * @param pwd
	 *            初始密码
	 * @param url
	 *            登陆地址
	 */
	public void sendFindPwdSuc(final String email, String pwd, String url) {
		final StringBuffer sb = new StringBuffer();
		sb.append("<h3>恭喜您，密码找回成功！</h3>").append("<h2>系统随机密码是：<b style='color:#F00'>").append(pwd)
				.append("</b>，请不要告诉任何人！</h2>").append("请及时<a href='").append(url).append("'>登陆网站</a>修改密码。");

		new Thread(new Runnable() {
			@Override
			public void run() {
				sendNormalEmail("成功找回密码", true, sb.toString(), true, email);
			}
		}).start();
	}

	/**
	 * 新用户注册通过
	 * 
	 * @param email
	 *            接收邮箱地址（管理员的）
	 * @param nickname
	 *            注册人姓名
	 * @param regEmail
	 *            注册人邮箱
	 * @param url
	 *            地址
	 */
	public void sendOnRegister(final String email, String nickname, String regEmail, String url) {
		final StringBuffer sb = new StringBuffer();
		sb.append("<a href='").append(url).append("'><h1>姓名：").append(nickname).append("</h1></a>");
		sb.append("<h3>注册邮箱：").append(regEmail).append("</h3>");
		new Thread(new Runnable() {
			@Override
			public void run() {
				sendNormalEmail("新用户注册通知", true, sb.toString(), true, email);
			}
		}).start();
	}
}
