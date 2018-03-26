package com.cbec.b2b.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.MsgResponse;
import com.cbec.b2b.entity.purchase.ChatRequest;
import com.cbec.b2b.entity.purchase.ChatResponse;
import com.cbec.b2b.entity.purchase.Inquiry;
import com.cbec.b2b.entity.purchase.Purchase;
import com.cbec.b2b.entity.purchase.PurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseList;

@Service
public interface IPurchaseService {
	List<ChatResponse> listChat(ChatRequest request);
	MsgResponse sendChat(ChatRequest request);
	
	List<Purchase> getPurchaseList(SearchPurchaseList searchPurchaseList);
	List<PurchaseGoods> PurchaseGoods(SearchPurchaseGoods searchPurchaseGoods);
	Purchase addPurchase(Purchase purchase);
	String updatePurchase(Purchase purchase);
	MsgResponse updatePurchaseStage(String purchasesn,String stage);
	List<PurchaseGoods> addPurchaseGoodsNew(String purchasesn,List<PurchaseGoods> purchaseGoodsList);
	String addPurchaseGoods(String purchasesn,List<PurchaseGoods> purchaseGoodsList);
	String updatePurchaseGoods(PurchaseGoods purchaseGoods);
	String delPurchaseGoods(List<PurchaseGoods> purchaseGoodsList);
	String splitPurchase(SearchPurchaseGoods searchPurchaseGoods);
	SearchPurchaseList getSearchPurchase(String userid,SearchPurchaseList searchPurchaseList);

	/****************************************** 客服部分 ***************************************/
	List<PurchaseGoods> goodsListOfOperate(String purchasesn);
	Purchase getPurchaseOfOperate(String purchasesn);
	MsgResponse updateFeeOfOperate(String purchasesn,String fee);
	MsgResponse updatePriceOfOperate(String id,String price,String total);
	List<Inquiry> supplyListOfOperate(String purchasesn,String goodsid);
	MsgResponse updateSupplyFlagOfOperate(String id,String flag);
	
	/****************************************** 供应商部分 ***************************************/
	List<PurchaseGoods> goodsListOfSupplier(String purchasesn);
	List<Purchase> getPurchaseListOfSupplier(SearchPurchaseList searchPurchaseList);
	Purchase getPurchaseOfSupplier(String purchasesn);
	List<Inquiry> getInquiryOfSupplier(String userCode,String purchasesn);
	MsgResponse updatePriceOfSupplier(List<Inquiry> request);
	/****************************************** 采购商部分 ***************************************/
	List<PurchaseGoods> goodsListOfPurchasers(String purchasesn);
	Purchase getPurchaseOfPurchasers(String purchasesn);
	MsgResponse updatePriceOfPurchasers(String id,String price,String total);
	
}
