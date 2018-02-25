package com.cbec.b2b.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.GoodsUpload.Offer;
import com.cbec.b2b.entity.GoodsUpload.SearchOffer;
import com.cbec.b2b.entity.GoodsUpload.UploadInfo;
import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.SearchGoods;

@Service
public interface IGoodsUploadService {
	List<UploadInfo> getUploadInfo(String userCode);
	int writeUploadInfo(UploadInfo uploadInfo);
	int deleteUploadInfo(UploadInfo uploadInfo);
	List<Offer> getOfferInfo(String userCode, SearchOffer searchOffer);
	int updateOffer(Offer offer);
	int writeOffer(Offer offer);
	List<Goods> getGoodsList(SearchGoods searchGoods);
}
