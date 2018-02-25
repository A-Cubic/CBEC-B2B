package com.cbec.b2b.mapper;

import java.util.List;

import com.cbec.b2b.entity.purchase.Purchase;
import com.cbec.b2b.entity.purchase.PurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseList;

public interface PurchaseMapper {
	List<Purchase> getPurchaseList(SearchPurchaseList searchPurchaseList);
	List<PurchaseGoods> getPurchaseGoods(SearchPurchaseGoods searchPurchaseGoods);
	int addPurchase(Purchase purchase);
	int updatePurchase(Purchase purchase);
}
