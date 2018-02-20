package com.cbec.b2b.mapper;

import java.util.List;

import com.cbec.b2b.entity.GoodsUpload.UploadInfo;

public interface GoodsUploadMapper {
	List<UploadInfo> readUploadInfo(String userCode);
	int writeUploadInfo(UploadInfo uploadInfo);
}
