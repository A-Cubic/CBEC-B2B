package com.cbec.b2b.entity.order;

import lombok.Data;

@Data
public class AccountGoods {
	private int id;
	private String merchantOrderId;
	private String barCode;
	private double skuUnitPrice;
	private int quantity;
}