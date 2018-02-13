package com.cbec.b2b.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbec.b2b.entity.menu.Menu;
import com.cbec.b2b.entity.menu.MenuChildren;
import com.cbec.b2b.entity.response.CurrentUser;
import com.cbec.b2b.entity.response.LoginResponseEntity;

public interface UserMapper {
	LoginResponseEntity validate(@Param("account") String account,@Param("password") String password);
	Integer isAuth(@Param("account") String account,@Param("url") String url);
	CurrentUser getUser(@Param("account") String account);
	List<Menu> getMenuTop(@Param("account") String account);
	List<MenuChildren> getMenuChildren(@Param("account") String account,@Param("menuid") String menuid);
}
