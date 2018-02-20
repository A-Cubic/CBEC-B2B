package com.cbec.b2b.entity.HomePage;

import lombok.Data;

@Data
public class Goods {
	private int id;
	private String goodsname;
	private String price;
	private String slt;
	private int supplierId;
	private String supplierName;
	private String OfficialWebsite;
	private String brand;
	private String brandE;
	private String brandTxt;
	private String goodsNameE;
	private int catelog1;
	private int catelog2;
	private int catelog3;
	private String barcode;
	private String thumb;
	private String content;
	private String country;
	private String source;
	private String model;
	private String color;
	private String flavor;
	private Double GW;
	private Double NW;
	private String MEA;
	private String LWH;
	private String packLWH;
	private String applicable;
	private String useMethod;
	private String efficacy;
	private String USP;
	private String formula;
	private String shelfLife;
	private String storage;
	private String needAttention;
	private int stock;
	private Double foreignPrice;
	private String purchaseCurrency;
	private Double purchasePrice;
	private Double tax;
	private Double freight;
	private Double BBC;
	private Double B2B;
	private String warehouseName;
	private int warehouseId;
	private String ifXG;
	private String ifHW;
	private String ifBS;
	private String ifMY;
	private String ifB2B;
	private String ifBBC;
	

	private String[] thumbs;
	private String[] contents;
}