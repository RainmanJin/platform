package com.icinfo.platform.oss.manager;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * ossClient
 */
@Service
public class OssClientManager {
    /**
     * OSS 访问域名
     */
    @Value("${url_endpoint}")
    private String endpoint;

    /**
     * 用户身份标识
     */
    @Value("${access_key_id}")
    private String accessKeyId;

    /**
     * 访问密钥
     */
    @Value("${access_key_secret}")
    private String accessKeySecret;

    /**
     * ossClient实例
     */
    private OSSClient ossClient = null;

    /**
     * 获取ossClient实例
     *
     * @return ossClient实例
     */
    public OSSClient getOssClient() {
        System.out.println(endpoint + accessKeyId + accessKeySecret);
        if (ossClient == null) {
            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        }
        return ossClient;
    }

    /**
     * 销毁
     */
    public void destory() {
        ossClient.shutdown();
    }
}
