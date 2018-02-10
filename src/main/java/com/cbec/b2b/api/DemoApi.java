package com.cbec.b2b.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.entity.Demo;
import com.cbec.b2b.entity.page.PageEntity;
import com.cbec.b2b.service.IDemoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping(value = "/api")
public class DemoApi {
    @Autowired
    IDemoService service;
    
    @RequestMapping(value = "/demo")
    public PageInfo<Demo> demo(@RequestParam(value = "pageNumber",defaultValue = "1")int currentPage,
            @RequestParam(value = "pageSize",defaultValue = "15")int pageSize) {
    	PageHelper.startPage(currentPage,pageSize);
    	List<Demo> demo = service.pageDemo();
    	PageInfo<Demo> pageData = new PageInfo<Demo>(demo);
//    	PageEntity<Demo> pageData = new PageEntity<Demo>(currentPage, pageSize, demo.size());
        return pageData;
    }
    
}



