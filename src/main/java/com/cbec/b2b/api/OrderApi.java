package com.cbec.b2b.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.entity.order.Account;
import com.cbec.b2b.entity.order.Order;
import com.cbec.b2b.entity.order.OrderGoods;
import com.cbec.b2b.entity.order.SearchOrderList;
import com.cbec.b2b.service.IOrderService;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping(value = "/api/order")
public class OrderApi {
    @Autowired
    IOrderService service;

    @RequestMapping(value = "/list")
    public PageInfo<Order> getOrderList(@RequestHeader(value = "userid") String userid,@RequestBody SearchOrderList searchOrderList) {
    	PageHelper.startPage(searchOrderList.getCurrent(),searchOrderList.getPageSize());
    	List<Order> LOrder = service.getOrderList(searchOrderList);
    	PageInfo<Order> pageData = new PageInfo<Order>(LOrder);
        return pageData;
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



