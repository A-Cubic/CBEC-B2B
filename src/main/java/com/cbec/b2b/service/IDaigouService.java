package com.cbec.b2b.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.entity.MsgResponse;
import com.cbec.b2b.entity.Daigou.SearchTicket;
import com.cbec.b2b.entity.Daigou.Ticket;

@Service
public interface IDaigouService {
	PageInfo<Ticket> getTicketList(SearchTicket searchTicket);
	Ticket getTicketByTicketCode(String ticketCode);
	MsgResponse updateStatus(Map<String,Object> request);
}
