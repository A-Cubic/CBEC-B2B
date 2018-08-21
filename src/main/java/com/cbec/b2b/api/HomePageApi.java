package com.cbec.b2b.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.entity.Catelog.Catelog;
import com.cbec.b2b.entity.HomePage.Adver;
import com.cbec.b2b.entity.HomePage.Country;
import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.GoodsInfo;
import com.cbec.b2b.entity.HomePage.GoodsList;
import com.cbec.b2b.entity.HomePage.GoodsListOld;
import com.cbec.b2b.entity.HomePage.GoodsType;
import com.cbec.b2b.entity.HomePage.Screen;
import com.cbec.b2b.entity.HomePage.ScreenType;
import com.cbec.b2b.entity.HomePage.SearchGoods;
import com.cbec.b2b.entity.HomePage.SendType;
import com.cbec.b2b.service.IHomePageService;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping(value = "/api")
public class HomePageApi {
    @Autowired
    IHomePageService service;
    

    @RequestMapping(value = "/catalog")
    public Catelog getCatalogAndBrands() {
    	return service.getCatalogAndBrands();
    }
    
    @RequestMapping(value = "/adver")
    public Adver getAdver() {
    	return service.getAdver();
    }
    @RequestMapping(value = "/goodslist")
    public GoodsListOld getGoodsList(@RequestBody SearchGoods searchGoods ) {
    	if(searchGoods.getPageNumber()==0) {
    		searchGoods.setPageNumber(1);
    	}
    	if(searchGoods.getPageSize()==0) {
    		searchGoods.setPageSize(20);
    	}
        return service.getGoodsList(searchGoods);
    }
//    @RequestMapping(value = "/goodslist")
//    public PageInfo<GoodsList> getGoodsList(@RequestBody SearchGoods searchGoods ) {
//    	if(searchGoods.getPageNumber()==0) {
//    		searchGoods.setPageNumber(1);
//    	}
//    	if(searchGoods.getPageSize()==0) {
//    		searchGoods.setPageSize(20);
//    	}
//    	PageHelper.startPage(searchGoods.getPageNumber(),searchGoods.getPageSize());
//    	List<GoodsList> LGoods = service.getGoodsList(searchGoods);
//    	PageInfo<GoodsList> pageData = new PageInfo<GoodsList>(LGoods);
//        return pageData;
//    }
    @RequestMapping(value = "/b2bgoodslist")
    public PageInfo<GoodsList> getB2BGoodsList(@RequestBody SearchGoods searchGoods ) {
    	if(searchGoods.getPageNumber()==0) {
    		searchGoods.setPageNumber(1);
    	}
    	if(searchGoods.getPageSize()==0) {
    		searchGoods.setPageSize(20);
    	}
    	PageHelper.startPage(searchGoods.getPageNumber(),searchGoods.getPageSize());
    	List<GoodsList> LGoods = service.getB2BGoodsList(searchGoods);
    	PageInfo<GoodsList> pageData = new PageInfo<GoodsList>(LGoods);
        return pageData;
    }

    @RequestMapping(value = "/screen")
    public Screen getScreen() {
    	List<Country> LCountry= service.getCountry();
    	List<SendType> LSendType= service.getSendType();
    	ScreenType st = new ScreenType();
    	st.setCountry(LCountry);
    	st.setSendtype(LSendType);

    	Screen s = new Screen();
    	s.setState(0);
    	s.setResults(st);
    	
    	return s;
    }
    @RequestMapping(value = "/goods")
    public GoodsInfo getGoods(@RequestBody String GoodsId ) {
    	List<Goods> LGoods= service.getGoodsByGoodsId(GoodsId);
    	for(Goods goods :LGoods) {
    		if(goods.getContent()!=null &&!"".equals(goods.getContent()))
    			goods.setContents(goods.getContent().split(","));
    		if(goods.getThumb()!=null&&!"".equals(goods.getThumb()))
    			goods.setThumbs(goods.getThumb().split(","));
    	}
        GoodsType gt = new GoodsType();
        gt.setGoods(LGoods);
    	GoodsInfo g = new GoodsInfo();
    	g.setState(0);
    	g.setResults(gt);
    	
    	return g;
    }
}



