package com.cbec.b2b.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.api.UserApi;
import com.cbec.b2b.common.RedisUtil;
import com.cbec.b2b.common.TokenUtils;
import com.cbec.b2b.common.Util;
import com.cbec.b2b.entity.menu.Menu;
import com.cbec.b2b.entity.message.MessageCountEntity;
import com.cbec.b2b.entity.message.MessageEntity;
import com.cbec.b2b.entity.register.RegisterStepTwo;
import com.cbec.b2b.entity.register.UserStatus;
import com.cbec.b2b.entity.request.LoginEntity;
import com.cbec.b2b.entity.request.MessageRequest;
import com.cbec.b2b.entity.response.CurrentUser;
import com.cbec.b2b.entity.response.LoginResponseEntity;

@RestController
@RequestMapping(value = "/llback/user")
public class UserController {

	@Autowired
	UserApi api;

	@Autowired
	private RedisUtil redisUtil;

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@RequestMapping(value = "/validate")
	public String validate(@RequestBody LoginEntity loginEntity, HttpServletResponse res) {
		String pwd = loginEntity.getPassword();
		String pwdMd5 = "";
		if (pwd != null) {
			pwdMd5 = Util.getMD5(loginEntity.getPassword());
		}
		LoginResponseEntity response = api.validate(loginEntity.getUserName(), pwdMd5);
		String userId = loginEntity.getUserName();
		String token = TokenUtils.createToken(userId);
		boolean setResult = redisUtil.set(userId, token);
		response.setToken(token);
		if (setResult) {
			logger.info(String.format("保存token成功：[%s][%s]", userId, token));
		}
		Util.responseResultSuccess(res);
		return response.toString();
	}

	@RequestMapping(value = "/currentUser")
	public CurrentUser getUser(@RequestHeader(value = "userid") String userid, HttpServletResponse res) {
		CurrentUser response = api.getUser(userid);
		Util.responseResultSuccess(res);
		return response;
	}

	@RequestMapping(value = "/menu")
	public List<Menu> getMenu(@RequestHeader(value = "userid") String userid, HttpServletResponse res) {
		List<Menu> menuList = api.getMenu(userid);
		Util.responseResultSuccess(res);
		return menuList;
	}

	@RequestMapping(value = "/message/list")
	public List<MessageEntity> getMessage(@RequestHeader(value = "userid") String userid, HttpServletResponse res) {
		List<MessageEntity> response = api.getMessage(userid);
		Util.responseResultSuccess(res);
		return response;
	}

	@RequestMapping(value = "/message/count")
	public MessageCountEntity getMessageCount(@RequestHeader(value = "userid") String userid, HttpServletResponse res) {
		MessageCountEntity response = api.getMessageCount(userid);
		Util.responseResultSuccess(res);
		return response;
	}

	@RequestMapping(value = "/message/empty")
	public String updateMessage(@RequestHeader(value = "userid") String userid, @RequestBody MessageRequest requestBean,
			HttpServletResponse res) {
		String response = api.updateMessage(userid, requestBean.getType());
		Util.responseResultSuccess(res);
		return response;
	}

	@RequestMapping(value = "/register/submit")
	public String registerSubmit(@RequestBody Map<String, String> request, HttpServletResponse res) {
		Util.responseResultSuccess(res);
		if (request == null || request.isEmpty()) {
			return "无效请求";
		}

		String mail = request.get("mail");
		String code = request.get("captcha");
		String pwd = request.get("password");
		String type = request.get("type");
		
		if (mail == null || code == null || pwd == null || type == null || "".equals(mail) || "".equals(code)
				|| "".equals(pwd) || "".equals(type) || (!"1".equals(type) && !"2".equals(type) && !"3".equals(type) && !"4".equals(type)) ) {
			return "非法请求，参数有误.";
		}
		if (!Util.checkEmail(mail)) {
			return "邮件格式不正确.";
		}
		
		if(pwd.length()<6) {
			return "密码格式有误.";
		}
		
		String key = mail + "_code";

		if (!redisUtil.isExistKey(key)) {
			return "无效的验证码.";
		}
		String redis_code = (String)redisUtil.get(key);
		if(!redis_code.equals(code)) {
			return "验证码不正确.";
		}

		return api.registerSubmit(mail, pwd, type);
	}

	@RequestMapping(value = "/register/code")
	public String registerCode(@RequestBody Map<String, String> request, HttpServletResponse res) {
		Util.responseResultSuccess(res);
		if (request == null || request.isEmpty()) {
			return "无效请求";
		}
		String mail = request.get("mail");
		if (mail != null && !"".equals(mail)) {
			if (!Util.checkEmail(mail)) {
				return "邮件格式不正确.";
			}
			String redis_temp_code = mail + "_code_temp";
			if (redisUtil.isExistKey(redis_temp_code)) {
				Long leaveTime = (Long) redisUtil.getExpire(redis_temp_code);
				return leaveTime + "s";
			}
			redisUtil.set(redis_temp_code, mail, 60l);
			String code = api.registerCode(mail);
			redisUtil.set(mail + "_code", code, 10800l);
		} else {
			return "非法的调用,账号为空.";
		}
		return "邮件已发送";
	}
	
	@RequestMapping(value = "/register/upload")
	public String registerInfoUpload(@RequestBody RegisterStepTwo request,@RequestHeader(value = "userid") String userid, HttpServletResponse res) {
		Util.responseResultSuccess(res);
		request.setUserName(userid);
		return api.registerInfoUpload(request);
	}
	
	@RequestMapping(value = "/register/status")
	public UserStatus registerStatus(@RequestHeader(value = "userid") String userid, HttpServletResponse res) {
		Util.responseResultSuccess(res);
		return api.registerStatus(userid);
	}
	
	@RequestMapping(value = "/register/check")
	public String registerCheck(@RequestBody Map<String,String> request, HttpServletResponse res) {
		Util.responseResultSuccess(res);
		String account = request.get("account");
		String check = request.get("check");
		return api.registerCheck(account,check);
	}
	 
}
