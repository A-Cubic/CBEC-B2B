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

    @RequestMapping(value = "/uploadinfo")
    public List<UploadInfo> uploadinfo(@RequestHeader(value = "userid") String userid,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.uploadinfo(userid);
    }
    @RequestMapping(value = "/upload")
    public String writeUploadInfo(@RequestBody UploadInfo uploadInfo,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.writeUploadInfo(uploadInfo);
    }
    @RequestMapping(value = "/delupload")
    public String deleteUploadInfo(@RequestBody UploadInfo uploadInfo,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.deleteUploadInfo(uploadInfo);
    } 
    @RequestMapping(value = "/list")
    public PageInfo<Goods> goodslist(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.getGoodsList(userid,searchGoods);
    } 
    @RequestMapping(value = "/b2blist")
    public PageInfo<Goods> b2bgoodslist(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.getB2BGoodsList(userid,searchGoods);
    } 
    @RequestMapping(value = "/offerinfo")
    public List<Offer> offerinfo(@RequestHeader(value = "userid") String userid,@RequestBody SearchOffer searchOffer,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.offerinfo(userid,searchOffer);
    }
    @RequestMapping(value = "/updateoffer")
    public String updateOffer(@RequestBody Offer offer,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.updateOffer(offer);
    }
    @RequestMapping(value = "/offer")
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



