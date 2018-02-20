package com.cbec.b2b.entity.menu;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class MenuChildren {
	private String name;
	private String path;
	@JsonIgnore 
	private String authoritys;
	private List<String> authority;
}
