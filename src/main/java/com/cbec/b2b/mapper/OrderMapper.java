package com.cbec.b2b.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbec.b2b.entity.order.AccountGoods;
import com.cbec.b2b.entity.order.Order;
import com.cbec.b2b.entity.order.OrderGoods;
import com.cbec.b2b.entity.order.SearchOrderList;

public interface OrderMapper {
	List<Order> getOrderList(SearchOrderList searchOrderList);
	List<Order> getOrderListOfWareHouse(SearchOrderList searchOrderList);
	List<Order> getOrderListOfPurchasers(SearchOrderList searchOrderList);
	List<OrderGoods> getOrderGoods(@Param("orderId") String orderId);
	List<AccountGoods> getAccountGoods(@Param("userid") String userid);
}
