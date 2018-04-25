package com.cbec.b2b.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.api.OrderApi;
import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.common.Util;
import com.cbec.b2b.entity.order.Account;
import com.cbec.b2b.entity.order.AccountOfPurchasers;
import com.cbec.b2b.entity.order.Order;
import com.cbec.b2b.entity.order.OrderGoods;
import com.cbec.b2b.entity.order.SearchOrderList;

@RestController
@RequestMapping(value = "/llback/order")
public class OrderController {
    @Autowired
    OrderApi api;

    @RequestMapping(value = "/list")
    public AccountOfPurchasers getOrderList(@RequestHeader(value = "userid") String userid,@RequestBody SearchOrderList searchOrderList,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.getOrderList(userid,searchOrderList);
    }
    @RequestMapping(value = "/listofwarehouse")
    public PageInfo<Order> getOrderListOfWareHouse(@RequestHeader(value = "userid") String userid,@RequestBody SearchOrderList searchOrderList,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.getOrderListOfWareHouse(userid,searchOrderList);
    }
    @RequestMapping(value = "/goods")
    public List<OrderGoods> getOrderGoods(@RequestHeader(value = "userid") String userid,@RequestBody Map<String,Object> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.getOrderGoods(userid,(String)request.get("orderid"));
    }
    @RequestMapping(value = "/account")
    public Account getAccount(@RequestHeader(value = "userid") String userid,@RequestBody Map<String,Object> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.getAccount(userid,(String)request.get("orderid"));
    }
}



