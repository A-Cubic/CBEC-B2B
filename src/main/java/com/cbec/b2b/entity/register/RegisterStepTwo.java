package com.cbec.b2b.entity.register;

import lombok.Data;

@Data
public class RegisterStepTwo {
	private String userName;
	private String companyName;
	private String linkman;
	private String linkmanphone;
	private String website;
	private String email;
	private String img1="";
	private String img2="";
	private String img3="";
	private String img4="";
	private String verifycode="3";
}
