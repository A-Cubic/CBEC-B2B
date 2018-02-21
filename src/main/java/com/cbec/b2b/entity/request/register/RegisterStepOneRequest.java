package com.cbec.b2b.entity.request.register;

import lombok.Data;

@Data
public class RegisterStepOneRequest {
	private String mail;
	private String captcha;
	private String password;
}
