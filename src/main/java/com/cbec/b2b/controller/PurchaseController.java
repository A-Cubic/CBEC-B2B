package com.cbec.b2b.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.api.PurchaseApi;
import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.common.Util;
import com.cbec.b2b.entity.MsgResponse;
import com.cbec.b2b.entity.purchase.ChatRequest;
import com.cbec.b2b.entity.purchase.ChatResponse;
import com.cbec.b2b.entity.purchase.Inquiry;
import com.cbec.b2b.entity.purchase.Purchase;
import com.cbec.b2b.entity.purchase.PurchaseAndGood;
import com.cbec.b2b.entity.purchase.PurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseList;

@RestController
@RequestMapping(value = "/llback/purchase")
public class PurchaseController {
	
    @Autowired
    PurchaseApi api;

    @RequestMapping(value = "/chat/list")
    public List<ChatResponse> listChat(@RequestBody ChatRequest request,@RequestHeader(value = "userid") String userid,HttpServletResponse res) {
		Util.responseResultSuccess(res);
		request.setSender(userid);
        return api.listChat(request);
    }
    
    @RequestMapping(value = "/chat/send")
    public MsgResponse sendChat(@RequestBody ChatRequest request,@RequestHeader(value = "userid") String userid,HttpServletResponse res) {
		Util.responseResultSuccess(res);
		request.setSender(userid);
		request.setId(Util.createUuid());;
        return api.sendChat(request);
    }
    
    @RequestMapping(value = "/goods")
    public PageInfo<PurchaseGoods> PurchaseGoods(@RequestBody SearchPurchaseGoods searchPurchaseGoods,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.PurchaseGoods(searchPurchaseGoods);
    }
    @RequestMapping(value = "/add")
    public Purchase addPurchase(@RequestBody Purchase purchase,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.addPurchase(purchase);
    }

    @RequestMapping(value = "/update")
    public MsgResponse updatePurchase(@RequestBody Purchase purchase,HttpServletResponse res) {
		Util.responseResultSuccess(res);
		MsgResponse response = new MsgResponse();
    	response.setMsg(api.updatePurchase(purchase));
    	response.setType("1");
    	return response;
    }
    @RequestMapping(value = "/update/stage")
    public MsgResponse updatePurchaseStage(@RequestBody Map<String,Object> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
    	return api.updatePurchaseStage((String)request.get("purchasesn"),(String)request.get("stage"));
    }
    @RequestMapping(value = "/goods/add")
    public MsgResponse addPurchaseGoods(@RequestBody PurchaseAndGood purchaseAndGood,HttpServletResponse res) {
		Util.responseResultSuccess(res);
		return api.addPurchaseGoods(purchaseAndGood);
    }
    @RequestMapping(value = "/goods/addnew")
    public List<PurchaseGoods> addPurchaseGoodsNew(@RequestBody PurchaseAndGood purchaseAndGood,HttpServletResponse res) {
		Util.responseResultSuccess(res);
		return api.addPurchaseGoodsNew(purchaseAndGood);
    }
    @RequestMapping(value = "/goods/update")
    public MsgResponse updatePurchaseGoods(@RequestBody PurchaseGoods purchaseGoods,HttpServletResponse res) {
		Util.responseResultSuccess(res);
		MsgResponse response = new MsgResponse();
    	response.setMsg(api.updatePurchaseGoods(purchaseGoods));
    	response.setType("1");
    	return response;
    }
    @RequestMapping(value = "/goods/del")
    public MsgResponse delPurchaseGoods(@RequestBody List<PurchaseGoods> purchaseGoodsList,HttpServletResponse res) {
		Util.responseResultSuccess(res);
		MsgResponse response = new MsgResponse();
    	response.setMsg(api.delPurchaseGoods(purchaseGoodsList));
    	response.setType("1");
    	return response;
    }
    @RequestMapping(value = "/split")
    public MsgResponse splitPurchase(@RequestBody SearchPurchaseGoods searchPurchaseGoods,HttpServletResponse res) {
		Util.responseResultSuccess(res);
		MsgResponse response = new MsgResponse();
		if(searchPurchaseGoods.getPurchasesn()=="") {
			response.setMsg("没有采购单号！");
			return response;
		}else {
			String st = api.splitPurchase(searchPurchaseGoods);
			if(st != "提交完成") {
				response.setMsg(st);
				return response;
			}else {
				response.setMsg(st);
		    	response.setType("1");
		    	return response;
			}
			
		}
    	
    }
    
    /****************************************** 客服部分 ***************************************/
    @RequestMapping(value = "/operate/list")
    public PageInfo<Purchase> listOfOperate(@RequestBody SearchPurchaseList searchPurchaseList,HttpServletResponse res) {
		Util.responseResultSuccess(res);
		if(searchPurchaseList.getTimes() !=null && searchPurchaseList.getTimes().length>0) {
			String[] times = searchPurchaseList.getTimes() ;
			for(int i=0;i<times.length;i++) {
				if(i==0) {
					searchPurchaseList.setTimeBegin(times[i].split("T")[0]);
				}else {
					searchPurchaseList.setTimeEnd(times[i].split("T")[0]);
				}
			}
		}
        return api.listOfOperate(searchPurchaseList);
    }
    
