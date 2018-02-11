package com.cbec.b2b.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.Demo;

@Service
public interface IDemoService {
	List<Demo> pageDemo();
}
