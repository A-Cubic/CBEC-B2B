package com.cbec.b2b.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.purchase.Purchase;
import com.cbec.b2b.entity.purchase.PurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseList;

@Service
public interface IPurchaseService {
	List<Purchase> getPurchaseList(SearchPurchaseList searchPurchaseList);
	List<PurchaseGoods> PurchaseGoods(SearchPurchaseGoods searchPurchaseGoods);
	String addPurchase(Purchase purchase);
	String updatePurchase(Purchase purchase);
	String addPurchaseGoods(List<PurchaseGoods> purchaseGoodsList);
	String updatePurchaseGoods(List<PurchaseGoods> purchaseGoodsList);
	String delPurchaseGoods(List<PurchaseGoods> purchaseGoodsList);
	String splitPurchase(SearchPurchaseGoods searchPurchaseGoods);
}
