package com.cbec.b2b.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.menu.Menu;
import com.cbec.b2b.entity.menu.MenuChildren;
import com.cbec.b2b.entity.response.CurrentUser;
import com.cbec.b2b.entity.response.LoginResponseEntity;

@Service
public interface IUserService {
	LoginResponseEntity validate(String account,String password);
	CurrentUser getUser(String account);
	Boolean isAuth(String account,String url);
	List<Menu> getMenuTop(String account);
	List<MenuChildren> getMenuChildren(String account,String menuid);
}
