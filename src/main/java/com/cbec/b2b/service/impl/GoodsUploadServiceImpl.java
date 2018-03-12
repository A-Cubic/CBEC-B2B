package com.cbec.b2b.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.GoodsUpload.Offer;
import com.cbec.b2b.entity.GoodsUpload.SearchOffer;
import com.cbec.b2b.entity.GoodsUpload.SendType;
import com.cbec.b2b.entity.GoodsUpload.UploadInfo;
import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.SearchGoods;
import com.cbec.b2b.mapper.GoodsMapper;
import com.cbec.b2b.mapper.GoodsUploadMapper;
import com.cbec.b2b.mapper.PublicMapper;
import com.cbec.b2b.mapper.UserMapper;
import com.cbec.b2b.service.IGoodsUploadService;

@Service
public class GoodsUploadServiceImpl implements IGoodsUploadService {

	@Autowired
	GoodsUploadMapper mapper;
	@Autowired
	UserMapper usermapper;
	@Autowired
	GoodsMapper goodsmapper;
	@Autowired
	PublicMapper publicmapper;
	@Override
	public List<UploadInfo> getUploadInfo(String userCode) {
		Map<String,Object> userMap = usermapper.getUserType(userCode);
		String type  = (String)userMap.get("usertype");
		if("0".equals(type)) {
			return mapper.getUploadInfoAll();
		}else {
			return mapper.getUploadInfo(userCode);
		}
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
	public List<Offer> getOfferInfo(SearchOffer searchOffer) {
		return mapper.getOfferInfo(searchOffer);
	}

	@Override
	public int updateOffer(Offer offer) {
		return mapper.updateOffer(offer);
	}

	@Override
	public int writeOffer(String userCode,List<Offer> offerList) {
		// TODO Auto-generated method stub
//		List<Offer> offerList = new ArrayList<Offer>();
		int c=0;
		for(Offer offer :offerList) {
			offer.setUsercode(userCode);
			c+=mapper.writeOfferFromGoods(offer);
		}
		return c;
	}

	@Override
	public List<Goods> getGoodsList(SearchGoods searchGoods) {
		// TODO Auto-generated method stub
		return goodsmapper.getGoodsList(searchGoods);
	}
	@Override
	public List<Goods> getB2BGoodsListToOffer(SearchGoods searchGoods) {
		// TODO Auto-generated method stub
		return goodsmapper.getB2BGoodsListToOffer(searchGoods);
	}
	@Override
	public List<Goods> getB2BGoodsList(SearchGoods searchGoods) {
		// TODO Auto-generated method stub
		return goodsmapper.getB2BGoodsList(searchGoods);
	}

	@Override
	public List<SendType> getSendType() {
		// TODO Auto-generated method stub
		return publicmapper.getSendType();
	}

	@Override
	public SearchOffer getSearchOffer(String userCode, SearchOffer searchOffer) {
		Map<String,Object> userMap = usermapper.getUserType(userCode);
		String type  = (String)userMap.get("usertype");
		if("0".equals(type)||"5".equals(type)) {
			searchOffer.setUserCode("");
		}else {
			searchOffer.setUserCode(userCode);
		}
		return searchOffer;
	}

	@Override
	public Offer getOfferById(String id) {
		// TODO Auto-generated method stub
		return mapper.getOfferById(id);
	}
}
