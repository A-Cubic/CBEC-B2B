package com.cbec.b2b.entity.purchase;


import java.util.List;

import lombok.Data;

@Data
public class Purchase {
	private int id;   //主键
	private String purchasesn;   //采购单号
	private String userCode;   //采购商账号
	private String stage;   //采购单阶段
	private String status;   //询价状态
	private String goodsnames;   //主要商品名称
	private String sendtype;   //取货方式id
	private String sendtypename;   //取货方式
	private String address;   //目的地
	private String deliverytime;   //纳期
	private String currency;   //币种
	private String createtime;   //建立时间
	private String remark;   //备注
}