package com.cbec.b2b.entity.order;

import java.util.List;

import lombok.Data;

@Data
public class Account {
	private double total;
	private List<AccountGoods> listAG;
}