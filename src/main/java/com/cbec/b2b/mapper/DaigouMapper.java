package com.cbec.b2b.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbec.b2b.entity.Daigou.Ticket;
import com.cbec.b2b.entity.Daigou.TicketBrand;

public interface DaigouMapper {
//	List<Ticket> getTicketList();
	List<Ticket> getTicketList(@Param("search") String search);
	Ticket getTicketByTicketCode(@Param("ticketCode") String ticketCode);
	List<TicketBrand> getTicketBrandByTicketCode(@Param("ticketCode") String ticketCode);
	int updateStatus(@Param("ticketCode") String ticketCode,@Param("status") String status,@Param("remark") String remark);
}
