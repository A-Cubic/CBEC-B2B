package com.cbec.b2b.entity;

import java.util.List;

import lombok.Data;

@Data
public class CateTWO {
	private int cateTWOId;
	private String cateTWOName;
	private List<CateThree> cateThree;
}
