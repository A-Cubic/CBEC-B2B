package com.cbec.b2b.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cbec.b2b.entity.Catelog.Brands;
import com.cbec.b2b.entity.Catelog.CateOne;
import com.cbec.b2b.entity.Catelog.CateTWO;
import com.cbec.b2b.entity.Catelog.CateThree;
import com.cbec.b2b.entity.Catelog.CateType;
import com.cbec.b2b.entity.Catelog.CateType2;
import com.cbec.b2b.entity.Catelog.Catelog;
import com.cbec.b2b.service.IDemoService;

@RestController
@RequestMapping(value = "/web")
public class HomePageController {
    @Autowired
    IDemoService service;
    
    @RequestMapping(value = "/catalog")
    public Catelog catalog() {
    	CateThree ct = new CateThree();
    	ct.setId(10001);
    	ct.setValue("面膜");
    	CateThree ct1 = new CateThree();
    	ct1.setId(10002);
    	ct1.setValue("脸霜");
    	List<CateThree> lc3= new ArrayList<CateThree>();
    	lc3.add(ct);
    	lc3.add(ct1);

    	CateTWO ct2 = new CateTWO();
    	ct2.setId(101);
    	ct2.setValue("护肤品");
    	ct2.setChildCate(lc3);
    	CateTWO ct21 = new CateTWO();
    	ct21.setId(102);
    	ct21.setValue("护肤品2");
    	ct21.setChildCate(lc3);

    	CateTWO ct3 = new CateTWO();
    	ct3.setId(201);
    	ct3.setValue("游戏");
    	ct3.setChildCate(lc3);
    	CateTWO ct31 = new CateTWO();
    	ct31.setId(102);
    	ct31.setValue("3C");
    	ct31.setChildCate(lc3);

    	List<CateTWO> lc2= new ArrayList<CateTWO>();
    	lc2.add(ct2);
    	lc2.add(ct21);
    	List<CateTWO> lc21= new ArrayList<CateTWO>();
    	lc21.add(ct3);
    	lc21.add(ct31);

    	Brands br = new Brands();
    	br.setId(1);
    	br.setValue("B+29");
    	Brands br1 = new Brands();
    	br1.setId(2);
    	br1.setValue("CON");
    	Brands br2 = new Brands();
    	br2.setId(3);
    	br2.setValue("FEyy");

    	List<Brands> lb= new ArrayList<Brands>();
    	lb.add(br);
    	lb.add(br1);
    	lb.add(br2);

    	CateType2 gt= new CateType2();
    	gt.setCatelog(lc2);
    	gt.setBrands(lb);
    	CateType2 gt1= new CateType2();
    	gt1.setCatelog(lc21);
    	gt1.setBrands(lb);
    	
    	List<CateType2> lc = new ArrayList<CateType2>();
    	lc.add(gt);
    	lc.add(gt1);

    	CateOne co = new CateOne();
    	co.setId(1);
    	co.setValue("美用没装");
    	CateOne co1 = new CateOne();
    	co1.setId(2);
    	co1.setValue("其他销售");
    	

    	List<CateOne> lco = new ArrayList<CateOne>();
    	lco.add(co);
    	lco.add(co1);
    	
    	CateType cateType =new CateType();
    	cateType.setLevel1(lco);
    	cateType.setLevel2(lc);
    	
    	Catelog catelog = new Catelog();
    	catelog.setState(0);
    	catelog.setResults(cateType);
    	
        return catelog;
    }
    
}



