package com.cbec.b2b.entity.register;

import lombok.Data;

@Data
public class RegisterStepOne {
	private Integer id;
	private String mail;
	private String avatar;
	private String password;
	private String type;
	private String ofAgent;
	private String verifycode="2";
	private String flag="1";
}
