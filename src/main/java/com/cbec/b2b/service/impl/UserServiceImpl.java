package com.cbec.b2b.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cbec.b2b.common.ContentErrorMsg;
import com.cbec.b2b.common.EmailUtils;
import com.cbec.b2b.common.ServiceException;
import com.cbec.b2b.common.Util;
import com.cbec.b2b.entity.menu.Menu;
import com.cbec.b2b.entity.menu.MenuChildren;
import com.cbec.b2b.entity.message.MessageCountEntity;
import com.cbec.b2b.entity.message.MessageEntity;
import com.cbec.b2b.entity.register.RegisterStepOne;
import com.cbec.b2b.entity.response.CurrentUser;
import com.cbec.b2b.entity.response.LoginResponseEntity;
import com.cbec.b2b.mapper.UserMapper;
import com.cbec.b2b.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserMapper mapper;
	
	@Autowired
	EmailUtils emailUtils;
	
	@Override
	public LoginResponseEntity validate(String account,String password) {
		LoginResponseEntity response = mapper.validate(account, password);
		if(response != null) {
			return response;
		}else {
			ServiceException ex = new ServiceException(ContentErrorMsg.ERROR_3);
			throw ex;
		}
	}
	
	@Override
	public Boolean isAuth(String account,String url) {
		Integer num = mapper.isAuth(account,url);
		if(num != null && num>0) {
			return true;
		}
		return false;
	}

	@Override
	public CurrentUser getUser(String account) {
		return mapper.getUser(account);
	}
	
	@Override
	public List<Menu> getMenuTop(String account) {
		List<Menu> menuList = mapper.getMenuTop(account);
		
		for(Menu bean : menuList) {
			String auth = bean.getAuthoritys();
			if(auth!=null && !"".equals(auth)) {
				List<String> menuAuth = new ArrayList<String>();
				String[] strs = auth.split(",");
				for(String s : strs) {
					menuAuth.add(s);
				}
				bean.setAuthority(menuAuth);
			}
			
			List<MenuChildren> clildList = bean.getChildren();
			if(clildList!=null && clildList.size()>0) {
				for(MenuChildren clild : clildList) {
					String auth_clild = clild.getAuthoritys();
					if(auth_clild!=null && !"".equals(auth_clild)) {
						List<String> menuChildAuth = new ArrayList<String>();
						String[] strs_child = auth_clild.split(",");
						for(String s : strs_child) {
							menuChildAuth.add(s);
						}
						clild.setAuthority(menuChildAuth);
					}
				}
			}
		}
		return menuList;
	}
	
	@Override
	public List<MenuChildren> getMenuChildren(String account,String menuid) {
		return mapper.getMenuChildren(account,menuid);
	}

	@Override
	public List<MessageEntity> getMessage(String account) {
		return mapper.getMessageByUserCode(account);
	}


	@Override
	public String updateMessage(String account, String type) {
		int num = mapper.updateMessageByUserCodeType(account,type);
		String response = String.format("清空%s成功.",type);
		if(num<1) {
			response = "没有新的消息.";
		}
		return response;
	}

	@Override
	public MessageCountEntity getMessageCount(String account) {
		return mapper.getMessageCountByUserCode(account);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String registerSubmit(String mail,String password,String type) {
		RegisterStepOne registerStepOne = new RegisterStepOne();
		registerStepOne.setMail(mail);
		registerStepOne.setPassword(Util.getMD5(password));
		registerStepOne.setType(type);
		
		int userNum = mapper.isUser(mail);
		
		if(userNum>0) {
			return "账号已存在.";
		}
		
		int insertUserNum = mapper.insertUser(registerStepOne);
		
		if(insertUserNum<1) {
			return "注册失败，请检查格式.";
		}
		
		Integer role_id = 5;
		
		if("2".equals(type) || "4".equals(type)) {
			role_id = 6;
		}
		
		int insertUserRoleNum = mapper.insertUserRole(registerStepOne.getId(),role_id);
		
		if(insertUserRoleNum>0) {
			return "注册成功";
		}
		return "注册失败";
	}

	@Override
	public String registerCode(String mail) {
		String code = Util.randomCode();
		emailUtils.sendRegisterCode(mail,code);
		return code;
	}


}
