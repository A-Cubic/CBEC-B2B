package com.cbec.b2b.service;

import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.response.CurrentUser;
import com.cbec.b2b.entity.response.LoginResponseEntity;

@Service
public interface IUserService {
	LoginResponseEntity validate(String account,String password);
	CurrentUser getUser(String account);
	Boolean isAuth(String account,String url);
}
