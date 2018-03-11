package com.cbec.b2b.entity.purchase;


import lombok.Data;

@Data
public class Inquiry {
	private int id;//主键
	private String purchasesn;//采购单号
	private String usercode;//用户账号
	private int goodsid;//商品ID
	private String goodsname;//商品名称
	private String barcode;//产品条码
	private double price;//价格
	private double total;//供货数量
	private String flag;//状态
	private String remark;//备注
	private String createtime;//建立时间
	private String company;
	private String sum;

}