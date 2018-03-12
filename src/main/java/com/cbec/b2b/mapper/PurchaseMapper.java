package com.cbec.b2b.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbec.b2b.entity.purchase.ChatRequest;
import com.cbec.b2b.entity.purchase.ChatResponse;
import com.cbec.b2b.entity.purchase.Inquiry;
import com.cbec.b2b.entity.purchase.Purchase;
import com.cbec.b2b.entity.purchase.PurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseList;

public interface PurchaseMapper {
	int insertChat(ChatRequest bean);
	List<ChatResponse> getChatList(ChatRequest bean);
	
	List<Purchase> getPurchaseList(SearchPurchaseList searchPurchaseList);
	List<PurchaseGoods> getPurchaseGoods(SearchPurchaseGoods searchPurchaseGoods);
	List<PurchaseGoods> getPurchaseGoodsToInquiry(String purchasesn);
	int addPurchase(Purchase purchase);
	int updatePurchase(Purchase purchase);
	int addPurchaseGoods(List<PurchaseGoods> purchaseGoodsList);
	int updatePurchaseGoods(PurchaseGoods purchaseGoods);
	int delPurchaseGoods(List<PurchaseGoods> purchaseGoodsList);
	int delPurchaseGoodsByPurchasesn(String purchasesn);
	int addInquiry(List<Inquiry> inquiryList);

	/****************************************** 客服部分 ***************************************/
	List<PurchaseGoods> listGoodsOfOperate(@Param("purchasesn") String purchasesn);
	Purchase getPurchaseBySnOfOperate(@Param("purchasesn") String purchasesn);
	int updateFeeOfOperate(@Param("purchasesn") String purchasesn,@Param("fee") String fee);
	int updatePriceOfOperate(@Param("id") String id,@Param("price") String price);
	List<Inquiry> supplyListOfOperate(@Param("purchasesn") String purchasesn,@Param("goodsid") String goodsid);
	int updateSupplyFlagOfOperate(@Param("id") String id,@Param("flag") String flag);

	/****************************************** 供应商部分 ***************************************/
	List<PurchaseGoods> listGoodsOfSupplier(@Param("purchasesn") String purchasesn);
	Purchase getPurchaseBySnOfSupplier(@Param("purchasesn") String purchasesn);
	List<Purchase> getPurchaseListOfSupplier(SearchPurchaseList searchPurchaseList);
	List<Inquiry> getInquiryOfSupplier(@Param("usercode") String usercode,@Param("purchasesn") String purchasesn);
	int updatePriceOfSupplier(Inquiry inquiry);
	/****************************************** 采购商部分 ***************************************/
	List<PurchaseGoods> listGoodsOfPurchasers(@Param("purchasesn") String purchasesn);
	Purchase getPurchaseBySnOfPurchasers(@Param("purchasesn") String purchasesn);
	int updatePriceOfPurchasers(@Param("id") String id,@Param("expectprice") String expectprice,@Param("total") String total);
	
}
