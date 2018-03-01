package com.cbec.b2b.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.api.PurchaseApi;
import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.entity.purchase.Purchase;
import com.cbec.b2b.entity.purchase.PurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseList;

@RestController
@RequestMapping(value = "/llback/purchase")
public class PurchaseController {
    @Autowired
    PurchaseApi api;

    @RequestMapping(value = "/list")
    public PageInfo<Purchase> PurchaseList(@RequestBody SearchPurchaseList searchPurchaseList,HttpServletResponse res) {
        return api.PurchaseList(searchPurchaseList);
    }
    @RequestMapping(value = "/goods")
    public List<PurchaseGoods> PurchaseGoods(@RequestBody SearchPurchaseGoods searchPurchaseGoods,HttpServletResponse res) {
        return api.PurchaseGoods(searchPurchaseGoods);
    }
    @RequestMapping(value = "/add")
    public String addPurchase(@RequestBody Purchase purchase,HttpServletResponse res) {
        return api.addPurchase(purchase);
    }

    @RequestMapping(value = "/update")
    public String updatePurchase(@RequestBody Purchase purchase,HttpServletResponse res) {
    	return api.updatePurchase(purchase);
    }
    @RequestMapping(value = "/goods/add")
    public String addPurchaseGoods(@RequestBody List<PurchaseGoods> purchaseGoodsList,HttpServletResponse res) {
    	return api.addPurchaseGoods(purchaseGoodsList);
    }
    @RequestMapping(value = "/goods/update")
    public String updatePurchaseGoods(@RequestBody List<PurchaseGoods> purchaseGoodsList,HttpServletResponse res) {
    	return api.updatePurchaseGoods(purchaseGoodsList);
    }
    @RequestMapping(value = "/goods/del")
    public String delPurchaseGoods(@RequestBody List<PurchaseGoods> purchaseGoodsList,HttpServletResponse res) {
    	return api.delPurchaseGoods(purchaseGoodsList);
    }
}



