package com.cbec.b2b.entity.HomePage;

import java.util.List;

import com.cbec.b2b.entity.Catelog.CateType;

import lombok.Data;

@Data
public class GoodsType {
	private List<Goods> goods;
}