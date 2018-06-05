package com.cbec.b2b.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.entity.MsgResponse;
import com.cbec.b2b.entity.Daigou.SearchTicket;
import com.cbec.b2b.entity.Daigou.Ticket;
import com.cbec.b2b.service.IDaigouService;

@RestController
@RequestMapping(value = "/api/order")
public class DaigouApi {
    @Autowired
    IDaigouService service;

    @RequestMapping(value = "/ticketlist")
    public PageInfo<Ticket> getTicketList(SearchTicket searchTicket) {
    	return service.getTicketList(searchTicket);
    }
    @RequestMapping(value = "/ticket")
    public Ticket getTicketByTicketCode(SearchTicket searchTicket) {
        return service.getTicketByTicketCode(searchTicket.getTicketCode());
    }

    @RequestMapping(value = "/updateStatus")
    public MsgResponse updateStatus(@RequestBody Map<String,Object> request) {
	    return service.updateStatus(request);
    }
    
}



