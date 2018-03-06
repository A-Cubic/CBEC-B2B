package com.cbec.b2b.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbec.b2b.entity.purchase.Inquiry;
import com.cbec.b2b.entity.purchase.Purchase;
import com.cbec.b2b.entity.purchase.PurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseList;

public interface PurchaseMapper {
	List<Purchase> getPurchaseList(SearchPurchaseList searchPurchaseList);
	List<PurchaseGoods> getPurchaseGoods(SearchPurchaseGoods searchPurchaseGoods);
	List<PurchaseGoods> getPurchaseGoodsToInquiry(String purchasesn);
	int addPurchase(Purchase purchase);
	int updatePurchase(Purchase purchase);
	int addPurchaseGoods(List<PurchaseGoods> purchaseGoodsList);
	int updatePurchaseGoods(PurchaseGoods purchaseGoods);
	int delPurchaseGoods(List<PurchaseGoods> purchaseGoodsList);
	int addInquiry(List<Inquiry> inquiryList);
	
	/****************************************** 客服部分 ***************************************/
	List<PurchaseGoods> listGoodsOfOperate(@Param("purchasesn") String purchasesn);
	Purchase getPurchaseBySnOfOperate(@Param("purchasesn") String purchasesn);
}
