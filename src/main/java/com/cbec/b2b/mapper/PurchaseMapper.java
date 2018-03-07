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
	int updateFeeOfOperate(@Param("purchasesn") String purchasesn,@Param("fee") String fee);
	int updatePriceOfOperate(@Param("id") String id,@Param("price") String price);

	/****************************************** 供应商部分 ***************************************/
	List<PurchaseGoods> listGoodsOfSupplier(@Param("purchasesn") String purchasesn);
	Purchase getPurchaseBySnOfSupplier(@Param("purchasesn") String purchasesn);
	/****************************************** 采购商部分 ***************************************/
	List<PurchaseGoods> listGoodsOfPurchasers(@Param("purchasesn") String purchasesn);
	Purchase getPurchaseBySnOfPurchasers(@Param("purchasesn") String purchasesn);
}
