package com.cbec.b2b.entity.purchase;


import java.util.List;

import lombok.Data;

@Data
public class PurchaseAndGood {
	private String purchasesn;   //采购单号
	List<PurchaseGoods> list;
}