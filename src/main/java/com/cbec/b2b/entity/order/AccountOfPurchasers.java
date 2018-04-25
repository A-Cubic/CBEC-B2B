package com.cbec.b2b.entity.order;

import java.util.List;

import com.cbec.b2b.common.PageInfo;

import lombok.Data;

@Data
public class AccountOfPurchasers {
	private double total;
	private double totalyj;
	private PageInfo<Order> listOrder;
}