package com.cbec.b2b.entity.order;


import lombok.Data;

@Data
public class SearchOrderList {
	private String[] times;//建立时间
	private String timeBegin;//建立时间
	private String timeEnd;//建立时间
	int current;//多少页
	int pageSize;//页面显示多少个商品
	private String status; //询价状态
}