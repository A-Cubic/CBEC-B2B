package com.cbec.b2b.mapper;

import java.util.List;

import com.cbec.b2b.entity.Catelog.Brand;
import com.cbec.b2b.entity.Catelog.CateOne;
import com.cbec.b2b.entity.Catelog.CateTWO;
import com.cbec.b2b.entity.Catelog.CateThree;
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
	List<CateOne> getCateOneList();
	List<Brand> getBrandByCateOneID(int cateOneID);
	List<CateTWO>  getCateTWOByCateOneID(int cateOneID);
	List<CateThree>  getCateThreeByCateOneID(int cateOneID);
}
