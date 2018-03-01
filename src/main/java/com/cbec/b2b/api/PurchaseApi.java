package com.cbec.b2b.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.SearchGoods;
import com.cbec.b2b.entity.purchase.Purchase;
import com.cbec.b2b.entity.purchase.PurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseList;
import com.cbec.b2b.service.IPurchaseService;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping(value = "/api")
public class PurchaseApi {
    @Autowired
    IPurchaseService service;

    @RequestMapping(value = "/purchaselist")
    public PageInfo<Purchase> PurchaseList(@RequestBody SearchPurchaseList searchPurchaseList) {
    	PageHelper.startPage(searchPurchaseList.getCurrent(),searchPurchaseList.getPageSize());
    	List<Purchase> LPurchase = service.getPurchaseList(searchPurchaseList);
    	PageInfo<Purchase> pageData = new PageInfo<Purchase>(LPurchase);
        return pageData;
    }
    @RequestMapping(value = "/purchasegoods")
    public List<PurchaseGoods> PurchaseGoods(@RequestBody SearchPurchaseGoods searchPurchaseGoods) {
    	return service.PurchaseGoods(searchPurchaseGoods);
    }

    @RequestMapping(value = "/addpurchase")
    public String addPurchase(@RequestBody Purchase purchases) {
    	return service.addPurchase(purchases);
    }
    @RequestMapping(value = "/updatepurchase")
    public String updatePurchase(@RequestBody Purchase purchases) {
    	return service.updatePurchase(purchases);
    }
    @RequestMapping(value = "/goods/add")
    public String addPurchaseGoods(@RequestBody List<PurchaseGoods> purchaseGoodsList) {
    	String error="";
    	for(PurchaseGoods purchaseGoods : purchaseGoodsList) {
    		if(purchaseGoods.getPurchasesn()==null ||"".equals(purchaseGoods.getPurchasesn())){
    			error= "ERROR:没有采购单号！";
        	}else if(purchaseGoods.getDeliverytype()==null ||"".equals(purchaseGoods.getDeliverytype())){
        		error= "ERROR:没有提货方式！";
        	}else if(purchaseGoods.getGoodsid()==null ||"".equals(purchaseGoods.getGoodsid())){
        		error= "ERROR:没有商品ID！";
        	}else if(purchaseGoods.getGoodsname()==null ||"".equals(purchaseGoods.getGoodsname())){
        		error= "ERROR:没有商品名称！";
        	}else if(purchaseGoods.getPrice()==null ||"".equals(purchaseGoods.getPrice())){
        		error= "ERROR:没有商品价格！";
        	}else if(purchaseGoods.getExpectprice()==null ||"".equals(purchaseGoods.getExpectprice())){
        		error= "ERROR:没有期望价格！";
        	}else if(purchaseGoods.getTotal()==null ||"".equals(purchaseGoods.getTotal())){
        		error= "ERROR:没有商品数量！";
        	}else if(purchaseGoods.getBarcode()==null ||"".equals(purchaseGoods.getBarcode())){
        		error= "ERROR:没有产品条码！";
        	}
    	}
    	if("".equals(error)) {
    		return service.addPurchaseGoods(purchaseGoodsList); 
    	}else {
    		return error;
    	}
    }
    @RequestMapping(value = "/goods/update")
    public String updatePurchaseGoods(@RequestBody List<PurchaseGoods> purchaseGoodsList) {
    	return service.updatePurchaseGoods(purchaseGoodsList); 
    }
    @RequestMapping(value = "/goods/del")
    public String delPurchaseGoods(@RequestBody List<PurchaseGoods> purchaseGoodsList) {
    	return service.delPurchaseGoods(purchaseGoodsList); 
    }
}



