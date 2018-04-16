package com.cbec.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.order.Account;
import com.cbec.b2b.entity.order.AccountGoods;
import com.cbec.b2b.entity.order.Order;
import com.cbec.b2b.entity.order.OrderGoods;
import com.cbec.b2b.entity.order.SearchOrderList;
import com.cbec.b2b.mapper.OrderMapper;
import com.cbec.b2b.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	OrderMapper mapper;

	@Override
	public List<Order> getOrderList(SearchOrderList searchOrderList) {
		// TODO Auto-generated method stub
		return mapper.getOrderList(searchOrderList);
	}
	@Override
	public List<Order> getOrderListOfWareHouse(SearchOrderList searchOrderList) {
		// TODO Auto-generated method stub
		return mapper.getOrderListOfWareHouse(searchOrderList);
	}

	@Override
	public List<OrderGoods> getOrderGoods(String userid, String orderId) {
		// TODO Auto-generated method stub
		return mapper.getOrderGoods(orderId);
	}

	@Override
	public Account getAccount(String userid) {
		List<AccountGoods> listAG = mapper.getAccountGoods(userid);
		double sum=0;
		try {
			for(AccountGoods ag :listAG) {
				ag.setSum(ag.getOffer() *ag.getQuantity());
				sum+=ag.getSum();
			}
		}catch(Exception e) {
			return null;
		}
		Account account = new Account();
		account.setTotal(sum);
		account.setListAG(listAG);
		return account;
	}
}
