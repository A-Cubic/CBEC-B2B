package com.cbec.b2b.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.entity.MsgResponse;
import com.cbec.b2b.entity.Daigou.SearchTicket;
import com.cbec.b2b.entity.Daigou.Ticket;
import com.cbec.b2b.mapper.DaigouMapper;
import com.cbec.b2b.service.IDaigouService;
import com.github.pagehelper.PageHelper;

@Service
public class DaigouServiceImpl implements IDaigouService {

	@Autowired
	DaigouMapper mapper;

	@Override
	public PageInfo<Ticket> getTicketList(SearchTicket searchTicket) {
    	PageHelper.startPage(searchTicket.getCurrent(),searchTicket.getPageSize());
    	List<Ticket> LGoods = mapper.getTicketList(searchTicket.getSearch());
    	PageInfo<Ticket> pageData = new PageInfo<Ticket>(LGoods);
    	return pageData;
	}

	@Override
	public Ticket getTicketByTicketCode(String ticketCode) {
		Ticket ticket = mapper.getTicketByTicketCode(ticketCode);
		ticket.setTicketModList(mapper.getTicketBrandByTicketCode(ticketCode));
		return ticket;
	}

	@Override
	public MsgResponse updateStatus(Map<String, Object> request) {
		int num = mapper.updateStatus(request.get("ticketCode").toString(),request.get("status1").toString(),request.get("remark1").toString());
		MsgResponse response = new MsgResponse();
		String result = "";
		if(num > 0) {
			response.setType("1");
			result="成功";
		}else {
			result="失败";
		}
		response.setMsg(result);
		return response;
	}

}
