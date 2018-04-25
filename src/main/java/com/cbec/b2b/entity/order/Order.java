package com.cbec.b2b.entity.order;

import lombok.Data;

@Data
public class Order {
	
	private int id;
	private String merchantOrderId;
	private String tradeTime;
	private String tradeAmount;
	private String goodsTotalAmount;
	private String status;
	private String consigneeName;
	private String consigneeMobile;
	private String addrCountry;
	private String addrProvince;
	private String addrCity;
	private String addrDistrict;
	private String addrDetail;
	private String idNumber;
	private String yjsum;
}