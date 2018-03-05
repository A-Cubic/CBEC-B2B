package com.cbec.b2b.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.entity.Catelog.Brand;
import com.cbec.b2b.entity.Catelog.CateOne;
import com.cbec.b2b.entity.Catelog.CateTWO;
import com.cbec.b2b.entity.Catelog.CateThree;
import com.cbec.b2b.entity.Catelog.CateType;
import com.cbec.b2b.entity.Catelog.CateType2;
import com.cbec.b2b.entity.Catelog.Catelog;
import com.cbec.b2b.entity.HomePage.Adver;
import com.cbec.b2b.entity.HomePage.AdverType;
import com.cbec.b2b.entity.HomePage.Banner;
import com.cbec.b2b.entity.HomePage.Brands;
import com.cbec.b2b.entity.HomePage.Country;
import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.GoodsInfo;
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
    	List<Banner> LBanner= service.getBanner();
    	List<Brands> LBrands= service.getBrands();
    	List<Goods> LGoods = new ArrayList<Goods>();
    	Goods g1 = new Goods();
    	g1.setId(1001);
    	g1.setPrice("500");
    	g1.setGoodsname("可尔必思");
    	g1.setSlt("http://ecc-product.oss-cn-beijing.aliyuncs.com/goodsuploads/201707070941382750.jpg");
    	
    	LGoods.add(g1);
    	Goods g2 = new Goods();
    	g2.setId(1002);
    	g2.setPrice("100");
    	g2.setGoodsname("BBLab 玻尿酸原液");
    	g2.setSlt("http://ecc-product.oss-cn-beijing.aliyuncs.com/goodsuploads/5909497fNfa0bbe47.jpg");
    	
    	LGoods.add(g2);
    	
    	AdverType at = new AdverType();
    	at.setBanner(LBanner);
    	at.setBrands(LBrands);
    	at.setGoods(LGoods);
    	
    	Adver a = new Adver();
    	a.setState(0);
    	a.setResults(at);
    	
    	return a;
    }
    @RequestMapping(value = "/goodslist")
    public PageInfo<Goods> getGoodsList(@RequestBody SearchGoods searchGoods ) {
    	PageHelper.startPage(searchGoods.getCurrent(),searchGoods.getPageSize());
    	List<Goods> LGoods = service.getGoodsList(searchGoods);
    	PageInfo<Goods> pageData = new PageInfo<Goods>(LGoods);
        return pageData;
    }
    @RequestMapping(value = "/b2bgoodslist")
    public PageInfo<Goods> getB2BGoodsList(@RequestBody SearchGoods searchGoods ) {
    	PageHelper.startPage(searchGoods.getCurrent(),searchGoods.getPageSize());
    	List<Goods> LGoods = service.getB2BGoodsList(searchGoods);
    	PageInfo<Goods> pageData = new PageInfo<Goods>(LGoods);
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
    		if(!"".equals(goods.getContent()))
    			goods.setContents(goods.getContent().split(","));
    		if(!"".equals(goods.getThumb()))
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



