package com.cbec.b2b.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.api.HomePageApi;
import com.cbec.b2b.entity.Demo;
import com.cbec.b2b.entity.Catelog.Catelog;
import com.cbec.b2b.entity.HomePage.Adver;
import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.Screen;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping(value = "/web")
public class HomePageController {
    @Autowired
    HomePageApi api;

    @RequestMapping(value = "/catalog")
    public Catelog catalog() {
        return api.getCatalogAndBrands();
    }
    @RequestMapping(value = "/adver")
    public Adver adver() {
        return api.getAdver();
    }
    @RequestMapping(value = "/Screen")
    public Screen screen() {
        return api.getScreen();
    }
    @RequestMapping(value = "/GoodsList")
    public PageInfo<Goods> goodslist( ) {
    	return api.getGoodsList(1, 8);
    }
    
}



