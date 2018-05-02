package com.cbec.b2b.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.entity.GoodsUpload.GoodsNumList;
import com.cbec.b2b.entity.order.Account;
import com.cbec.b2b.entity.order.AccountOfOrder;
import com.cbec.b2b.entity.order.Order;
import com.cbec.b2b.entity.order.OrderGoods;
import com.cbec.b2b.entity.order.SearchOrderList;
import com.cbec.b2b.service.IGoodsUploadService;
import com.cbec.b2b.service.IOrderService;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping(value = "/api/order")
public class OrderApi {
    @Autowired
    IOrderService service;
    @Autowired
    IGoodsUploadService goodsservice;

    @RequestMapping(value = "/list")
    public AccountOfOrder getOrderList(@RequestHeader(value = "userid") String userid,@RequestBody SearchOrderList searchOrderList) {
    	String type = goodsservice.getUserType(userid);
    	searchOrderList.setUserCode(userid);
    	if("new".equals(searchOrderList.getStatus())) {
    		searchOrderList.setStatus("新订单");
    	}else if("fh".equals(searchOrderList.getStatus())) {
    		searchOrderList.setStatus("已发货");
    	}else if("wc".equals(searchOrderList.getStatus())) {
    		searchOrderList.setStatus("已完成");
    	}else if("all".equals(searchOrderList.getStatus())) {
    		searchOrderList.setStatus("全部");
    	}
    	
    	if("2".equals(type)) {
            return service.getOrderListOfPurchasers(searchOrderList);
		}else {
	        return service.getOrderList(searchOrderList);
		}
    	
    }
    @RequestMapping(value = "/listofwarehouse")
    public AccountOfOrder getOrderListOfWareHouse(@RequestHeader(value = "userid") String userid,@RequestBody SearchOrderList searchOrderList) {
    	
    	if("new".equals(searchOrderList.getStatus())) {
    		searchOrderList.setStatus("新订单");
    	}else if("fh".equals(searchOrderList.getStatus())) {
    		searchOrderList.setStatus("已发货");
    	}else if("wc".equals(searchOrderList.getStatus())) {
    		searchOrderList.setStatus("已完成");
    	}else if("all".equals(searchOrderList.getStatus())) {
    		searchOrderList.setStatus("全部");
    	}else {
    		searchOrderList.setStatus("全部");
    	}
    	return service.getOrderListOfWareHouse(searchOrderList);
    }
    @RequestMapping(value = "/goods")
    public List<OrderGoods> getOrderGoods(@RequestHeader(value = "userid") String userid,@RequestBody String orderId) {
    	return service.getOrderGoods(userid, orderId);
    	
    }
    @RequestMapping(value = "/account")
    public Account getAccount(@RequestHeader(value = "userid") String userid,@RequestBody String orderId) {
    	return service.getAccount(userid);
    	
    }
}



