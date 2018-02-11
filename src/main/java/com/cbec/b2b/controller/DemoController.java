package com.cbec.b2b.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cbec.b2b.api.DemoApi;
import com.cbec.b2b.entity.Demo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
 * 用于分页demo
 * @author fei.yu
 *
 */
@RestController
@RequestMapping(value = "/llback/demo")
public class DemoController {
	@Autowired
    DemoApi api;
	
	@RequestMapping(value = "/pagedemo")
    public PageInfo<Demo> pageDemo(@RequestBody Map<String,Object> parmsMap){
		return api.demo((int)parmsMap.get("pageNumber"), (int)parmsMap.get("pageSize"));
    }
}
