package com.cbec.b2b.mapper;

import java.util.List;

import com.cbec.b2b.entity.GoodsUpload.Goods;
import com.cbec.b2b.entity.GoodsUpload.GoodsList;
import com.cbec.b2b.entity.GoodsUpload.SearchGoods;

public interface GoodsMapper {
	List<GoodsList> getGoodsList(SearchGoods searchGoods);
	List<GoodsList> getGoodsListOfOperate(SearchGoods searchGoods);
	List<GoodsList> getB2BGoodsList(SearchGoods searchGoods);
	List<GoodsList> getB2BGoodsListToOffer(SearchGoods searchGoods);
	int updateGoodsOfOperate(Goods goods);
	Goods getGoodsById(String id);
}
