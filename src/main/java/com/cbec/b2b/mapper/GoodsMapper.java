package com.cbec.b2b.mapper;

import java.util.List;

import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.SearchGoods;

public interface GoodsMapper {
	List<Goods> getGoodsList(SearchGoods searchGoods);
}
