package com.cbec.b2b.common;

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;

public class OSSUtils {
	private static final Logger logger = LogManager.getLogger(OSSUtils.class);
	private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
	private static String accessKeyId = "LTAIxUcHQaICM3FD";
	private static String accessKeySecret = "NAII5ZJIYFSPQ4ioZAqjIrgw49yCaP";
	private static String bucketName = "ecc-product";
	private static String key = "upload";
	
	public static void uploadOSSToInputStream(InputStream in,String filename) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            if (!ossClient.doesBucketExist(bucketName)) {
                System.out.println("Creating bucket " + bucketName + "\n");
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest= new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            ossClient.putObject(new PutObjectRequest(bucketName,key+"/"+filename, in));
        } catch (Exception e) {
        	logger.error("上传oss失败，原因："+e.getMessage());
        }  finally {
            ossClient.shutdown();
        }
    }
    
}
