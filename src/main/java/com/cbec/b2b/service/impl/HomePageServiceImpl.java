package com.cbec.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.HomePage.Banner;
import com.cbec.b2b.entity.HomePage.Brands;
import com.cbec.b2b.entity.HomePage.Country;
import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.SendType;
import com.cbec.b2b.mapper.HomePageMapper;
import com.cbec.b2b.service.IHomePageService;

@Service
public class HomePageServiceImpl implements IHomePageService {

	@Autowired
	HomePageMapper mapper;

	@Override
	public List<Banner> getBanner() {
		return mapper.getBanner();
	}

	@Override
	public List<Brands> getBrands() {
		return mapper.getBrands();
	}

	@Override
	public List<Goods> getGoods() {
		return mapper.getGoods();
	}

	@Override
	public List<Country> getCountry() {
		return mapper.getCountry();
	}

	@Override
	public List<SendType> getSendType() {
		return mapper.getSendType();
	}
	@Override
	public List<Goods> getGoodsByGoodsId(String goodsId) {
		return mapper.getGoodsByGoodsId(goodsId);
	}
	
}
