package com.cbec.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cbec.b2b.common.ContentErrorMsg;
import com.cbec.b2b.common.ServiceException;
import com.cbec.b2b.entity.response.CurrentUser;
import com.cbec.b2b.entity.response.LoginResponseEntity;
import com.cbec.b2b.mapper.UserMapper;
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
			ServiceException ex = new ServiceException(ContentErrorMsg.ERROR_3);
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
	public CurrentUser getUser(String account) {
		return mapper.getUser(account);
	}


}
