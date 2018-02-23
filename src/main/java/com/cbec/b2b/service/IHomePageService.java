package com.cbec.b2b.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.HomePage.Banner;
import com.cbec.b2b.entity.HomePage.Brands;
import com.cbec.b2b.entity.HomePage.Country;
import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.SendType;

@Service
public interface IHomePageService {
	List<Banner> getBanner();
	List<Brands> getBrands();
	List<Goods> getGoods();
	List<Country> getCountry();
	List<SendType> getSendType();
	List<Goods> getGoodsByGoodsId(String goodsId);
}
