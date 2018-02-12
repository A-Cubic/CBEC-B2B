package com.cbec.b2b.entity;

import java.util.List;

import lombok.Data;

@Data
public class CateOne {
	private int cateOneId;
	private String cateOneName;
	private List<CateTWO> cateTWO;
	private List<Brands> brands;
}
