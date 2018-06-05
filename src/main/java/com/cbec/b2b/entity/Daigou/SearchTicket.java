package com.cbec.b2b.entity.Daigou;

import lombok.Data;

@Data
public class SearchTicket {
	String search;
	String ticketCode;
	int current;//多少页
	int pageSize;//页面显示多少个商品
}