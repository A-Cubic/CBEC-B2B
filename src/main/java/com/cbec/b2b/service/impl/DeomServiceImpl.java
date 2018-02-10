package com.cbec.b2b.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.Demo;
import com.cbec.b2b.mapper.DemoMapper;
import com.cbec.b2b.service.IDemoService;

@Service
public class DeomServiceImpl implements IDemoService {

	@Autowired
	DemoMapper mapper;

	@Override
	public List<Demo> pageDemo() {
		List<Demo> list = mapper.pageDemo();
		return list==null?new ArrayList<Demo>():list;
	}
	
}
