package com.cbec.b2b.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.entity.Demo;
import com.cbec.b2b.entity.page.PageEntity;
import com.cbec.b2b.entity.response.LoginResponseEntity;
import com.cbec.b2b.service.IDemoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping(value = "/web")
public class HomePageApi {
    @Autowired
    IDemoService service;
    
    @RequestMapping(value = "/catalog")
    public String catalog() {
    	 
        return "{\"state\":0,\"results\":{\"美容护肤1\":[{\"catelog\":[{\"id\":0,\"value\":\"0\",\"childCate\":[{\"id\":0,\"value\":\"0\"}]}],\"brands\":[{\"id\":0,\"value\":\"0\"}]}],\"美容护肤2\":[{\"catelog\":[{\"id\":0,\"value\":\"0\",\"childCate\":[{\"id\":0,\"value\":\"0\"}]}],\"brands\":[{\"id\":0,\"value\":\"0\"}]}]}}";
    }
    
}



