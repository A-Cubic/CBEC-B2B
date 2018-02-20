package com.cbec.b2b.entity.GoodsUpload;


import lombok.Data;

@Data
public class UploadInfo {
	private int id;
	private String usercode;
	private String createTime;
	private String fileUrl;
	private String remark;
	private String flag;
	private String remark2;

}