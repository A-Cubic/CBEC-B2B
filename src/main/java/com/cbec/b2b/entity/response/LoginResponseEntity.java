package com.cbec.b2b.entity.response;

import lombok.Data;

@Data
public class LoginResponseEntity {
	private String currentAuthority="guest";
	private String userId="";
	private String token="";
}
