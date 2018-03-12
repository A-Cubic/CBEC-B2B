package com.cbec.b2b.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.MsgResponse;
import com.cbec.b2b.entity.GoodsUpload.Offer;
import com.cbec.b2b.entity.purchase.ChatRequest;
import com.cbec.b2b.entity.purchase.ChatResponse;
import com.cbec.b2b.entity.purchase.Inquiry;
import com.cbec.b2b.entity.purchase.Purchase;
import com.cbec.b2b.entity.purchase.PurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseList;
import com.cbec.b2b.mapper.GoodsUploadMapper;
import com.cbec.b2b.mapper.PublicMapper;
import com.cbec.b2b.mapper.PurchaseMapper;
import com.cbec.b2b.mapper.UserMapper;
import com.cbec.b2b.service.IPurchaseService;

@Service
public class PurchaseServiceImpl implements IPurchaseService {

	@Autowired
	PurchaseMapper mapper;
	@Autowired
	UserMapper usermapper;
	@Autowired
	PublicMapper publicmapper;
	@Autowired 
	GoodsUploadMapper goodsUploadMapper;
	
	@Override
	public List<ChatResponse> listChat(ChatRequest request) {
		return mapper.getChatList(request);
	}

	@Override
	public MsgResponse sendChat(ChatRequest request) {
		int num = mapper.insertChat(request);
		MsgResponse response = new MsgResponse();
		String result = "";
		if(num > 0) {
			response.setType("1");
			result="消息发送成功";
		}else {
			result="消息发送失败";
		}
		response.setMsg(result);
		return response;
	}

	@Override
	public List<Purchase> getPurchaseList(SearchPurchaseList searchPurchaseList) {
		return mapper.getPurchaseList(searchPurchaseList);
	}
	@Override
	public List<PurchaseGoods> PurchaseGoods(SearchPurchaseGoods searchPurchaseGoods) {
		return mapper.getPurchaseGoods(searchPurchaseGoods);
	}
	
	@Override
	public Purchase  addPurchase(Purchase purchase) {
		String id = getDate()+publicmapper.getSeq("PURCHASE");
		purchase.setPurchasesn(id);
		if(mapper.addPurchase(purchase)>0) {
			return purchase;
		}else {
			return null;
		}
	}
	
