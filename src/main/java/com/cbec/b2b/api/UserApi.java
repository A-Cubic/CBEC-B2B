package com.cbec.b2b.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.common.Util;
import com.cbec.b2b.entity.menu.Menu;
import com.cbec.b2b.entity.menu.MenuChildren;
import com.cbec.b2b.entity.response.CurrentUser;
import com.cbec.b2b.entity.response.LoginResponseEntity;
import com.cbec.b2b.service.IUserService;

@RestController
@RequestMapping(value = "/api")
public class UserApi {
    @Autowired
    IUserService service;
    

    @RequestMapping(value = "/validate")
    public LoginResponseEntity validate(@RequestParam String userName,@RequestParam String password) {
        return service.validate(userName, password);
    }
    
    @RequestMapping(value = "/currentUser")
    public CurrentUser getUser(@RequestParam String userName) {
        return service.getUser(userName);
    }
    
    @RequestMapping(value = "/menu")
    public List<Menu> getMenu(@RequestParam String userid) {
    	List<Menu> lm= service.getMenuTop(userid);
//    	for(Menu m :lm) {
//    		List<MenuChildren> mcList = service.getMenuChildren(userid,String.valueOf(m.getId()) );
//    		m.setChildren(mcList);
//    	}
        return lm;
    }
}



