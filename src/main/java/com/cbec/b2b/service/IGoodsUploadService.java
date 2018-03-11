package com.cbec.b2b.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.GoodsUpload.Offer;
import com.cbec.b2b.entity.GoodsUpload.SearchOffer;
import com.cbec.b2b.entity.GoodsUpload.SendType;
import com.cbec.b2b.entity.GoodsUpload.UploadInfo;
import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.SearchGoods;

@Service
public interface IGoodsUploadService {
	List<UploadInfo> getUploadInfo(String userCode);
	int writeUploadInfo(UploadInfo uploadInfo);
	int deleteUploadInfo(UploadInfo uploadInfo);
	List<Offer> getOfferInfo(SearchOffer searchOffer);
	Offer getOfferById(String id);
	int updateOffer(Offer offer);
	int writeOffer(String userCode,List<Map<String,Object>> request);
	List<Goods> getGoodsList(SearchGoods searchGoods);
	List<Goods> getB2BGoodsListToOffer(SearchGoods searchGoods);
	List<Goods> getB2BGoodsList(SearchGoods searchGoods);
	List<SendType> getSendType();
	SearchOffer getSearchOffer(String userCode, SearchOffer searchOffer);
}
