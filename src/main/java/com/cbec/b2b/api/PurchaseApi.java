package com.cbec.b2b.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.entity.MsgResponse;
import com.cbec.b2b.entity.purchase.Inquiry;
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
		return service.addPurchaseGoods(purchaseGoodsList); 
    }
    @RequestMapping(value = "/goods/update")
    public String updatePurchaseGoods(@RequestBody List<PurchaseGoods> purchaseGoodsList) {
    	return service.updatePurchaseGoods(purchaseGoodsList); 
    }
    @RequestMapping(value = "/goods/del")
    public String delPurchaseGoods(@RequestBody List<PurchaseGoods> purchaseGoodsList) {
    	return service.delPurchaseGoods(purchaseGoodsList); 
    }
    @RequestMapping(value = "/split")
    public String splitPurchase(@RequestBody SearchPurchaseGoods searchPurchaseGoods) {
    	return service.splitPurchase(searchPurchaseGoods);
    }
    
    /****************************************** 客服部分 ***************************************/
    //获取采购单信息
    @RequestMapping(value = "/operate/list")
    public PageInfo<Purchase> listOfOperate(@RequestParam SearchPurchaseList search) {
    	PageHelper.startPage(search.getCurrent(),search.getPageSize());
    	List<Purchase> list = service.getPurchaseList(search);
    	PageInfo<Purchase> pageData = new PageInfo<Purchase>(list);
        return pageData;
    }
    //获取采购单信息 从采购单号
    @RequestMapping(value = "/operate/info/details")
    public Purchase getPurchaseOfOperate(@RequestParam String purchasesn) {
        return service.getPurchaseOfOperate(purchasesn);
    }
    
    //获取采购单商品信息 从采购单号
    @RequestMapping(value = "/operate/goods")
    public PageInfo<PurchaseGoods> goodsListOfOperate(@RequestParam String purchasesn,@RequestParam Integer current,@RequestParam Integer pageSize) {
    	PageHelper.startPage(current,pageSize);
    	List<PurchaseGoods> list = service.goodsListOfOperate(purchasesn);
    	PageInfo<PurchaseGoods> pageData = new PageInfo<PurchaseGoods>(list);
        return pageData;
    }
    
    @RequestMapping(value = "/operate/update/fee")
    public MsgResponse updateFeeOfOperate(@RequestParam String purchasesn,@RequestParam String fee) {
        return service.updateFeeOfOperate(purchasesn,fee);
    }
    
    @RequestMapping(value = "/operate/update/price")
    public MsgResponse updatePriceOfOperate(@RequestParam String id,@RequestParam String price) {
        return service.updatePriceOfOperate(id,price);
    }
    
    @RequestMapping(value = "/operate/supply/list")
    public List<Inquiry> supplyListOfOperate(@RequestParam String purchasesn,@RequestParam String goodsid) {
    	return service.supplyListOfOperate(purchasesn,goodsid);
    }
    
    @RequestMapping(value = "/operate/supply/flag")
    public MsgResponse updateSupplyFlagOfOperate(@RequestParam String id,@RequestParam String flag) {
        return service.updateSupplyFlagOfOperate(id,flag);
    }


    /****************************************** 供应商部分 ***************************************/
    //获取采购单信息
    @RequestMapping(value = "/supplier/list")
    public PageInfo<Purchase> PurchaseListOfSupplier(@RequestBody SearchPurchaseList searchPurchaseList) {
    	PageHelper.startPage(searchPurchaseList.getCurrent(),searchPurchaseList.getPageSize());
    	List<Purchase> LPurchase = service.getPurchaseList(searchPurchaseList);
    	PageInfo<Purchase> pageData = new PageInfo<Purchase>(LPurchase);
        return pageData;
    }
    
    
    /****************************************** 采购商部分 ***************************************/
    //获取采购单信息
    @RequestMapping(value = "/purchasers/list")
    public PageInfo<Purchase> PurchaseListOfPurchasers(@RequestBody SearchPurchaseList searchPurchaseList) {
    	PageHelper.startPage(searchPurchaseList.getCurrent(),searchPurchaseList.getPageSize());
    	List<Purchase> LPurchase = service.getPurchaseList(searchPurchaseList);
    	PageInfo<Purchase> pageData = new PageInfo<Purchase>(LPurchase);
        return pageData;
    }
    
    //获取采购单商品信息 从采购单号
    @RequestMapping(value = "/operate/goods")
    public PageInfo<PurchaseGoods> goodsListOfPurchasers(@RequestParam String purchasesn,@RequestParam Integer current,@RequestParam Integer pageSize) {
    	PageHelper.startPage(current,pageSize);
    	List<PurchaseGoods> list = service.goodsListOfOperate(purchasesn);
    	PageInfo<PurchaseGoods> pageData = new PageInfo<PurchaseGoods>(list);
        return pageData;
    }
    
}



