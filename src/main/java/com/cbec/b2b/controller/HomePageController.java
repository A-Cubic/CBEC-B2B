package com.cbec.b2b.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.entity.Brands;
import com.cbec.b2b.entity.CateTWO;
import com.cbec.b2b.entity.CateThree;
import com.cbec.b2b.entity.Demo;
import com.cbec.b2b.entity.CateOne;
import com.cbec.b2b.entity.page.PageEntity;
import com.cbec.b2b.entity.response.LoginResponseEntity;
import com.cbec.b2b.service.IDemoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping(value = "/web")
public class HomePageController {
    @Autowired
    IDemoService service;
    
    @RequestMapping(value = "/catalog")
    public List<CateOne> catalog() {
    	CateThree ct = new CateThree();
    	ct.setCateId(10001);
    	ct.setCateName("面膜");
    	CateThree ct1 = new CateThree();
    	ct1.setCateId(10002);
    	ct1.setCateName("脸霜");
    	List<CateThree> lc3= new ArrayList<CateThree>();
    	lc3.add(ct);
    	lc3.add(ct1);

    	CateTWO ct2 = new CateTWO();
    	ct2.setCateTWOId(101);
    	ct2.setCateTWOName("护肤品");
    	ct2.setCateThree(lc3);
    	CateTWO ct21 = new CateTWO();
    	ct2.setCateTWOId(102);
    	ct2.setCateTWOName("护肤品2");
    	ct21.setCateThree(lc3);

    	List<CateTWO> lc2= new ArrayList<CateTWO>();
    	lc2.add(ct2);
    	lc2.add(ct21);

    	Brands br = new Brands();
    	br.setBradnsId(1);
    	br.setBrandsName("B+29");
    	Brands br1 = new Brands();
    	br1.setBradnsId(2);
    	br1.setBrandsName("CON");
    	Brands br2 = new Brands();
    	br2.setBradnsId(3);
    	br2.setBrandsName("FEyy");

    	List<Brands> lb= new ArrayList<Brands>();
    	lb.add(br);
    	lb.add(br1);
    	lb.add(br2);

    	CateOne gt= new CateOne();
    	gt.setCateOneId(1);
    	gt.setCateOneName("美容护肤");
    	gt.setCateTWO(lc2);
    	gt.setBrands(lb);
    	CateOne gt1= new CateOne();
    	gt1.setCateOneId(2);
    	gt1.setCateOneName("身体护理");
    	gt1.setCateTWO(lc2);
    	gt1.setBrands(lb);
    	
    	List<CateOne> lc = new ArrayList<CateOne>();
    	lc.add(gt);
    	lc.add(gt1);
    	
    	
        return lc;
    }
    
}



