package com.cbec.b2b.entity.GoodsUpload;

import lombok.Data;

@Data
public class GoodsList {
	private int key;
	private int id;
	private String goodsname;
	private String price;
	private String slt;
	private String barcode;
	private String ifB2B;
	private String ifBBC;
}