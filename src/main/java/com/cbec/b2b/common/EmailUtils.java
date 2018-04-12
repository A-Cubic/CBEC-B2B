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
		sb.append("<h4>" + email + ",您好！欢迎加入流连优选，您正在验证账号，验证码：<b style='color:red'><u>" + code + "</u></b>，有效期为3小时。<h4>").append("<p>如果您没有进行此操作，请忽略该邮件。</p>");
		sendNormalEmail("【验证码】", true, sb.toString(), true, email);
	}

	/**
	 * 审核通过时的提示邮件
	 * 
	 * @param email 接收的邮箱地址 【注册人】
	 *
	 */
	public void sendRegisterSuccess(String email) {
		final StringBuffer sb = new StringBuffer();
		sb.append("<h3>恭喜您，您的账号已通过审核！</h3>").append("<h2>更多操作，请登录<a href='http://console.llwell.net/#/user/login'>流连优选后台</a>查看</h2>");
		sendNormalEmail("【审核通过】", true, sb.toString(), true, email);
	}

	/**
	 * 审核失败时的提示邮件
	 * 
	 * @param email 接收的邮箱地址 【注册人】
	 * @param content 失败信息
	 */
	public void sendRegisterFail(String email, String content) {
		final StringBuffer sb = new StringBuffer();
		sb.append("<h3>很遗憾，您的账号未通过审核！</h3>").append("<p><h2>原因为：").append(content)
				.append("</h2></p><p><h2>您可以登录<a href='http://console.llwell.net/#/user/login'>流连优选后台</a>重新提交审核资料，感谢您对流连优选的信任和支持。</h2></p>");
		sendNormalEmail("【审核未通过】", true, sb.toString(), true, email);
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
