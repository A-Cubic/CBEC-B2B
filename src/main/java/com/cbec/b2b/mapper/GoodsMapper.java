package com.cbec.b2b.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbec.b2b.entity.GoodsUpload.Goods;
import com.cbec.b2b.entity.GoodsUpload.GoodsList;
import com.cbec.b2b.entity.GoodsUpload.GoodsNumList;
import com.cbec.b2b.entity.GoodsUpload.SearchGoods;
import com.cbec.b2b.entity.GoodsUpload.SearchSellNum;
import com.cbec.b2b.entity.GoodsUpload.SellNum;
import com.cbec.b2b.entity.GoodsUpload.UpdateGoodsNum;

public interface GoodsMapper {
	List<GoodsList> getGoodsList(SearchGoods searchGoods);
	List<GoodsList> getGoodsListOfOperate(SearchGoods searchGoods);
	List<GoodsList> getB2BGoodsList(SearchGoods searchGoods);
	List<GoodsList> getB2BGoodsListToOffer(SearchGoods searchGoods);
	int updateGoodsOfOperate(Goods goods);
	Goods getGoodsById(String id);
	List<GoodsNumList> getGoodsNumListOfSupplier(@Param("userCode") String userCode,@Param("search") String search,@Param("whid") String whid);
	List<GoodsNumList> getGoodsNumList(@Param("search") String search,@Param("whid") String whid);
	int updateGoodsNum(UpdateGoodsNum updateGoodsNum);
	int insertGoodsNumLog(UpdateGoodsNum updateGoodsNum);
	List<SellNum> getSellNumList(SearchSellNum searchSellNum);
	GoodsNumList getGoodsNumByBarcode(String barcode);
}
