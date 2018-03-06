package com.cbec.b2b.api;

import java.util.List;

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
    	List<Goods> LGoods = service.getGoodsList(searchGoods);
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
    public List<Offer> offerinfoOfSupplier(@RequestHeader(value = "userid") String userid,@RequestBody SearchOffer searchOffer) {
    	return service.getOfferInfo(userid,searchOffer);
    }
    @RequestMapping(value = "/operate/offerinfo")
    public List<Offer> offerinfoOfOperate(@RequestHeader(value = "userid") String userid,@RequestBody SearchOffer searchOffer) {
    	return service.getOfferInfo(userid,searchOffer);
    }
    @RequestMapping(value = "/supplier/updateoffer")
    public String updateOffer(@RequestBody Offer offer) {
    	int c = service.updateOffer(offer);
    	String result="0";
    	if(c>0) result="1";
    	return result;
    }
    @RequestMapping(value = "/supplier/offer")
    public String writeOffer(@RequestBody Offer offer) {
    	int c = service.writeOffer(offer);
    	String result="0";
    	if(c>0) result="1";
    	return result;
    }
    @RequestMapping(value = "/sendtype")
    public List<SendType> sendType() {
    	
    	return service.getSendType();
    }
}



