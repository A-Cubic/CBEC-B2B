package com.cbec.b2b.entity.user;

import lombok.Data;

@Data
public class User {
	private Integer id;
	private String usercode;
	private String usertype;
	private String username;
	private String avatar;
	private String company;
	private String contact;
	private String website;
	private String tel;
	private String email;
	private String three;
	private String img1;
	private String img2;
	private String img3;
	private String createtime;
	private String verifycode;
	private String flag;
	private String lasttime;
	Integer current;
	Integer pageSize;
}
