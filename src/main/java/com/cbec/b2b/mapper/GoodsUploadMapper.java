package com.cbec.b2b.mapper;

import java.util.List;

import com.cbec.b2b.entity.GoodsUpload.Offer;
import com.cbec.b2b.entity.GoodsUpload.UploadInfo;

public interface GoodsUploadMapper {
	List<UploadInfo> getUploadInfo(String userCode);
	int writeUploadInfo(UploadInfo uploadInfo);
	int deleteUploadInfo(UploadInfo uploadInfo);
	List<Offer> getOfferInfo(String userCode);
	int updateOffer(Offer offer);
	int writeOffer(Offer offer);
}
