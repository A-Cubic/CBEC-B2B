package com.cbec.b2b.entity.Catelog;

import java.util.List;

import lombok.Data;

@Data
public class CateTWO {
	private int id;
	private String value;
	private List<CateThree> childCate;
}
