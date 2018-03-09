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
    public List<PurchaseGoods> PurchaseGoods(@RequestBody SearchPurchaseGoods searchPurchaseGoods,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.PurchaseGoods(searchPurchaseGoods);
    }
    @RequestMapping(value = "/add")
    public MsgResponse addPurchase(@RequestBody Purchase purchase,HttpServletResponse res) {
		Util.responseResultSuccess(res);
		MsgResponse response = new MsgResponse();
    	response.setMsg(api.addPurchase(purchase));
    	response.setType("1");
        return response;
    }

    @RequestMapping(value = "/update")
    public MsgResponse updatePurchase(@RequestBody Purchase purchase,HttpServletResponse res) {
		Util.responseResultSuccess(res);
		MsgResponse response = new MsgResponse();
    	response.setMsg(api.updatePurchase(purchase));
    	response.setType("1");
    	return response;
    }
    @RequestMapping(value = "/goods/add")
    public MsgResponse addPurchaseGoods(@RequestBody List<PurchaseGoods> purchaseGoodsList,HttpServletResponse res) {
		Util.responseResultSuccess(res);
		MsgResponse response = new MsgResponse();
    	for(PurchaseGoods purchaseGoods : purchaseGoodsList) {
    		if(purchaseGoods.getPurchasesn()==null ||"".equals(purchaseGoods.getPurchasesn())){
    			response.setMsg("没有采购单号！");
    			return response;
        	}else if(purchaseGoods.getDeliverytype()==null ||"".equals(purchaseGoods.getDeliverytype())){
    			response.setMsg("没有提货方式！");
    			return response;
        	}else if(purchaseGoods.getGoodsid()==null ||"".equals(purchaseGoods.getGoodsid())){
    			response.setMsg("没有商品ID！");
    			return response;
        	}else if(purchaseGoods.getGoodsname()==null ||"".equals(purchaseGoods.getGoodsname())){
    			response.setMsg("没有商品名称！");
    			return response;
        	}else if(purchaseGoods.getPrice()==null ||"".equals(purchaseGoods.getPrice())){
    			response.setMsg("没有商品价格！");
    			return response;
        	}else if(purchaseGoods.getExpectprice()==null ||"".equals(purchaseGoods.getExpectprice())){
    			response.setMsg("没有期望价格！");
    			return response;
        	}else if(purchaseGoods.getTotal()==null ||"".equals(purchaseGoods.getTotal())){
    			response.setMsg("没有商品数量！");
    			return response;
        	}else if(purchaseGoods.getBarcode()==null ||"".equals(purchaseGoods.getBarcode())){
    			response.setMsg("没有产品条码！");
    			return response;
        	}
    	}
    	response.setMsg(api.addPurchaseGoods(purchaseGoodsList));
    	response.setType("1");
		return response;
    }
    @RequestMapping(value = "/goods/update")
    public MsgResponse updatePurchaseGoods(@RequestBody List<PurchaseGoods> purchaseGoodsList,HttpServletResponse res) {
		Util.responseResultSuccess(res);
		MsgResponse response = new MsgResponse();
    	response.setMsg(api.updatePurchaseGoods(purchaseGoodsList));
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
    public PageInfo<Purchase> PurchaseListOfSupplier(@RequestBody SearchPurchaseList searchPurchaseList,HttpServletResponse res) {
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
        return api.PurchaseListOfSupplier(searchPurchaseList);
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
    
    @RequestMapping(value = "/purchasers/goods")
    public PageInfo<PurchaseGoods> goodsListOfPurchasers(@RequestBody Map<String,Object> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.goodsListOfPurchasers((String)request.get("purchasesn"),(Integer)request.get("current"),(Integer)request.get("pageSize"));
    }
}



