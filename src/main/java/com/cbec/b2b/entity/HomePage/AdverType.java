package com.cbec.b2b.entity.HomePage;

import java.util.List;


import lombok.Data;

@Data
public class AdverType {
	private List<Banner> banner;
	private List<Brands> brands;
	private List<GoodsList> goods;
}