package com.cbec.b2b.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.GoodsUpload.Offer;
import com.cbec.b2b.entity.GoodsUpload.UploadInfo;

@Service
public interface IGoodsUploadService {
	List<UploadInfo> getUploadInfo(String userCode);
	int writeUploadInfo(UploadInfo uploadInfo);
	int deleteUploadInfo(UploadInfo uploadInfo);
	List<Offer> getOfferInfo(String userCode);
	int updateOffer(Offer offer);
	int writeOffer(Offer offer);
}
