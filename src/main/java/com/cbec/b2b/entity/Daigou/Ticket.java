package com.cbec.b2b.entity.Daigou;

import java.util.List;

import lombok.Data;

@Data
public class Ticket {
	 int id;
	 String openId;
	 String createTime;
	 String img;
	 String ticketCode;
	 String shopName;
	 String status;
	 String remark;
	 List<TicketBrand> ticketModList;
}