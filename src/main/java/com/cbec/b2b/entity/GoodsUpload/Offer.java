package com.cbec.b2b.entity.GoodsUpload;


import lombok.Data;

@Data
public class Offer {
	private int id;
	private String usercode;
	private int goodsid;
	private String barcode;
	private String goodsName;
	private String slt;
	private double offer;
	private String remark;
	private String flag;

}