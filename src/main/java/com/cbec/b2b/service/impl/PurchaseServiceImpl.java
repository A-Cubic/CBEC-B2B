package com.cbec.b2b.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.purchase.Purchase;
import com.cbec.b2b.entity.purchase.PurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseGoods;
import com.cbec.b2b.entity.purchase.SearchPurchaseList;
import com.cbec.b2b.mapper.PublicMapper;
import com.cbec.b2b.mapper.PurchaseMapper;
import com.cbec.b2b.service.IPurchaseService;

@Service
public class PurchaseServiceImpl implements IPurchaseService {

	@Autowired
	PurchaseMapper mapper;
	@Autowired
	PublicMapper publicmapper;
	@Override
	public List<Purchase> getPurchaseList(SearchPurchaseList searchPurchaseList) {
		return mapper.getPurchaseList(searchPurchaseList);
	}
	@Override
	public List<PurchaseGoods> PurchaseGoods(SearchPurchaseGoods searchPurchaseGoods) {
		return mapper.getPurchaseGoods(searchPurchaseGoods);
	}
	@Override
	public String  addPurchase(Purchase purchase) {
		String id = getDate()+publicmapper.getSeq("PURCHASE");
		purchase.setPurchasesn(id);
		// TODO Auto-generated method stub
		return String.valueOf(mapper.addPurchase(purchase));
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
}
