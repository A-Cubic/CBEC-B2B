package com.cbec.b2b.entity.order;

import lombok.Data;

@Data
public class OrderGoods {
	private int id;
	private String merchantOrderId;
	private String barCode;
	private String skuUnitPrice;
	private int quantity;
	private String skuBillName;
	private String batchNo;
	private String goodsName;
	private String api;
	private String fqSkuID;
	private String sendType;
	private String status;
	private String waybill;
	private String yj;
}