    @RequestMapping(value = "/operate/info/details")
    public Purchase getPurchaseOfOperate(@RequestBody Map<String,Object> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.getPurchaseOfOperate((String)request.get("purchasesn"));
    }
    
    @RequestMapping(value = "/operate/goods")
    public PageInfo<PurchaseGoods> goodsListOfOperate(@RequestBody Map<String,Object> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.goodsListOfOperate((String)request.get("purchasesn"),(Integer)request.get("current"),(Integer)request.get("pageSize"));
    }
    
    @RequestMapping(value = "/operate/update/fee")
    public MsgResponse updateFeeOfOperate(@RequestBody Map<String,Object> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.updateFeeOfOperate((String)request.get("purchasesn"),(String)request.get("waybillfeeValue"));
    }
    
    @RequestMapping(value = "/operate/update/price")
    public MsgResponse updatePriceOfOperate(@RequestBody Map<String,Object> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.updatePriceOfOperate((String)request.get("id"),(String)request.get("realprice"));
    }
    
    @RequestMapping(value = "/operate/supply/list")
    public List<Inquiry> supplyListOfOperate(@RequestBody Map<String,Object> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.supplyListOfOperate((String)request.get("purchasesn"),(String)request.get("goodsid"));
    }
    
    @RequestMapping(value = "/operate/supply/flag")
    public MsgResponse updateSupplyFlagOfOperate(@RequestBody Map<String,Object> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.updateSupplyFlagOfOperate((String)request.get("id"),(String)request.get("flag"));
    }

    
    /****************************************** 供应商部分 ***************************************/
    @RequestMapping(value = "/supplier/list")
    public PageInfo<Purchase> PurchaseListOfSupplier(@RequestHeader(value = "userid") String userid,@RequestBody SearchPurchaseList searchPurchaseList,HttpServletResponse res) {
		Util.responseResultSuccess(res);
		if(searchPurchaseList.getTimes() !=null && searchPurchaseList.getTimes().length>0) {
			String[] times = searchPurchaseList.getTimes() ;
			for(int i=0;i<times.length;i++) {
				if(i==0) {
					searchPurchaseList.setTimeBegin(times[i].split("T")[0]);
				}else {
					searchPurchaseList.setTimeEnd(times[i].split("T")[0]);
				}
			}
		}
        return api.PurchaseListOfSupplier(userid,searchPurchaseList);
    }
    
    @RequestMapping(value = "/supplier/info/details")
    public Purchase getPurchaseBySnOfSupplier(@RequestBody Map<String,Object> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.getPurchaseBySnOfSupplier((String)request.get("purchasesn"));
    }
    
    @RequestMapping(value = "/supplier/inquiry")
    public List<Inquiry> getInquiryBySnOfSupplier(@RequestHeader(value = "userid") String userid,@RequestBody Map<String,Object> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.getInquiryBySnOfSupplier(userid,(String)request.get("purchasesn"));
    }
    
    @RequestMapping(value = "/supplier/update/price")
    public MsgResponse updatePriceOfSupplier(@RequestBody List<Inquiry> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.updatePriceOfSupplier(request);
    }
    
    
    /****************************************** 采购商部分 ***************************************/
    @RequestMapping(value = "/purchasers/list")
    public PageInfo<Purchase> PurchaseListOfPurchasers(@RequestBody SearchPurchaseList searchPurchaseList,HttpServletResponse res) {
		Util.responseResultSuccess(res);
		if(searchPurchaseList.getTimes() !=null && searchPurchaseList.getTimes().length>0) {
			String[] times = searchPurchaseList.getTimes() ;
			for(int i=0;i<times.length;i++) {
				if(i==0) {
					searchPurchaseList.setTimeBegin(times[i].split("T")[0]);
				}else {
					searchPurchaseList.setTimeEnd(times[i].split("T")[0]);
				}
			}
		}
        return api.PurchaseListOfPurchasers(searchPurchaseList);
    }
    
    @RequestMapping(value = "/purchasers/info/details")
    public Purchase getPurchaseBySnOfPurchasers(@RequestBody Map<String,Object> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.getPurchaseBySnOfPurchasers((String)request.get("purchasesn"));
    }
    
    @RequestMapping(value = "/purchasers/goods")
    public List<PurchaseGoods> goodsListOfPurchasers(@RequestBody Map<String,Object> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.goodsListOfPurchasers((String)request.get("purchasesn"));
    }
    
    @RequestMapping(value = "/purchasers/update/price")
    public MsgResponse updatePriceOfPurchasers(@RequestBody Map<String,Object> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.updatePriceOfPurchasers((String)request.get("id"),(String)request.get("expectprice"),(String)request.get("total"));
    }
}



