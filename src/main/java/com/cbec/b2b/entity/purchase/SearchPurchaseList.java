package com.cbec.b2b.entity.purchase;


import lombok.Data;

@Data
public class SearchPurchaseList {
	private String userCode;//采购商账号
	private String purchasesn;//采购单号
	private String createtime;//建立时间
	private String stage;//采购单阶段
}