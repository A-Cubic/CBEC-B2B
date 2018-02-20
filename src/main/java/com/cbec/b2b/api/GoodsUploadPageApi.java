package com.cbec.b2b.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.entity.Demo;
import com.cbec.b2b.entity.Catelog.Brand;
import com.cbec.b2b.entity.Catelog.CateOne;
import com.cbec.b2b.entity.Catelog.CateTWO;
import com.cbec.b2b.entity.Catelog.CateThree;
import com.cbec.b2b.entity.Catelog.CateType;
import com.cbec.b2b.entity.Catelog.CateType2;
import com.cbec.b2b.entity.Catelog.Catelog;
import com.cbec.b2b.entity.GoodsUpload.Offer;
import com.cbec.b2b.entity.GoodsUpload.UploadInfo;
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
import com.cbec.b2b.entity.menu.Menu;
import com.cbec.b2b.entity.response.CurrentUser;
import com.cbec.b2b.entity.response.LoginResponseEntity;
import com.cbec.b2b.service.IGoodsUploadService;
import com.cbec.b2b.service.IHomePageService;
import com.cbec.b2b.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping(value = "/api")
public class GoodsUploadPageApi {
    @Autowired
    IGoodsUploadService service;

    @RequestMapping(value = "/uploadinfo")
    public List<UploadInfo> uploadinfo(@RequestHeader(value = "userid") String userid) {
    	return service.getUploadInfo(userid);
    }
    @RequestMapping(value = "/upload")
    public String writeUploadInfo(@RequestBody UploadInfo uploadInfo) {
    	int c = service.writeUploadInfo(uploadInfo);
    	String result="0";
    	if(c>0) result="1";
    	return result;
    }
    @RequestMapping(value = "/delupload")
    public String deleteUploadInfo(@RequestBody UploadInfo uploadInfo) {
    	int c = service.deleteUploadInfo(uploadInfo);
    	String result="0";
    	if(c>0) result="1";
    	return result;
    }
    @RequestMapping(value = "/goodslist1")
    public PageInfo<Goods> getGoodsList(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods ) {
    	PageHelper.startPage(searchGoods.getPageNumber(),searchGoods.getPageSize());
    	List<Goods> LGoods = new ArrayList<Goods>();
    	for(int i=0;i<100;i++) {
    		Goods g1 = new Goods();
        	g1.setId(1000+i);
        	g1.setPrice("500"+i);
        	g1.setGoodsname("测试商品"+i);
        	g1.setSlt("http://ecc-product.oss-cn-beijing.aliyuncs.com/goodsuploads/201707070941382750.jpg");
        	LGoods.add(g1);
    	}
    	PageInfo<Goods> pageData = new PageInfo<Goods>(LGoods);
//    	PageEntity<Demo> pageData = new PageEntity<Demo>(currentPage, pageSize, demo.size());
        return pageData;
    }
    @RequestMapping(value = "/offerinfo")
    public List<Offer> offerinfo(@RequestHeader(value = "userid") String userid) {
    	return service.getOfferInfo(userid);
    }
    @RequestMapping(value = "/updateoffer")
    public String updateOffer(@RequestBody Offer offer) {
    	int c = service.updateOffer(offer);
    	String result="0";
    	if(c>0) result="1";
    	return result;
    }
    @RequestMapping(value = "/offer")
    public String writeOffer(@RequestBody Offer offer) {
    	int c = service.writeOffer(offer);
    	String result="0";
    	if(c>0) result="1";
    	return result;
    }
}



