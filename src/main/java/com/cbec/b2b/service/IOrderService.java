package com.cbec.b2b.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.order.Order;
import com.cbec.b2b.entity.order.OrderGoods;
import com.cbec.b2b.entity.order.SearchOrderList;

@Service
public interface IOrderService {
	List<Order> getOrderList(SearchOrderList searchOrderList);
	List<OrderGoods> getOrderGoods(String userid,String orderId);
}
