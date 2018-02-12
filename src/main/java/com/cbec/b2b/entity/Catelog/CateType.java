package com.cbec.b2b.entity.Catelog;

import java.util.List;

import lombok.Data;

@Data
public class CateType {
	private List<CateOne> level1;
	private List<CateType2> level2;
}