	private String getDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}
	
	@Override
	public String updatePurchase(Purchase purchase) {
		// TODO Auto-generated method stub
		return String.valueOf(mapper.updatePurchase(purchase));
	}
	
	@Override
	public String addPurchaseGoods(List<PurchaseGoods> purchaseGoodsList) {
		mapper.delPurchaseGoodsByPurchasesn(purchaseGoodsList.get(0).getPurchasesn());
		return String.valueOf(mapper.addPurchaseGoods(purchaseGoodsList));
	}
	
	@Override
	public String updatePurchaseGoods(List<PurchaseGoods> purchaseGoodsList) {
		int c = 0;
		for(PurchaseGoods purchaseGoods : purchaseGoodsList) {
			c+= mapper.updatePurchaseGoods(purchaseGoods);
		}
		return String.valueOf(c);
	}
	
	@Override
	public String delPurchaseGoods(List<PurchaseGoods> purchaseGoodsList) {
		return String.valueOf(mapper.delPurchaseGoods(purchaseGoodsList));
	}
	
	@Override
	public String splitPurchase(SearchPurchaseGoods searchPurchaseGoods) {
		List<PurchaseGoods> pGoodsList = mapper.getPurchaseGoodsToInquiry(searchPurchaseGoods.getPurchasesn());
		List<Inquiry> inquiryList = new ArrayList<Inquiry>();
		if(pGoodsList.size()==0) {
			return "未查到采购单对应的采购商品信息！";
		}else {
			for(PurchaseGoods pGoods:pGoodsList) {
				List<Offer> offerList = goodsUploadMapper.getOfferInfoByGoodsId(pGoods.getGoodsid());
				for(Offer offer : offerList) {
					Inquiry inquiry = new Inquiry();
					inquiry.setPurchasesn(pGoods.getPurchasesn());
					inquiry.setUsercode(offer.getUsercode());
					inquiry.setGoodsid(offer.getGoodsid());
					inquiry.setGoodsname(offer.getGoodsName());
					inquiry.setBarcode(offer.getBarcode());
					inquiry.setPrice(offer.getOffer());
					inquiry.setFlag("1");
					inquiry.setRemark("");
					
					inquiryList.add(inquiry);
				}
			}
		}
		if(inquiryList.size()>0) {
			if(mapper.addInquiry(inquiryList)>0) {
				Purchase purchase = new Purchase();
				purchase.setPurchasesn(searchPurchaseGoods.getPurchasesn());
				purchase.setStage("1");
				purchase.setStatus("2");
				
				updatePurchase(purchase);
				return "提交完成";
			}else {
				return "询价单创建失败！";
			}
			
		}else {
			return "无对应的报价信息！";
		}
	}


	@Override
	public SearchPurchaseList getSearchPurchase(String userid, SearchPurchaseList searchPurchaseList) {
		Map<String,Object> userMap = usermapper.getUserType(userid);
		
		String type  = (String)userMap.get("usertype");
//		String type="1";
		if("0".equals(type)||"5".equals(type)) {
			searchPurchaseList.setUserCode("");
		}else {
			searchPurchaseList.setUserCode(userid);
		}
		return searchPurchaseList;
	}

	/****************************************** 客服部分 ***************************************/
	@Override
	public List<PurchaseGoods> goodsListOfOperate(String purchasesn) {
		return mapper.listGoodsOfOperate(purchasesn);
	}
	
	@Override
	public Purchase getPurchaseOfOperate(String purchasesn) {
		return mapper.getPurchaseBySnOfOperate(purchasesn);
	}

	@Override
	public MsgResponse updateFeeOfOperate(String purchasesn,String fee) {
		int num = mapper.updateFeeOfOperate(purchasesn,fee);
		MsgResponse response = new MsgResponse();
		String result = "";
		if(num > 0) {
			response.setType("1");
			result="保存运费成功";
		}else {
			result="保存运费失败";
		}
		response.setMsg(result);
		return response;
	}
	
	@Override
	public MsgResponse updatePriceOfOperate(String id, String price) {
		int num = mapper.updatePriceOfOperate(id,price);
		MsgResponse response = new MsgResponse();
		String result = "";
		if(num > 0) {
			response.setType("1");
			result="改价成功";
		}else {
			result="改价失败";
		}
		response.setMsg(result);
		return response;
	}
	
	@Override
	public List<Inquiry> supplyListOfOperate(String purchasesn,String goodsid) {
		return mapper.supplyListOfOperate(purchasesn,goodsid);
	}
	
	@Override
	public MsgResponse updateSupplyFlagOfOperate(String id, String flag) {
		int num = mapper.updateSupplyFlagOfOperate(id,flag);
		MsgResponse response = new MsgResponse();
		String result = "";
		if(num > 0) {
			if("1".equals(flag)) {
				result="解除成功";
			}else {
				result="敲定成功";
			}
			response.setType("1");
		}else {
			if("1".equals(flag)) {
				result="解除失败";
			}else {
				result="敲定失败";
			}
		}
		response.setMsg(result);
		return response;
	}
	
	/****************************************** 供应商部分 ***************************************/
	@Override
	public List<PurchaseGoods> goodsListOfSupplier(String purchasesn) {
		return mapper.listGoodsOfSupplier(purchasesn);
	}
	
	@Override
	public Purchase getPurchaseOfSupplier(String purchasesn) {
		return mapper.getPurchaseBySnOfSupplier(purchasesn);
	}

	@Override
	public List<Purchase> getPurchaseListOfSupplier(SearchPurchaseList searchPurchaseList) {
			return mapper.getPurchaseListOfSupplier(searchPurchaseList);
	}

	@Override
	public List<Inquiry> getInquiryOfSupplier(String userCode, String purchasesn) {
		// TODO Auto-generated method stub
		return mapper.getInquiryOfSupplier(userCode,purchasesn);
	}
	
	/****************************************** 采购商部分 ***************************************/
	@Override
	public List<PurchaseGoods> goodsListOfPurchasers(String purchasesn) {
		return mapper.listGoodsOfPurchasers(purchasesn);
	}
	
	@Override
	public Purchase getPurchaseOfPurchasers(String purchasesn) {
		return mapper.getPurchaseBySnOfPurchasers(purchasesn);
	}

	@Override
	public MsgResponse updatePriceOfPurchasers(String id, String price, String total) {
		int num = mapper.updatePriceOfPurchasers(id,price,total);
		MsgResponse response = new MsgResponse();
		String result = "";
		if(num > 0) {
			response.setType("1");
			result="保存成功";
		}else {
			result="保存失败";
		}
		response.setMsg(result);
		return response;
	}

	
}
