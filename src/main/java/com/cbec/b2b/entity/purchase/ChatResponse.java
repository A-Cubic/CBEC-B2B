package com.cbec.b2b.entity.purchase;

import lombok.Data;

@Data
public class ChatResponse {
	private String id;
	private String sender;
	private String avatar;
	private String purchasesn;
	private String inquiry_id;
	private String content;
	private String createtime;
}
