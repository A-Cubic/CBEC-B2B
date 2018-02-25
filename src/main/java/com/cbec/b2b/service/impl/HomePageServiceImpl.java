package com.cbec.b2b.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.HomePage.Banner;
import com.cbec.b2b.entity.HomePage.Brands;
import com.cbec.b2b.entity.HomePage.Country;
import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.SearchGoods;
import com.cbec.b2b.entity.HomePage.SendType;
import com.cbec.b2b.mapper.GoodsMapper;
import com.cbec.b2b.mapper.HomePageMapper;
import com.cbec.b2b.mapper.UserMapper;
import com.cbec.b2b.service.IHomePageService;

@Service
public class HomePageServiceImpl implements IHomePageService {

	@Autowired
	HomePageMapper mapper;
	@Autowired
	UserMapper usermapper;
	@Autowired
	GoodsMapper goodsmapper;

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

	@Override
	public List<Goods> getGoodsList(SearchGoods searchGoods) {
		if(searchGoods.getUserCode()!=null&&!"".equals(searchGoods.getUserCode())) {
			Map<String,Object> userMap = usermapper.getUserType(searchGoods.getUserCode());
			if(userMap==null || userMap.size()<1) {
				searchGoods.setUserCode("");
			}else {
				String id  = (String)userMap.get("id");
				searchGoods.setUserCode(id);
			}
		}
		return goodsmapper.getGoodsList(searchGoods);
	}
	
}
