package com.cbec.b2b.entity.HomePage;

import java.util.List;


import lombok.Data;

@Data
public class ScreenType {
	private List<Country> country;
	private List<SendType> sendtype;
	private List<Brands> brands;
}