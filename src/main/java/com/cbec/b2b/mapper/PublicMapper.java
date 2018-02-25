package com.cbec.b2b.mapper;

import java.util.List;

import com.cbec.b2b.entity.GoodsUpload.SendType;

public interface PublicMapper {
	String getSeq(String seqName);
	List<SendType> getSendType();
}
