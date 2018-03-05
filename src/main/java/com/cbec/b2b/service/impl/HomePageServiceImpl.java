package com.cbec.b2b.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.Catelog.Brand;
import com.cbec.b2b.entity.Catelog.CateOne;
import com.cbec.b2b.entity.Catelog.CateTWO;
import com.cbec.b2b.entity.Catelog.CateThree;
import com.cbec.b2b.entity.Catelog.CateType;
import com.cbec.b2b.entity.Catelog.CateType2;
import com.cbec.b2b.entity.Catelog.Catelog;
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
		return goodsmapper.getGoodsList(searchGoods);
	}
	@Override
	public List<Goods> getB2BGoodsList(SearchGoods searchGoods) {
		return goodsmapper.getB2BGoodsList(searchGoods);
	}

	@Override
	public Catelog getCatalogAndBrands() {
    	List<CateOne> lco = mapper.getCateOneList();
    	for(CateOne co : lco) {
    		List<CateType2> lct2 = new ArrayList<CateType2>();
    		
    		CateType2 ct2 = new CateType2();
    		List<Brand> lb= mapper.getBrandByCateOneID(co.getId());
    		ct2.setBrands(lb);
    		List<CateTWO> lc2 = mapper.getCateTWOByCateOneID(co.getId());
    		for(CateTWO c2 : lc2) {
    			List<CateThree> lc3= mapper.getCateThreeByCateOneID(co.getId());
    			c2.setChildCate(lc3);
    		}
    		ct2.setCatelog(lc2);
    		lct2.add(ct2);
    		co.setLevel2(lct2);
    	}
    	CateType cateType =new CateType();
    	cateType.setLevel1(lco);
    	
    	Catelog catelog = new Catelog();
    	catelog.setState(0);
    	catelog.setResults(cateType);
    	
        return catelog;
	}
	
}
