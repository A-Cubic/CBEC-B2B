package com.cbec.b2b.entity.purchase;


import lombok.Data;

@Data
public class SearchPurchaseGoods {
	private String userCode;//采购商账号
	private String purchasesn;//采购单号
}