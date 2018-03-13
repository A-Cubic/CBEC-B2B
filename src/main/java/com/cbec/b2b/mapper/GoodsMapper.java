package com.cbec.b2b.mapper;

import java.util.List;

import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.GoodsList;
import com.cbec.b2b.entity.HomePage.SearchGoods;

public interface GoodsMapper {
	List<Goods> getGoodsList(SearchGoods searchGoods);
	List<GoodsList> getGoodsListOfOperate(SearchGoods searchGoods);
	List<Goods> getB2BGoodsList(SearchGoods searchGoods);
	List<Goods> getB2BGoodsListToOffer(SearchGoods searchGoods);
	int updateGoodsOfOperate(Goods goods);
	Goods getGoodsById(String id);
}
