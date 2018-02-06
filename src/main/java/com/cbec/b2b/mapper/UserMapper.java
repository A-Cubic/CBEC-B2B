package com.cbec.b2b.mapper;

import org.apache.ibatis.annotations.Param;

import com.cbec.b2b.entity.response.CurrentUser;
import com.cbec.b2b.entity.response.LoginResponseEntity;

public interface UserMapper {
	LoginResponseEntity validate(@Param("account") String account,@Param("password") String password);
	Integer isAuth(@Param("account") String account,@Param("url") String url);
	CurrentUser getUser(@Param("account") String account);
}
