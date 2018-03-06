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
    public List<UploadInfo> uploadinfoOfSupplier(@RequestHeader(value = "userid") String userid,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.uploadinfoOfSupplier(userid);
    }
    @RequestMapping(value = "/operate/uploadinfo")
    public List<UploadInfo> uploadinfo(@RequestHeader(value = "userid") String userid,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.uploadinfoOfOperate(userid);
    }
    @RequestMapping(value = "/supplier/upload")
    public String writeUploadInfo(@RequestBody UploadInfo uploadInfo,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.writeUploadInfo(uploadInfo);
    }
    @RequestMapping(value = "/supplier/delupload")
    public String deleteUploadInfoOfSupplier(@RequestBody UploadInfo uploadInfo,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.deleteUploadInfoOfSupplier(uploadInfo);
    } 
    @RequestMapping(value = "/operate/delupload")
    public String deleteUploadInfoOfOperate(@RequestBody UploadInfo uploadInfo,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.deleteUploadInfoOfOperate(uploadInfo);
    } 
    @RequestMapping(value = "/supplier/list")
    public PageInfo<Goods> goodslistOfSupplier(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.getGoodsListOfSupplier(userid,searchGoods);
    } 
    @RequestMapping(value = "/operate/list")
    public PageInfo<Goods> goodslistOfOperate(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.getGoodsListOfOperate(userid,searchGoods);
    } 
    @RequestMapping(value = "/purchasers/list")
    public PageInfo<Goods> goodslistOfPurchasers(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.getGoodsListOfPurchasers(userid,searchGoods);
    } 
    @RequestMapping(value = "/supplier/b2blist")
    public PageInfo<Goods> b2bgoodslist(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.getB2BGoodsList(userid,searchGoods);
    } 
    @RequestMapping(value = "/supplier/offerinfo")
    public List<Offer> offerinfoOfSupplier(@RequestHeader(value = "userid") String userid,@RequestBody SearchOffer searchOffer,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.offerinfoOfSupplier(userid,searchOffer);
    }
    @RequestMapping(value = "/operate/offerinfo")
    public List<Offer> offerinfoOfOperate(@RequestHeader(value = "userid") String userid,@RequestBody SearchOffer searchOffer,HttpServletResponse res ) {
		Util.responseResultSuccess(res);
    	return api.offerinfoOfOperate(userid,searchOffer);
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



