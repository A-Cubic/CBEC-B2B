package com.cbec.b2b.entity.GoodsUpload;

import lombok.Data;

@Data
public class UpdateGoodsNum {
	private String suppliercode;
	private String warehouseid;
	private String barcode;
	private int goodsnum;
}