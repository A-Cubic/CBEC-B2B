package com.cbec.b2b.entity.GoodsUpload;


import lombok.Data;

@Data
public class SearchOffer {
	private String userCode;
	private String userName;
	private String barcode;
	private String goodsName;
	int current;//多少页
	int pageSize;//页面显示多少个商品

}