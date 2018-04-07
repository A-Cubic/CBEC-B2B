package com.cbec.b2b.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.GoodsUpload.Offer;
import com.cbec.b2b.entity.GoodsUpload.SearchOffer;
import com.cbec.b2b.entity.GoodsUpload.SendType;
import com.cbec.b2b.entity.GoodsUpload.UploadInfo;
import com.cbec.b2b.entity.GoodsUpload.Goods;
import com.cbec.b2b.entity.GoodsUpload.GoodsList;
import com.cbec.b2b.entity.GoodsUpload.GoodsNumList;
import com.cbec.b2b.entity.GoodsUpload.SearchGoods;

@Service
public interface IGoodsUploadService {
	List<UploadInfo> getUploadInfo(String userCode);
	int writeUploadInfo(UploadInfo uploadInfo);
	int deleteUploadInfo(UploadInfo uploadInfo);
	List<Offer> getOfferInfo(SearchOffer searchOffer);
	Offer getOfferById(String id);
	int updateOffer(Offer offer);
	int writeOffer(String userCode,List<Offer> offerList);
	List<GoodsList> getGoodsList(SearchGoods searchGoods);
	List<GoodsList> getGoodsListOfOperate(SearchGoods searchGoods);
	List<GoodsList> getB2BGoodsListToOffer(SearchGoods searchGoods);
	List<GoodsList> getB2BGoodsList(SearchGoods searchGoods);
	String updateGoodsOfOperate(String userid,Goods goods);
	List<SendType> getSendType();
	SearchOffer getSearchOffer(String userCode, SearchOffer searchOffer);
	Goods getGoodsById(String id);
	List<GoodsNumList> getGoodsNumListOfSupplier(String userCode,String search);
	List<GoodsNumList> getGoodsNumList(String search);
	String getUserType(String userCode);
}
