package com.cbec.b2b.entity.GoodsUpload;

import lombok.Data;

@Data
public class SearchGoods {
	String userCode; //供货商账号
	String ifXG; //是否香港自提
	String ifHW; //是否海外自提
	String ifBS; //是否保税备货
	String ifMY; //是否一般贸易
	String country;//原产国
	String catelog1;//商品分类1
	String catelog2;//商品分类2
	String catelog3;//商品分类3
	String brands;//品牌
	String search;//搜索内容
	String sort;//排序方式
	int current;//多少页
	int pageSize;//页面显示多少个商品
}


