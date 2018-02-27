package com.cbec.b2b.common;

import lombok.Data;

@Data
public class Pagination {
	//当前页
    private int current;
    //每页的数量
    private int pageSize;
    //总条数
    private long total;
}
