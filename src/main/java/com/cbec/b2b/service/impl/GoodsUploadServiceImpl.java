package com.cbec.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.GoodsUpload.Offer;
import com.cbec.b2b.entity.GoodsUpload.UploadInfo;
import com.cbec.b2b.mapper.GoodsUploadMapper;
import com.cbec.b2b.service.IGoodsUploadService;

@Service
public class GoodsUploadServiceImpl implements IGoodsUploadService {

	@Autowired
	GoodsUploadMapper mapper;
	@Override
	public List<UploadInfo> getUploadInfo(String userCode) {
		return mapper.getUploadInfo(userCode);
	}

	@Override
	public int writeUploadInfo(UploadInfo uploadInfo) {
		return mapper.writeUploadInfo(uploadInfo);
	}
	@Override
	public int deleteUploadInfo(UploadInfo uploadInfo) {
		return mapper.deleteUploadInfo(uploadInfo);
	}

	@Override
	public List<Offer> getOfferInfo(String userCode) {
		return mapper.getOfferInfo(userCode);
	}

	@Override
	public int updateOffer(Offer offer) {
		return mapper.updateOffer(offer);
	}

	@Override
	public int writeOffer(Offer offer) {
		// TODO Auto-generated method stub
		return mapper.writeOffer(offer);
	}
}
