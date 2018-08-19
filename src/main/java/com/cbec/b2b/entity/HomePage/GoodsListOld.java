package com.cbec.b2b.entity.HomePage;

import java.util.List;

import lombok.Data;

@Data
public class GoodsListOld {
	private int pageNum;
	private int pageNumber;
	private int pageSize;
	private Boolean hasNextPage;
	private Boolean hasPreviousPage;
	private int size;
	private String orderBy;
	private int startRow;
	private int endRow;
	private int total;
//	private int pages;
	private int[] pages;
	private List<GoodsList> list;
}