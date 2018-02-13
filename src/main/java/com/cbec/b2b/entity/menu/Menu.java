package com.cbec.b2b.entity.menu;

import java.util.List;

import lombok.Data;

@Data
public class Menu {
	private int id;
	private String name;
	private String icon;
	private String path;
	private List<MenuChildren> children;
}
