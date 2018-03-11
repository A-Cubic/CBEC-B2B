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
	public int writeOffer(String userCode,List<Map<String,Object>> request) {
		// TODO Auto-generated method stub
		List<Offer> offerList = new ArrayList<Offer>();
		for(Map<String,Object> map :request) {
			Offer offer = new Offer();
			offer.setUsercode(userCode);
			offer.setBarcode((String)map.get("barcode"));
			offer.setGoodsid((Integer)map.get("id"));
			offer.setGoodsName((String)map.get("goodsname"));
			offer.setSlt((String)map.get("slt"));
			offer.setOffer(0);
			offerList.add(offer);
		}
		return mapper.writeOfferFromGoodsList(offerList);
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
		if("0".equals(type)) {
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
