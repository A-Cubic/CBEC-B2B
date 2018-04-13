package com.cbec.b2b.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cbec.b2b.entity.GoodsUpload.Offer;
import com.cbec.b2b.entity.GoodsUpload.SearchOffer;
import com.cbec.b2b.entity.GoodsUpload.SearchSellNum;
import com.cbec.b2b.entity.GoodsUpload.SellNum;
import com.cbec.b2b.entity.GoodsUpload.SendType;
import com.cbec.b2b.entity.GoodsUpload.UpdateGoodsNum;
import com.cbec.b2b.entity.GoodsUpload.UploadInfo;
import com.cbec.b2b.entity.GoodsUpload.Goods;
import com.cbec.b2b.entity.GoodsUpload.GoodsList;
import com.cbec.b2b.entity.GoodsUpload.GoodsNumList;
import com.cbec.b2b.entity.GoodsUpload.SearchGoods;
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
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
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
	public List<GoodsList> getGoodsListOfOperate(SearchGoods searchGoods) {
		// TODO Auto-generated method stub
		return goodsmapper.getGoodsListOfOperate(searchGoods);
	}
	@Override
	public List<GoodsList> getGoodsList(SearchGoods searchGoods) {
		// TODO Auto-generated method stub
		return goodsmapper.getGoodsList(searchGoods);
	}
	@Override
	public List<GoodsList> getB2BGoodsListToOffer(SearchGoods searchGoods) {
		// TODO Auto-generated method stub
		return goodsmapper.getB2BGoodsListToOffer(searchGoods);
	}
	@Override
	public List<GoodsList> getB2BGoodsList(SearchGoods searchGoods) {
		// TODO Auto-generated method stub
		return goodsmapper.getB2BGoodsList(searchGoods);
	}

	@Override
	public String updateGoodsOfOperate(String userid, Goods goods) {
		// TODO Auto-generated method stub
		return String.valueOf(goodsmapper.updateGoodsOfOperate(goods));
	}
	@Override
	public Goods getGoodsById(String id) {
		// TODO Auto-generated method stub
		return goodsmapper.getGoodsById(id);
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

	@Override
	public List<GoodsNumList> getGoodsNumListOfSupplier(String userCode,String search,String whid) {
		
		return goodsmapper.getGoodsNumListOfSupplier(userCode, search,whid);
	}
	@Override
	public List<GoodsNumList> getGoodsNumList(String search,String whid) {
		
		return goodsmapper.getGoodsNumList(search,whid);
	}

	@Override
	public String getUserType(String userCode) {
		Map<String,Object> userMap = usermapper.getUserType(userCode);
		return (String)userMap.get("usertype");
	}

	@Override
	public String updateGoodsNum(String userCode, String barcode, int rb, int hg, int gj) {
		UpdateGoodsNum updateGoodsNum = new UpdateGoodsNum();
		
		updateGoodsNum.setBarcode(barcode);
		updateGoodsNum.setGoodsnum(rb);
		updateGoodsNum.setWarehouseid("15");
		updateGoodsNum.setSuppliercode(userCode);
		
		int r = goodsmapper.updateGoodsNum(updateGoodsNum);
		goodsmapper.insertGoodsNumLog(updateGoodsNum);
		
		updateGoodsNum.setGoodsnum(hg);
		updateGoodsNum.setWarehouseid("16");
		int h = goodsmapper.updateGoodsNum(updateGoodsNum);
		goodsmapper.insertGoodsNumLog(updateGoodsNum);
		
		updateGoodsNum.setGoodsnum(gj);
		updateGoodsNum.setWarehouseid("17");
		int g = goodsmapper.updateGoodsNum(updateGoodsNum);
		goodsmapper.insertGoodsNumLog(updateGoodsNum);
		r=r++;
		h=h++;
		g=g++;
//		if(r>0&&h>0&&g>0) {
//			return "完成";
//		}else {
//			return "请刷新";
//		}
		return "调整完成";
	}

	@Override
	public List<SellNum> getSellNumList(SearchSellNum searchSellNum) {
		// TODO Auto-generated method stub
		return goodsmapper.getSellNumList(searchSellNum);
	}


}
