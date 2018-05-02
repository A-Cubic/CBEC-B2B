package com.cbec.b2b.entity.GoodsUpload;

import lombok.Data;

@Data
public class SearchSellNum {
	private String times;//建立时间
	private String timeBegin;
	private String timeEnd;
	private String whid;
	private String search;
	int current;//多少页
	int pageSize;//页面显示多少个商品
}