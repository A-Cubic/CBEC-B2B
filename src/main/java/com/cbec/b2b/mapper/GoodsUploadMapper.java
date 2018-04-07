package com.cbec.b2b.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbec.b2b.entity.GoodsUpload.GoodsNumList;
import com.cbec.b2b.entity.GoodsUpload.Offer;
import com.cbec.b2b.entity.GoodsUpload.SearchOffer;
import com.cbec.b2b.entity.GoodsUpload.UploadInfo;

public interface GoodsUploadMapper {
	List<UploadInfo> getUploadInfo(String userCode);
	List<UploadInfo> getUploadInfoAll();
	int writeUploadInfo(UploadInfo uploadInfo);
	int deleteUploadInfo(UploadInfo uploadInfo);
	List<Offer> getOfferInfo(SearchOffer searchOffer);
	List<Offer> getOfferInfoByGoodsId(String goodsid);
	Offer getOfferById(String id);
	int updateOffer(Offer offer);
	int writeOffer(Offer offer);
	int writeOfferFromGoods(Offer offerList);
	List<GoodsNumList> getGoodsNumListOfSupplier(@Param("userCode") String userCode,@Param("search") String search);
	List<GoodsNumList> getGoodsNumList(@Param("search") String search);
}
