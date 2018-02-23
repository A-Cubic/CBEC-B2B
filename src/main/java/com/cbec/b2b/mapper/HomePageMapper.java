package com.cbec.b2b.mapper;

import java.util.List;

import com.cbec.b2b.entity.HomePage.Banner;
import com.cbec.b2b.entity.HomePage.Brands;
import com.cbec.b2b.entity.HomePage.Country;
import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.SendType;

public interface HomePageMapper {
	List<Banner> getBanner();
	List<Brands> getBrands();
	List<Goods> getGoods();
	List<Country> getCountry();
	List<SendType> getSendType();
	List<Goods> getGoodsByGoodsId(String goodsId);
}
