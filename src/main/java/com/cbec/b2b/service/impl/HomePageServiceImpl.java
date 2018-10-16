package com.cbec.b2b.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.Catelog.Brand;
import com.cbec.b2b.entity.Catelog.CateOne;
import com.cbec.b2b.entity.Catelog.CateTWO;
import com.cbec.b2b.entity.Catelog.CateThree;
import com.cbec.b2b.entity.Catelog.CateType;
import com.cbec.b2b.entity.Catelog.CateType2;
import com.cbec.b2b.entity.Catelog.Catelog;
import com.cbec.b2b.entity.HomePage.Adver;
import com.cbec.b2b.entity.HomePage.AdverType;
import com.cbec.b2b.entity.HomePage.Banner;
import com.cbec.b2b.entity.HomePage.Brands;
import com.cbec.b2b.entity.HomePage.Country;
import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.GoodsList;
import com.cbec.b2b.entity.HomePage.GoodsListOld;
import com.cbec.b2b.entity.HomePage.SearchGoods;
import com.cbec.b2b.entity.HomePage.SendType;
import com.cbec.b2b.mapper.HomePageMapper;
import com.cbec.b2b.service.IHomePageService;

@Service
public class HomePageServiceImpl implements IHomePageService {

	@Autowired
	HomePageMapper mapper;

	@Override
	public List<Banner> getBanner() {
		return mapper.getBanner();
	}

	@Override
	public List<Brands> getBrands() {
		return mapper.getBrands();
	}

//	@Override
//	public List<Goods> getGoods() {
//		return mapper.getGoods();
//	}

	@Override
	public List<Country> getCountry() {
		return mapper.getCountry();
	}

	@Override
	public List<SendType> getSendType() {
		return mapper.getSendType();
	}
	@Override
	public List<Goods> getGoodsByGoodsId(String goodsId) {
		return mapper.getGoodsByGoodsId(goodsId);
	}

	@Override
	public GoodsListOld getGoodsList(SearchGoods searchGoods) {
		if("0".equals(searchGoods.getCatelog1())) {
			searchGoods.setCatelog1(null);
		}
		if("0".equals(searchGoods.getCatelog2())) {
			searchGoods.setCatelog2(null);
		}
		if("1".equals(searchGoods.getSendType())) {
			searchGoods.setIfXG("");
			searchGoods.setIfHW("");
			searchGoods.setIfHG("");
			searchGoods.setIfRB("1");
			searchGoods.setIfBS("");
			searchGoods.setIfMY("");
		}else if("2".equals(searchGoods.getSendType())) {
			searchGoods.setIfXG("");
			searchGoods.setIfHW("");
			searchGoods.setIfHG("1");
			searchGoods.setIfRB("");
			searchGoods.setIfBS("");
			searchGoods.setIfMY("");
		}else if("3".equals(searchGoods.getSendType())) {
			searchGoods.setIfXG("1");
			searchGoods.setIfHW("");
			searchGoods.setIfHG("");
			searchGoods.setIfRB("");
			searchGoods.setIfBS("");
			searchGoods.setIfMY("");
		}else if("4".equals(searchGoods.getSendType())) {
			searchGoods.setIfXG("");
			searchGoods.setIfHW("");
			searchGoods.setIfHG("");
			searchGoods.setIfRB("");
			searchGoods.setIfBS("1");
			searchGoods.setIfMY("");
		}else if("5".equals(searchGoods.getSendType())) {
			searchGoods.setIfXG("");
			searchGoods.setIfHW("1");
			searchGoods.setIfHG("");
			searchGoods.setIfRB("");
			searchGoods.setIfBS("");
			searchGoods.setIfMY("");
		}else if("6".equals(searchGoods.getSendType())) {
			searchGoods.setIfXG("");
			searchGoods.setIfHW("");
			searchGoods.setIfHG("");
			searchGoods.setIfRB("");
			searchGoods.setIfBS("");
			searchGoods.setIfMY("1");
		}
//		if("1".equals(searchGoods.getCountry())) {
//			searchGoods.setCountry("中国");
//		}else if("2".equals(searchGoods.getCountry())) {
//			searchGoods.setCountry("日本");
//		}else if("3".equals(searchGoods.getCountry())) {
//			searchGoods.setCountry("韩国");
//		}else if("4".equals(searchGoods.getCountry())) {
//			searchGoods.setCountry("乌克兰");
//		}else if("4".equals(searchGoods.getCountry())) {
//			searchGoods.setCountry("乌克兰");
//		}else if("4".equals(searchGoods.getCountry())) {
//			searchGoods.setCountry("乌克兰");
//		}
		
		searchGoods.setStartPage((searchGoods.getPageNumber()-1)*searchGoods.getPageSize());
		GoodsListOld goodsListOld =new GoodsListOld();
		List<GoodsList> lg = mapper.getGoodsList(searchGoods);
		for(int i=0;i<lg.size();i++) {
			GoodsList gl = lg.get(i);
			
			String coin =gl.getCoin();
			if("".equals(coin)||coin==null) 
			{
				if("韩国".equals(gl.getCountry())) {
					coin ="₩";
				}else if("中国".equals(gl.getCountry())){
					coin ="¥";
				}else {
					coin ="$";
				}
			}
			if("0.00".equals(gl.getPrice())) {
				if("0.00".equals(gl.getBeginPrice())&&"0.00".equals(gl.getEndPrice())) {
					gl.setPrice(coin+" 0.00");
				}else {
					gl.setPrice(coin+" "+gl.getBeginPrice()+"-"+gl.getEndPrice());
				}
			}else {
				gl.setPrice(coin+" "+gl.getPrice());
			}
			lg.set(i, gl);
		}
		goodsListOld.setList(lg);
		goodsListOld.setPageNum(searchGoods.getPageNumber());
		goodsListOld.setPageSize(searchGoods.getPageSize());
		int total = mapper.getGoodsListTotal(searchGoods);
		int page=total/searchGoods.getPageSize() +1;
		int start=1,end=1;
		if(searchGoods.getPageNumber()>1) {
			goodsListOld.setHasPreviousPage(true);
		}else {
			goodsListOld.setHasPreviousPage(false);
		}
		if(searchGoods.getPageNumber() <page) {
			goodsListOld.setHasNextPage(true);
		}else {
			goodsListOld.setHasNextPage(false);
		}
		goodsListOld.setPageNumber(searchGoods.getPageNumber());
		goodsListOld.setSize(page);
		goodsListOld.setTotal(total);
//		int p=(searchGoods.getPageNumber()-1)/10;
//		int p1=p*10+1;
//		int p2 =(p+1)*10;
//		if(p2>page) {
//			p2=page;
//		}
//		int[] pages = new int[p2-p1+1];
//		for(int i = 0; i<p2-p1+1;i++) {
//			pages[i]=p1+i;
//		}
//		goodsListOld.setPages(pages);
		if(page>=10) {
			goodsListOld.setPages(10);
		}else {
			goodsListOld.setPages(page);
		}
		return goodsListOld;
	}
	@Override
	public List<GoodsList> getB2BGoodsList(SearchGoods searchGoods) {
		if("0".equals(searchGoods.getCatelog1())) {
			searchGoods.setCatelog1(null);
		}
		if("1".equals(searchGoods.getSendType())) {
			searchGoods.setIfXG("1");
			searchGoods.setIfHW("");
			searchGoods.setIfBS("");
			searchGoods.setIfMY("");
		}else if("2".equals(searchGoods.getSendType())) {
			searchGoods.setIfXG("");
			searchGoods.setIfHW("1");
			searchGoods.setIfBS("");
			searchGoods.setIfMY("");
		}else if("3".equals(searchGoods.getSendType())) {
			searchGoods.setIfXG("");
			searchGoods.setIfHW("");
			searchGoods.setIfBS("1");
			searchGoods.setIfMY("");
		}else if("4".equals(searchGoods.getSendType())) {
			searchGoods.setIfXG("");
			searchGoods.setIfHW("");
			searchGoods.setIfBS("");
			searchGoods.setIfMY("1");
		}
		return mapper.getB2BGoodsList(searchGoods);
	}

