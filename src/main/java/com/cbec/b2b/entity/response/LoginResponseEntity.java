package com.cbec.b2b.entity.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseEntity {
	private String currentAuthority="guest";
	private String userId="";
	private String token="";
	
	@Override
	public String toString(){
	  return String.format("{\"currentAuthority\":\"%s\",\"token\":{\"token\":\"%s\",\"userId\":\"%s\"}}", currentAuthority, token, userId);
	}
}
