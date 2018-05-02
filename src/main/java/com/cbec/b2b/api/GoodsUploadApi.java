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
import com.cbec.b2b.entity.GoodsUpload.Goods;
import com.cbec.b2b.entity.GoodsUpload.GoodsList;
import com.cbec.b2b.entity.GoodsUpload.GoodsNumList;
import com.cbec.b2b.entity.GoodsUpload.Offer;
import com.cbec.b2b.entity.GoodsUpload.SearchGoods;
import com.cbec.b2b.entity.GoodsUpload.SearchOffer;
import com.cbec.b2b.entity.GoodsUpload.SearchSellNum;
import com.cbec.b2b.entity.GoodsUpload.SellNum;
import com.cbec.b2b.entity.GoodsUpload.SendType;
import com.cbec.b2b.entity.GoodsUpload.UploadInfo;
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
    	searchGoods.setUserCode(userid);
    	PageHelper.startPage(searchGoods.getCurrent(),searchGoods.getPageSize());
    	List<GoodsList> LGoods = service.getB2BGoodsListToOffer(searchGoods);
    	PageInfo<GoodsList> pageData = new PageInfo<GoodsList>(LGoods);
        return pageData;
    }
    @RequestMapping(value = "/goodsnum")
    public PageInfo<GoodsNumList> getGoodsNum(@RequestHeader(value = "userid") String userid ,@RequestBody Map<String,String> request) {
    	int current =1,pagesize=10;
    	try {
    		current = Integer.parseInt(request.get("current"));
    		pagesize = Integer.parseInt(request.get("pageSize"));
    	} catch (NumberFormatException e) {
    	    
    	}
    	String type = service.getUserType(userid);
//    	if("0".equals(type)||"5".equals(type)) {
//    		PageHelper.startPage( current,pagesize);
//        	List<GoodsNumList> LGoods = service.getGoodsNumList(request.get("search"),request.get("warehouse"));
//        	PageInfo<GoodsNumList> pageData = new PageInfo<GoodsNumList>(LGoods);
//            return pageData;
//		}else {
//    		PageHelper.startPage( current,pagesize);
//        	List<GoodsNumList> LGoods = service.getGoodsNumListOfSupplier(userid,request.get("search"),request.get("warehouse"));
//        	PageInfo<GoodsNumList> pageData = new PageInfo<GoodsNumList>(LGoods);
//	        return pageData;
//		}
    	PageHelper.startPage( current,pagesize);
    	List<GoodsNumList> LGoods = service.getGoodsNumList(request.get("search"),request.get("warehouse"));
    	PageInfo<GoodsNumList> pageData = new PageInfo<GoodsNumList>(LGoods);
        return pageData;
    }
    @RequestMapping(value = "/numbybarcode")
    public GoodsNumList getGoodsNumByBarcode(@RequestBody Map<String,String> request) {
    	GoodsNumList Goods = service.getGoodsNumByBarcode(request.get("id"));
        return Goods;
    	
    }
    @RequestMapping(value = "/updategoodsnum")
    public MsgResponse updateGoodsNum(@RequestHeader(value = "userid") String userid,@RequestBody Map<String,String> request ) {
		MsgResponse response = new MsgResponse();
//		try {
			String barcode =request.get("barcode");
			int rb = Integer.valueOf(request.get("rb"));
			int hg = Integer.valueOf(request.get("hg"));
			int gj = Integer.valueOf(request.get("gj"));
			String s = service.updateGoodsNum(userid,barcode,rb,hg,gj);
			response.setMsg(s);
	    	response.setType("1");
//		}catch(Exception e) {
//			response.setMsg("接口参数错误！");
//		}		
    	
		return response;
    }
    @RequestMapping(value = "/getsellnum")
    public PageInfo<SellNum> getSellNumList(@RequestBody SearchSellNum searchSellNum) {
    	PageHelper.startPage(searchSellNum.getCurrent(),searchSellNum.getPageSize());
    	List<SellNum> LGoods = service.getSellNumList(searchSellNum);
    	PageInfo<SellNum> pageData = new PageInfo<SellNum>(LGoods);
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



