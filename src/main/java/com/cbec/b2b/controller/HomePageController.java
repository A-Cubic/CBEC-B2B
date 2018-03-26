package com.cbec.b2b.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.api.HomePageApi;
import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.entity.Catelog.Catelog;
import com.cbec.b2b.entity.HomePage.Adver;
import com.cbec.b2b.entity.HomePage.Goods;
import com.cbec.b2b.entity.HomePage.GoodsInfo;
import com.cbec.b2b.entity.HomePage.GoodsList;
import com.cbec.b2b.entity.HomePage.Screen;
import com.cbec.b2b.entity.HomePage.SearchGoods;
import com.cbec.b2b.entity.HomePage.SearchGoods2;

@RestController
@RequestMapping(value = "/web")
public class HomePageController {
    @Autowired
    HomePageApi api;

    @RequestMapping(value = "/catalog")
    public Catelog catalog() {
        return api.getCatalogAndBrands();
    }
    @RequestMapping(value = "/adver")
    public Adver adver() {
        return api.getAdver();
    }
    @RequestMapping(value = "/Screen")
    public Screen screen() {
        return api.getScreen();
    }
    @RequestMapping(value = "/GoodsList")
    public PageInfo<GoodsList> goodslist(@RequestBody SearchGoods searchGoods ) {
    	return api.getGoodsList(searchGoods);
    }
    @RequestMapping(value = "/B2BGoodsList")
    public PageInfo<GoodsList> b2bGoodslist(@RequestBody SearchGoods searchGoods ) {
    	return api.getB2BGoodsList(searchGoods);
    }
    @RequestMapping(value = "/Goods")
    public GoodsInfo goods(@RequestBody SearchGoods2 searchGoods) {
    	return api.getGoods(searchGoods.getGoodsId());
    }

    @RequestMapping(value = "/Phone")
    public String Phone(@RequestBody Map<String,Object> request) {
    	
    	StringBuffer sb = new StringBuffer();
    	   InputStreamReader isr = null;
    	   BufferedReader br = null;
    	   try
    	   {
    		 String code = URLEncoder.encode("#code#=333242", "GBK").toLowerCase();  //输出%C4%E3%BA%C3
    	     URL url = new URL("http://v.juhe.cn/sms/send?mobile=13644237400&tpl_id=68600&tpl_value="+code+"&key=7c21d791256af1ffdd85375c64846358");
    	     URLConnection urlConnection = url.openConnection();
    	     urlConnection.setAllowUserInteraction(false);
    	     isr = new InputStreamReader(url.openStream());
    	     br = new BufferedReader(isr);
    	     String line;
    	     while ((line = br.readLine()) != null)
    	     {
    	      sb.append(line);
    	     }
    	   }
    	   catch (IOException e)
    	   {
    	     e.printStackTrace();
    	   }
    	   finally
    	   {
    	     
    	   }
    	   System.out.println(sb.toString());
    	   return sb.toString();
    }
    
}



