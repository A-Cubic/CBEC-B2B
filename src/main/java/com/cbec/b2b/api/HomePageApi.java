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
//    	List<CateOne> lco = new ArrayList<CateOne>();
//    	
//    	CateType cateType =new CateType();
//    	cateType.setLevel1(lco);
//    	
//    	Catelog catelog = new Catelog();
//    	catelog.setState(0);
//    	catelog.setResults(cateType);
//    	
//        return catelog;
//    	
//    	
//    	
//    	CateThree ct = new CateThree();
//    	ct.setId(10001);
//    	ct.setValue("面膜");
//    	CateThree ct1 = new CateThree();
//    	ct1.setId(10002);
//    	ct1.setValue("脸霜");
//    	List<CateThree> lc3= new ArrayList<CateThree>();
//    	lc3.add(ct);
//    	lc3.add(ct1);
//
//    	CateTWO ct2 = new CateTWO();
//    	ct2.setId(101);
//    	ct2.setValue("护肤品");
//    	ct2.setChildCate(lc3);
//    	CateTWO ct21 = new CateTWO();
//    	ct21.setId(102);
//    	ct21.setValue("护肤品2");
//    	ct21.setChildCate(lc3);
//
//    	CateTWO ct3 = new CateTWO();
//    	ct3.setId(201);
//    	ct3.setValue("游戏");
//    	ct3.setChildCate(lc3);
//    	CateTWO ct31 = new CateTWO();
//    	ct31.setId(102);
//    	ct31.setValue("3C");
//    	ct31.setChildCate(lc3);
//
//    	List<CateTWO> lc2= new ArrayList<CateTWO>();
//    	lc2.add(ct2);
//    	lc2.add(ct21);
//    	List<CateTWO> lc21= new ArrayList<CateTWO>();
//    	lc21.add(ct3);
//    	lc21.add(ct31);
//
//    	Brand br = new Brand();
//    	br.setId(1);
//    	br.setValue("B+29");
//    	Brand br1 = new Brand();
//    	br1.setId(2);
//    	br1.setValue("CON");
//    	Brand br2 = new Brand();
//    	br2.setId(3);
//    	br2.setValue("FEyy");
//
//    	List<Brand> lb= new ArrayList<Brand>();
//    	lb.add(br);
//    	lb.add(br1);
//    	lb.add(br2);
//
//    	CateType2 gt= new CateType2();
//    	gt.setCatelog(lc2);
//    	gt.setBrands(lb);
//    	CateType2 gt1= new CateType2();
//    	gt1.setCatelog(lc21);
//    	gt1.setBrands(lb);
//
//    	List<CateType2> lc = new ArrayList<CateType2>();
//    	lc.add(gt);
//    	List<CateType2> lc1 = new ArrayList<CateType2>();
//    	lc1.add(gt1);
//
//    	CateOne co = new CateOne();
//    	co.setId(4);
//    	co.setValue("美用没装");
//    	co.setLevel2(lc);
//    	CateOne co1 = new CateOne();
//    	co1.setId(2);
//    	co1.setValue("其他销售");
//    	co1.setLevel2(lc1);
//    	
//
//    	List<CateOne> lco = new ArrayList<CateOne>();
//    	lco.add(co);
//    	lco.add(co1);
//    	
//    	CateType cateType =new CateType();
//    	cateType.setLevel1(lco);
//    	
//    	Catelog catelog = new Catelog();
//    	catelog.setState(0);
//    	catelog.setResults(cateType);
//    	
//        return catelog;
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



