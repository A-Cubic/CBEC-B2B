package com.cbec.b2b.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.api.GoodsUploadApi;
import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.common.Util;
import com.cbec.b2b.entity.GoodsUpload.Offer;
import com.cbec.b2b.entity.GoodsUpload.SearchOffer;
import com.cbec.b2b.entity.GoodsUpload.SendType;
import com.cbec.b2b.entity.GoodsUpload.UploadInfo;
import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.SearchGoods;

@RestController
@RequestMapping(value = "/llback/goods")
public class GoodsUploadController {
    @Autowired
    GoodsUploadApi api;

    @RequestMapping(value = "/supplier/uploadinfo")
    public List<UploadInfo> uploadinfoForSupplier(@RequestHeader(value = "userid") String userid,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.uploadinfoForSupplier(userid);
    }
    @RequestMapping(value = "/operate/uploadinfo")
    public List<UploadInfo> uploadinfo(@RequestHeader(value = "userid") String userid,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.uploadinfoForOperate(userid);
    }
    @RequestMapping(value = "/supplier/upload")
    public String writeUploadInfo(@RequestBody UploadInfo uploadInfo,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.writeUploadInfo(uploadInfo);
    }
    @RequestMapping(value = "/supplier/delupload")
    public String deleteUploadInfoForSupplier(@RequestBody UploadInfo uploadInfo,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.deleteUploadInfoForSupplier(uploadInfo);
    } 
    @RequestMapping(value = "/operate/delupload")
    public String deleteUploadInfoForOperate(@RequestBody UploadInfo uploadInfo,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.deleteUploadInfoForOperate(uploadInfo);
    } 
    @RequestMapping(value = "/supplier/list")
    public PageInfo<Goods> goodslistForSupplier(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.getGoodsListForSupplier(userid,searchGoods);
    } 
    @RequestMapping(value = "/operate/list")
    public PageInfo<Goods> goodslistForOperate(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.getGoodsListForOperate(userid,searchGoods);
    } 
    @RequestMapping(value = "/purchasers/list")
    public PageInfo<Goods> goodslistForPurchasers(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.getGoodsListForPurchasers(userid,searchGoods);
    } 
    @RequestMapping(value = "/supplier/b2blist")
    public PageInfo<Goods> b2bgoodslist(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.getB2BGoodsList(userid,searchGoods);
    } 
    @RequestMapping(value = "/supplier/offerinfo")
    public List<Offer> offerinfoForSupplier(@RequestHeader(value = "userid") String userid,@RequestBody SearchOffer searchOffer,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.offerinfoForSupplier(userid,searchOffer);
    }
    @RequestMapping(value = "/operate/offerinfo")
    public List<Offer> offerinfoForOperate(@RequestHeader(value = "userid") String userid,@RequestBody SearchOffer searchOffer,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.offerinfoForOperate(userid,searchOffer);
    }
    @RequestMapping(value = "/supplier/updateoffer")
    public String updateOffer(@RequestBody Offer offer,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.updateOffer(offer);
    }
    @RequestMapping(value = "/supplier/offer")
    public String offer(@RequestBody Offer offer,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.writeOffer(offer);
    }
    @RequestMapping(value = "/sendtype")
    public List<SendType> sendType(HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.sendType();
    }
    
}



