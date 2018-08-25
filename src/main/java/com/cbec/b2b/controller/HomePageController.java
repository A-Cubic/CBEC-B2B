package com.cbec.b2b.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.api.HomePageApi;
import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.common.SmsUtils;
import com.cbec.b2b.entity.Catelog.Catelog;
import com.cbec.b2b.entity.HomePage.Adver;
import com.cbec.b2b.entity.HomePage.GoodsInfo;
import com.cbec.b2b.entity.HomePage.GoodsList;
import com.cbec.b2b.entity.HomePage.GoodsListOld;
import com.cbec.b2b.entity.HomePage.Screen;
import com.cbec.b2b.entity.HomePage.SearchGoods;
import com.cbec.b2b.entity.HomePage.SearchGoods2;

@RestController
@RequestMapping(value = "/web")
public class HomePageController {
    @Autowired
    HomePageApi api;
    @Autowired
    SmsUtils smsUtils;

    @RequestMapping(value = "/catalog")
    public Catelog catalog() {
        return api.getCatalogAndBrands();
    }
    @RequestMapping(value = "/catalogNew")
    public Catelog catalogNew() {
        return api.getCatalogAndBrandsNew();
    }
    @RequestMapping(value = "/adver")
    public Adver adver() {
        return api.getAdver();
    }
    @RequestMapping(value = "/Screen")
    public Screen screen() {
        return api.getScreen();
    }
//    @RequestMapping(value = "/GoodsList")
//    public PageInfo<GoodsList> goodslist(@RequestBody SearchGoods searchGoods ) {
//    	return api.getGoodsList(searchGoods);
//    }
    @RequestMapping(value = "/GoodsList")
    public GoodsListOld goodslist(@RequestBody SearchGoods searchGoods ) {
    	return api.getGoodsList(searchGoods);
    }
    @RequestMapping(value = "/B2BGoodsList")
    public PageInfo<GoodsList> b2bGoodslist(@RequestBody SearchGoods searchGoods ) {
    	return api.getB2BGoodsList(searchGoods);
    }
    @RequestMapping(value = "/Goods")
    public GoodsInfo goods(@RequestBody SearchGoods2 searchGoods) {
    	return api.getGoods(searchGoods.getGoodsId());
    }

    @RequestMapping(value = "/Phone")
    public String Phone(@RequestBody Map<String,Object> request) {
    	
    	smsUtils.sendRegisterSuccess("13644237400");
    	return "OK";
    }
    
}



