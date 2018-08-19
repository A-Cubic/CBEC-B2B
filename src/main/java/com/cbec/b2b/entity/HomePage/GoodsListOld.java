package com.cbec.b2b.entity.HomePage;

import java.util.List;

import lombok.Data;

@Data
public class GoodsListOld {
	private int pageNum;
	private int pageSize;
	private int hasNextPage;
	private int hasPreviousPage;
	private int size;
	private String orderBy;
	private int startRow;
	private int endRow;
	private int total;
	private int pages;
	private List<GoodsList> list;
}