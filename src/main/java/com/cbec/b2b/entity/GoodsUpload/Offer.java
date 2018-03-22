package com.cbec.b2b.entity.GoodsUpload;

import lombok.Data;

@Data
public class Offer {
	private int id; // 序号
	private String usercode; // 发布账号
	private String company;
	private String ifB2B;
	private int goodsid;
	private String barcode;
	private String goodsName;
	private String slt;
	private double offer;
	private String remark;
	private String flag;
}