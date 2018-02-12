package com.cbec.b2b.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.entity.menu.Menu;
import com.cbec.b2b.entity.menu.MenuChildren;
import com.cbec.b2b.service.IDemoService;

@RestController
@RequestMapping(value = "/api")
public class MenuController {
    @Autowired
    IDemoService service;
    
    @RequestMapping(value = "/menu")
    public List<Menu> catalog(@RequestHeader(value = "userid") String userid,HttpServletResponse res) {
    	MenuChildren mc1 = new MenuChildren();
    	mc1.setName("分析页");
    	mc1.setPath("fenxi");
    	MenuChildren mc11 = new MenuChildren();
    	mc11.setName("监控页");
    	mc11.setPath("monitor");
    	MenuChildren mc2 = new MenuChildren();
    	mc2.setName("商品信息");
    	mc2.setPath("shangpin");
    	MenuChildren mc21 = new MenuChildren();
    	mc21.setName("商品上传");
    	mc21.setPath("goodsupload");

    	List<MenuChildren> lmc1= new ArrayList<MenuChildren>();
    	lmc1.add(mc1);
    	lmc1.add(mc11);
    	List<MenuChildren> lmc2 =new ArrayList<MenuChildren>();
    	lmc2.add(mc2);
    	lmc2.add(mc21);

    	Menu m1 = new Menu();
    	m1.setName("dashboard");
    	m1.setIcon("dashboard");
    	m1.setPath("dashboard");
    	m1.setChildren(lmc1);
    	Menu m2 = new Menu();
    	m2.setName("商品管理");
    	m2.setIcon("shop");
    	m2.setPath("goods");
    	m2.setChildren(lmc2);
    	
    	List<Menu> lm= new ArrayList<Menu>();
    	lm.add(m1);
    	lm.add(m2);
        return lm;
    }
    
}



