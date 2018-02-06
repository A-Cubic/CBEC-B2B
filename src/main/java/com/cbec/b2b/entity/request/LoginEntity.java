package com.cbec.b2b.entity.request;

import lombok.Data;

@Data
public class LoginEntity {
	private String userName;
	private String password;
	private String type;
}