	@Override
	public Catelog getCatalogAndBrands(SearchGoods searchGoods) {

		if("0".equals(searchGoods.getCatelog2())) {
			searchGoods.setCatelog2(null);
		}
		if("1".equals(searchGoods.getSendType())) {
			searchGoods.setIfXG("");
			searchGoods.setIfHW("");
			searchGoods.setIfHG("");
			searchGoods.setIfRB("1");
			searchGoods.setIfBS("");
			searchGoods.setIfMY("");
		}else if("2".equals(searchGoods.getSendType())) {
			searchGoods.setIfXG("");
			searchGoods.setIfHW("");
			searchGoods.setIfHG("1");
			searchGoods.setIfRB("");
			searchGoods.setIfBS("");
			searchGoods.setIfMY("");
		}else if("3".equals(searchGoods.getSendType())) {
			searchGoods.setIfXG("1");
			searchGoods.setIfHW("");
			searchGoods.setIfHG("");
			searchGoods.setIfRB("");
			searchGoods.setIfBS("");
			searchGoods.setIfMY("");
		}else if("4".equals(searchGoods.getSendType())) {
			searchGoods.setIfXG("");
			searchGoods.setIfHW("");
			searchGoods.setIfHG("");
			searchGoods.setIfRB("");
			searchGoods.setIfBS("1");
			searchGoods.setIfMY("");
		}else if("5".equals(searchGoods.getSendType())) {
			searchGoods.setIfXG("");
			searchGoods.setIfHW("1");
			searchGoods.setIfHG("");
			searchGoods.setIfRB("");
			searchGoods.setIfBS("");
			searchGoods.setIfMY("");
		}else if("6".equals(searchGoods.getSendType())) {
			searchGoods.setIfXG("");
			searchGoods.setIfHW("");
			searchGoods.setIfHG("");
			searchGoods.setIfRB("");
			searchGoods.setIfBS("");
			searchGoods.setIfMY("1");
		}
    	List<CateOne> lco = mapper.getCateOneList();
    	for(CateOne co : lco) {
    		List<CateType2> lct2 = new ArrayList<CateType2>();
    		
    		CateType2 ct2 = new CateType2();
    		searchGoods.setCatelog1(String.valueOf(co.getId()) );
    		List<Brand> lb= mapper.getBrandByCateOneID(searchGoods);
    		ct2.setBrands(lb);
    		List<CateTWO> lc2 = mapper.getCateTWOByCateOneID(co.getId());

    		CateTWO cateTWO =new CateTWO();
    		cateTWO.setId(0);
    		cateTWO.setValue("请选择");
    		lc2.add(0,cateTWO);
//    		for(CateTWO c2 : lc2) {
//    			List<CateThree> lc3= mapper.getCateThreeByCateOneID(co.getId());
//    			c2.setChildCate(lc3);
//    		}
    		ct2.setCatelog(lc2);
    		lct2.add(ct2);
    		co.setLevel2(lct2);
    	}

    	CateOne c= new CateOne();
    	c.setId(0);
    	c.setValue("请选择");
    	List<CateType2> lct2 = new ArrayList<CateType2>();
		
		CateType2 ct2 = new CateType2();
		List<CateTWO> lc2 = new ArrayList<CateTWO>();
		CateTWO cateTWO =new CateTWO();
		cateTWO.setId(0);
		cateTWO.setValue("请选择");
		lc2.add(cateTWO);
		ct2.setCatelog(lc2);
		List<Brand> lb= mapper.getAdvBrandByCateOneID(searchGoods);
		ct2.setBrands(lb);
		lct2.add(ct2);
		c.setLevel2(lct2);
    	lco.add(0, c);
    	
    	CateType cateType =new CateType();
    	cateType.setLevel1(lco);
    	
    	Catelog catelog = new Catelog();
    	catelog.setState(0);
    	catelog.setResults(cateType);
    	
        return catelog;
	}

