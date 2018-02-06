package com.cbec.b2b.service;

import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.response.LoginResponseEntity;
import com.cbec.b2b.entity.response.UserResponseEntity;
import com.cbec.b2b.response.Response;

@Service
public interface IUserService {
	Response<LoginResponseEntity> validate(String account,String password);
	Response<UserResponseEntity> getUser(String account);
	Boolean isAuth(String account,String url);
}
