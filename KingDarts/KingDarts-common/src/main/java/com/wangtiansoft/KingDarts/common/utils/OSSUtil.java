package com.wangtiansoft.KingDarts.common.utils;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;

import java.io.File;
import java.net.URL;
import java.util.Date;

/**
 * Created by 李淋栋 on 16/9/28.
 */
public class OSSUtil {

    private static String accessKeyId = "LTAI1lRxZgArhki9";
    private static String accessKeySecret = "YniZNavLsybAmiOoijpXNYfzYZjXKZ";

    //  vps地址
    private static String endpoint = "vpc100-oss-cn-qingdao.aliyuncs.com";
    private static String bucketName = "ab-darts";
//    private static String endpoint = "http://resource.lovedarts.cn/";
//    private static String bucketName = "ab-darts";

    //  外网地址
    //    private static String endpoint = "oss-cn-qingdao.aliyuncs.com";
    //    private static String bucketName = "ab-darts";

    private static long signDuration = 1000 * 3600; //  1小时

    public static URL sign(String fileName, String mimeType) {

        Date expiration = new Date(System.currentTimeMillis() + signDuration);
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, fileName, HttpMethod.PUT);
        request.setContentType(mimeType);
        request.setExpiration(expiration);
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        URL signedUrl = ossClient.generatePresignedUrl(request);
        ossClient.shutdown();
        return signedUrl;
    }

    public static boolean doesObjectExist(String fileName) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        boolean b = ossClient.doesObjectExist(bucketName, fileName);
        ossClient.shutdown();
        return b;
    }

    public static String uploadFile(File file, String fileName) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, file);
        ossClient.shutdown();
        return fileName;
    }

}
