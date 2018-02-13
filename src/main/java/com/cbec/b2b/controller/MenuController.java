package com.cbec.b2b.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.common.Util;
import com.cbec.b2b.entity.menu.Menu;
import com.cbec.b2b.entity.menu.MenuChildren;
import com.cbec.b2b.service.IUserService;

@RestController
@RequestMapping(value = "/llback/system")
public class MenuController {
    @Autowired
    IUserService service;
    
    @RequestMapping(value = "/menu")
    public List<Menu> menu(@RequestHeader(value = "userid") String userid,HttpServletResponse res) {
    	List<Menu> lm= service.getMenuTop(userid);
    	for(Menu m :lm) {
    		List<MenuChildren> mcList = service.getMenuChildren(userid,String.valueOf(m.getId()) );
    		m.setChildren(mcList);
    	}
    	Util.responseResultSuccess(res);
        return lm;
    }
    
}



