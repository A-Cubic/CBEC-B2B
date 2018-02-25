package com.cbec.b2b.entity;

import lombok.Data;

@Data
public class MsgResponse {
	private String msg="";
	private String type="0";//0:错误； 1：正确 ；-1：跳出倒数
}
