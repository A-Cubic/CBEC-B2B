package com.cbec.b2b.entity.GoodsUpload;

import lombok.Data;

@Data
public class SendType {
	private String id;   //序号
	private String typename;   //取货方式名称
	private String realname;   //名字
	private String mobile;   //手机
	private String province;   //省
	private String city;   //市
	private String area;   //区
	private String address;   //地址
	private String isdefault;   //是否默认
	private String zipcode;   //邮编
	private String datavalue;   //数据值省市区的id，用空格分开 例如： 110 11001 1100101
}