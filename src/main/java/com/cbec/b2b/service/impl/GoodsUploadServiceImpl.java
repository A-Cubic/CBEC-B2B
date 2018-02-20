package com.cbec.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbec.b2b.entity.GoodsUpload.UploadInfo;
import com.cbec.b2b.mapper.GoodsUploadMapper;
import com.cbec.b2b.service.IGoodsUploadService;

@Service
public class GoodsUploadServiceImpl implements IGoodsUploadService {

	@Autowired
	GoodsUploadMapper mapper;
	@Override
	public List<UploadInfo> readUploadInfo(String userCode) {
		return mapper.readUploadInfo(userCode);
	}

	@Override
	public int writeUploadInfo(UploadInfo uploadInfo) {
		// TODO Auto-generated method stub
		return mapper.writeUploadInfo(uploadInfo);
	}
	
}
