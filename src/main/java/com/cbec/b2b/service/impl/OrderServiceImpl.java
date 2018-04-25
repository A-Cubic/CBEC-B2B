package com.cbec.b2b.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.entity.order.Account;
import com.cbec.b2b.entity.order.AccountGoods;
import com.cbec.b2b.entity.order.AccountOfPurchasers;
import com.cbec.b2b.entity.order.Order;
import com.cbec.b2b.entity.order.OrderGoods;
import com.cbec.b2b.entity.order.SearchOrderList;
import com.cbec.b2b.mapper.OrderMapper;
import com.cbec.b2b.service.IOrderService;
import com.github.pagehelper.PageHelper;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	OrderMapper mapper;

	@Override
	public AccountOfPurchasers getOrderList(SearchOrderList searchOrderList) {
		// TODO Auto-generated method stub
		AccountOfPurchasers a = new AccountOfPurchasers();
		PageHelper.startPage(searchOrderList.getCurrent(),searchOrderList.getPageSize());
    	List<Order> LOrder = mapper.getOrderList(searchOrderList);
    	PageInfo<Order> pageData = new PageInfo<Order>(LOrder);
		a.setListOrder(pageData);
		double sum=0,sumyj=0;
		try {
			for(Order o :LOrder) {
				sum+=Double.parseDouble(o.getTradeAmount());
				sumyj+=Double.parseDouble(o.getYjsum());
			}
		}catch(Exception e) {
			return null;
		}
		a.setTotal(sum);
		a.setTotalyj(sumyj);
		return a;
	}
	@Override
	public AccountOfPurchasers getOrderListOfPurchasers(SearchOrderList searchOrderList){
		// TODO Auto-generated method stub
		AccountOfPurchasers a = new AccountOfPurchasers();
		PageHelper.startPage(searchOrderList.getCurrent(),searchOrderList.getPageSize());
    	List<Order> LOrder = mapper.getOrderListOfPurchasers(searchOrderList);
    	PageInfo<Order> pageData = new PageInfo<Order>(LOrder);
		a.setListOrder(pageData);
		double sum=0,sumyj=0;
		try {
			for(Order o :LOrder) {
				sum+=Double.parseDouble(o.getTradeAmount());
				sumyj+=Double.parseDouble(o.getYjsum());
			}
		}catch(Exception e) {
			return null;
		}
		BigDecimal bg = new BigDecimal(sum);
		sum=bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		bg = new BigDecimal(sumyj);
		sumyj=bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		a.setTotal(sum);
		a.setTotalyj(sumyj);
		return a;
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
