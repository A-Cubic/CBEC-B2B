package com.cbec.b2b.entity.HomePage;

import lombok.Data;

@Data
public class GoodsList {
	private int key;
	private int id;
	private String goodsname;
	private String price;
	private String beginPrice;
	private String endPrice;
	private String slt;
	private String country;
	private String barcode;
	private String ifB2B;
	private String ifBBC;
}