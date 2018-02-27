package com.cbec.b2b.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}



