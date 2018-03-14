package com.cbec.b2b.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.entity.MsgResponse;
import com.cbec.b2b.entity.GoodsUpload.Offer;
import com.cbec.b2b.entity.GoodsUpload.SearchOffer;
import com.cbec.b2b.entity.GoodsUpload.SendType;
import com.cbec.b2b.entity.GoodsUpload.UploadInfo;
import com.cbec.b2b.entity.GoodsUpload.Goods;
import com.cbec.b2b.entity.GoodsUpload.GoodsList;
import com.cbec.b2b.entity.GoodsUpload.SearchGoods;
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
    //////////////////////////////////////////商品相关 begin////////////////////////////////////////////////
    @RequestMapping(value = "/supplier/goodslist")
    public PageInfo<GoodsList> getGoodsListOfSupplier(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods ) {
    	PageHelper.startPage(searchGoods.getCurrent(),searchGoods.getPageSize());
    	List<GoodsList> LGoods = service.getB2BGoodsListToOffer(searchGoods);
    	PageInfo<GoodsList> pageData = new PageInfo<GoodsList>(LGoods);
        return pageData;
    }
    @RequestMapping(value = "/supplier/b2bgoodslist")
    public PageInfo<GoodsList> getB2BGoodsList(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods ) {
    	PageHelper.startPage(searchGoods.getCurrent(),searchGoods.getPageSize());
    	List<GoodsList> LGoods = service.getB2BGoodsList(searchGoods);
    	PageInfo<GoodsList> pageData = new PageInfo<GoodsList>(LGoods);
        return pageData;
    }
    @RequestMapping(value = "/purchasers/goodslist")
    public PageInfo<GoodsList> getGoodsListOfPurchasers(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods ) {
    	PageHelper.startPage(searchGoods.getCurrent(),searchGoods.getPageSize());
    	List<GoodsList> LGoods = service.getGoodsList(searchGoods);
    	PageInfo<GoodsList> pageData = new PageInfo<GoodsList>(LGoods);
        return pageData;
    }
    @RequestMapping(value = "/operate/goodslist")
    public PageInfo<GoodsList> getGoodsListOfOperate(@RequestHeader(value = "userid") String userid,@RequestBody SearchGoods searchGoods ) {
    	PageHelper.startPage(searchGoods.getCurrent(),searchGoods.getPageSize());
    	List<GoodsList> LGoods = service.getGoodsListOfOperate(searchGoods);
    	PageInfo<GoodsList> pageData = new PageInfo<GoodsList>(LGoods);
        return pageData;
    }
    @RequestMapping(value = "/operate/update")
    public MsgResponse updateGoodsOfOperate(@RequestHeader(value = "userid") String userid,@RequestBody Goods goods ) {
		MsgResponse response = new MsgResponse();
    	response.setMsg(service.updateGoodsOfOperate(userid,goods));
    	response.setType("1");
		return response;
    }
    @RequestMapping(value = "/operate/goodsbyid")
    public Goods getGoodsById(@RequestBody Map<String,String> request) {
		return service.getGoodsById((String)request.get("id"));
    }
    //////////////////////////////////////////商品相关 end////////////////////////////////////////////////
    //////////////////////////////////////////报价单相关 begin////////////////////////////////////////////////
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
    //////////////////////////////////////////报价单相关 end////////////////////////////////////////////////
    @RequestMapping(value = "/sendtype")
    public List<SendType> sendType() {
    	
    	return service.getSendType();
    }
}



