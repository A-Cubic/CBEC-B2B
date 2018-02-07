package com.cbec.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbec.b2b.common.ServiceException;
import com.cbec.b2b.entity.response.CurrentUser;
import com.cbec.b2b.entity.response.LoginResponseEntity;
import com.cbec.b2b.entity.response.UserResponseEntity;
import com.cbec.b2b.mapper.UserMapper;
import com.cbec.b2b.response.Response;
import com.cbec.b2b.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserMapper mapper;
	
	@Override
	public LoginResponseEntity validate(String account,String password) {
		LoginResponseEntity response = mapper.validate(account, password);
		if(response != null) {
			return response;
		}else {
			ServiceException ex = new ServiceException("账号或密码不正确！");
			throw ex;
		}
//		return response;
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
	public Response<UserResponseEntity> getUser(String account) {
		Response<UserResponseEntity> response = new Response<UserResponseEntity>();
		UserResponseEntity UserResponseEntity = new UserResponseEntity();
		CurrentUser currentUser = mapper.getUser(account);
		UserResponseEntity.setCurrentUser(currentUser);
		response.setResults(UserResponseEntity);
		return response;
	}


}
