package com.cbec.b2b.entity.purchase;


import lombok.Data;

@Data
public class PurchaseGoods {
	private String key;   //主键
	private String id;   //主键
	private String purchasesn;   //采购单号
	private String goodsid;   //商品ID
	private String goodsname;   //商品名称
	private String price;   //商品价格
	private String deliverytype;   //提货方式
	private String expectprice;   //期望价格
	private String realprice;   //实际价格
	private String total;   //商品数量
	private String barcode;   //产品条码
	private String costprice;   //成本价
	private String otherprice;   //其他费用
	private String supplierid;   //供货商ID
	private String sum;

}