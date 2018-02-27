package com.cbec.b2b.entity.purchase;


import lombok.Data;

@Data
public class SearchPurchaseList {
	private String userCode;//采购商账号
	private String purchasesn;//采购单号
	private String timeBegin;//建立时间
	private String timeEnd;//建立时间
	private String stage;//采购单阶段
	int current;//多少页
	int pageSize;//页面显示多少个商品
}