	@Override
	public Catelog getCatalogAndBrandsNew() {
    	List<CateOne> lco = mapper.getCateOneList();
    	for(CateOne co : lco) {
    		List<CateType2> lct2 = new ArrayList<CateType2>();
    		
    		CateType2 ct2 = new CateType2();
//    		List<Brand> lb= mapper.getBrandByCateOneID(co.getId());
//    		ct2.setBrands(lb);
    		List<CateTWO> lc2 = mapper.getCateTWOByCateOneID(co.getId());
    		for(CateTWO c2 : lc2) {
    			List<CateThree> lc3= mapper.getCateThreeByCateOneID(co.getId());
    			c2.setChildCate(lc3);
    		}
    		ct2.setCatelog(lc2);
    		lct2.add(ct2);
    		co.setLevel2(lct2);
    	}
//
//    	CateOne c= new CateOne();
//    	c.setId(0);
//    	c.setValue("请选择");
//    	List<CateType2> lct2 = new ArrayList<CateType2>();
//		
//		CateType2 ct2 = new CateType2();
//		List<CateTWO> lc2 = new ArrayList<CateTWO>();
//		CateTWO cateTWO =new CateTWO();
//		cateTWO.setId(0);
//		cateTWO.setValue("请选择");
//		lc2.add(cateTWO);
//		ct2.setCatelog(lc2);
//		List<Brand> lb= mapper.getAdvBrandByCateOneID();
//		ct2.setBrands(lb);
//		lct2.add(ct2);
//		c.setLevel2(lct2);
//    	lco.add(0, c);
    	
    	CateType cateType =new CateType();
    	cateType.setLevel1(lco);
    	
    	Catelog catelog = new Catelog();
    	catelog.setState(0);
    	catelog.setResults(cateType);
    	
        return catelog;
	}

	@Override
	public Adver getAdver() {
		List<Banner> LBanner= mapper.getBanner();
    	List<Brands> LBrands= mapper.getBrands();
    	
    	
    	List<GoodsList> lg = mapper.getRecomGoods();
    	for(int i=0;i<lg.size();i++) {
			GoodsList gl = lg.get(i);
			String coin =gl.getCoin();
			if("".equals(coin)||coin==null) 
			{
				if("韩国".equals(gl.getCountry())) {
					coin ="₩";
				}else if("中国".equals(gl.getCountry())){
					coin ="¥";
				}else {
					coin ="$";
				}
			}
			
			if("0.00".equals(gl.getPrice())) {
				if("0.00".equals(gl.getBeginPrice())&&"0.00".equals(gl.getEndPrice())) {
					gl.setPrice(coin+" 0.00");
				}else {
					gl.setPrice(coin+" "+gl.getBeginPrice()+"-"+gl.getEndPrice());
				}
			}else {
				gl.setPrice(coin+" "+gl.getPrice());
			}
			lg.set(i, gl);
		}
    	AdverType at = new AdverType();
    	at.setBanner(LBanner);
    	at.setBrands(LBrands);
    	at.setGoods(lg);
    	
    	Adver a = new Adver();
    	a.setState(0);
    	a.setResults(at);
    	
    	return a;
	}
	
}
