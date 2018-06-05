package com.cbec.b2b.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.api.DaigouApi;
import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.common.Util;
import com.cbec.b2b.entity.MsgResponse;
import com.cbec.b2b.entity.Daigou.SearchTicket;
import com.cbec.b2b.entity.Daigou.Ticket;

@RestController
@RequestMapping(value = "/llback/daigou")
public class DaigouController {
    @Autowired
    DaigouApi api;

    @RequestMapping(value = "/ticketlist")
    public PageInfo<Ticket> getTicketList(@RequestHeader(value = "userid") String userid,@RequestBody SearchTicket searchTicket,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.getTicketList(searchTicket);
    }
    @RequestMapping(value = "/ticket")
    public Ticket getTicketByTicketCode(@RequestHeader(value = "userid") String userid,@RequestBody SearchTicket searchTicket,HttpServletResponse res) {
		Util.responseResultSuccess(res);
		Ticket t = api.getTicketByTicketCode(searchTicket);
        return t;
    }
    @RequestMapping(value = "/updateStatus")
    public MsgResponse updateStatus(@RequestHeader(value = "userid") String userid,@RequestBody Map<String,Object> request,HttpServletResponse res) {
		Util.responseResultSuccess(res);
        return api.updateStatus(request);
    }
}



