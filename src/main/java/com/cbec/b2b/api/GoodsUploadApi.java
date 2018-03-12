package com.cbec.b2b.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.entity.GoodsUpload.Offer;
import com.cbec.b2b.entity.GoodsUpload.SearchOffer;
import com.cbec.b2b.entity.GoodsUpload.SendType;
import com.cbec.b2b.entity.GoodsUpload.UploadInfo;
import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.SearchGoods;
import com.cbec.b2b.service.IGoodsUploadService;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping(value = "/api/goods")
public class GoodsUploadApi {
    @Autowired
    IGoodsUploadService service;

    @RequestMapping(value = "/supplier/uploadinfo")
    public List<UploadInfo> uploadinfoOfSupplier(@RequestHeader(value = "userid") String userid) {
    	return service.getUploadInfo(userid);
    }
    @RequestMapping(value = "/operate/uploadinfo")
    public List<UploadInfo> uploadinfoOfOperate(@RequestHeader(value = "userid") String userid) {
    	return service.getUploadInfo(userid);
    }
    @RequestMapping(value = "/supplier/upload")
    public String writeUploadInfo(@RequestBody UploadInfo uploadInfo) {
    	int c = service.writeUploadInfo(uploadInfo);
    	String result="0";
    	if(c>0) result="1";
    	return result;
    } 
    @RequestMapping(value = "/supplier/delupload")
    public String deleteUploadInfoOfSupplier(@RequestBody UploadInfo uploadInfo) {
    	int c = service.deleteUploadInfo(uploadInfo);
    	String result="0";
    	if(c>0) result="1";
    	return result;
    }
    @RequestMapping(value = "/operate/delupload")
    public String deleteUploadInfoOfOperate(@RequestBody UploadInfo uploadInfo) {
    	int c = service.deleteUploadInfo(uploadInfo);
    	String result="0";
    	if(c>0) result="1";
    	return result;
    }
    @RequestMapping(value = "/supplier/goodslist")
    public PageInfo<Goods> getGoodsListOfSupplier(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods ) {
    	PageHelper.startPage(searchGoods.getCurrent(),searchGoods.getPageSize());
    	List<Goods> LGoods = service.getB2BGoodsListToOffer(searchGoods);
    	PageInfo<Goods> pageData = new PageInfo<Goods>(LGoods);
        return pageData;
    }
    @RequestMapping(value = "/operate/goodslist")
    public PageInfo<Goods> getGoodsListOfOperate(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods ) {
    	PageHelper.startPage(searchGoods.getCurrent(),searchGoods.getPageSize());
    	List<Goods> LGoods = service.getGoodsList(searchGoods);
    	PageInfo<Goods> pageData = new PageInfo<Goods>(LGoods);
        return pageData;
    }
    @RequestMapping(value = "/purchasers/goodslist")
    public PageInfo<Goods> getGoodsListOfPurchasers(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods ) {
    	PageHelper.startPage(searchGoods.getCurrent(),searchGoods.getPageSize());
    	List<Goods> LGoods = service.getGoodsList(searchGoods);
    	PageInfo<Goods> pageData = new PageInfo<Goods>(LGoods);
        return pageData;
    }
    @RequestMapping(value = "/supplier/b2bgoodslist")
    public PageInfo<Goods> getB2BGoodsList(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods ) {
    	PageHelper.startPage(searchGoods.getCurrent(),searchGoods.getPageSize());
    	List<Goods> LGoods = service.getB2BGoodsList(searchGoods);
    	PageInfo<Goods> pageData = new PageInfo<Goods>(LGoods);
        return pageData;
    }
    @RequestMapping(value = "/supplier/offerinfo")
    public PageInfo<Offer> offerinfoOfSupplier(@RequestHeader(value = "userid") String userid,@RequestBody SearchOffer searchOffer) {
    	searchOffer = service.getSearchOffer(userid, searchOffer);
    	PageHelper.startPage(searchOffer.getCurrent(),searchOffer.getPageSize());
    	List<Offer> LOffer = service.getOfferInfo(searchOffer);
    	PageInfo<Offer> pageData = new PageInfo<Offer>(LOffer);
        return pageData;
    }
    @RequestMapping(value = "/operate/offerinfo")
    public PageInfo<Offer> offerinfoOfOperate(@RequestHeader(value = "userid") String userid,@RequestBody SearchOffer searchOffer) {
    	searchOffer = service.getSearchOffer(userid, searchOffer);
    	PageHelper.startPage(searchOffer.getCurrent(),searchOffer.getPageSize());
    	List<Offer> LOffer = service.getOfferInfo(searchOffer);
    	PageInfo<Offer> pageData = new PageInfo<Offer>(LOffer);
        return pageData;
    }
    @RequestMapping(value = "/supplier/offerbyid")
    public Offer offerByIdOfOperate(@RequestHeader(value = "userid") String userid,@RequestBody String id) {
    	Offer offer = service.getOfferById(id);
        return offer;
    }
    @RequestMapping(value = "/supplier/updateoffer")
    public String updateOffer(@RequestBody Offer offer) {
    	return String.valueOf(service.updateOffer(offer));
    }
    @RequestMapping(value = "/supplier/updateofferflag")
    public String updateOfferFlag(@RequestBody Offer offer) {
    	if("1".equals(offer.getFlag())) {
    		offer.setFlag("0");
    	}else {
    		offer.setFlag("1");
    	}
    	return String.valueOf(service.updateOffer(offer));
    }
    @RequestMapping(value = "/supplier/offer")
    public String writeOffer(@RequestHeader(value = "userid") String userid,@RequestBody List<Offer> offerList) {
    	return String.valueOf(service.writeOffer(userid,offerList));
    }
    @RequestMapping(value = "/sendtype")
    public List<SendType> sendType() {
    	
    	return service.getSendType();
    }
}



