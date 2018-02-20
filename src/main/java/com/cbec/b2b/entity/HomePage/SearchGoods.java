package com.cbec.b2b.entity.HomePage;

import lombok.Data;

@Data
public class SearchGoods {
	String sendType; //提货方式
	String country;//原产国
	String catelog1;//商品分类1
	String catelog2;//商品分类2
	String catelog3;//商品分类3
	String brands;//品牌
	String search;//搜索内容
	String sort;//排序方式
	int pageNumber;//多少页
	int pageSize;//页面显示多少个商品
}