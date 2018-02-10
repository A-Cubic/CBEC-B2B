package com.cbec.b2b.entity.page;

import lombok.Data;

@Data
public class Pagination {
	private int total;
	private int pageSize;
	private int current;
}
