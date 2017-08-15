package com.icinfo.platform.oss.api;

import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.icinfo.platform.oss.manager.OssClientManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * oss api
 */
@Service
public class OssApi {
    private static final Logger logger = LoggerFactory.getLogger(OssApi.class);

    /**
     * ossClient管理注入
     */
    @Autowired
    private OssClientManager clientManager;

    public static final String bucketName = "zhang-data" ;

    /**
     * 获取所有bucket
     *
     * @return 所有bucket
     * @throws Exception
     */
    public List<Bucket> selectBucketList() throws Exception {
        return clientManager.getOssClient().listBuckets();
    }

    /**
     * 上传本地文件
     *
     * @throws Exception
     */
    public void uploadLocalFile() throws Exception {
        clientManager.getOssClient().putObject(bucketName, "1", new File("E:\\otherworkspace\\platform\\src\\main\\webapp\\upload\\image\\14876585220751875.png"));
    }

    /**
     * 上传文件：InputStream
     *
     * @param name oss key
     * @param inputStream 输入流
     * @return 上传结果
     * @throws Exception
     */
    public String uploadFile(String name, InputStream inputStream)throws Exception{
        PutObjectResult result = clientManager.getOssClient().putObject(bucketName, name, inputStream);
        return result.getETag();
    }

    /**
     * 上传文件：MultipartFile
     *
     * @param file 文件
     * @return oss key
     * @throws Exception
     */
    public String uploadFile(MultipartFile file) throws Exception {
        if (file.getSize() > 1024 * 1024) {
            throw new Exception("上传图片大小不能超过1M！");
        }
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        //使用时间戳作为key,可以保证不重复，不被覆盖
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        try {
            InputStream inputStream = file.getInputStream();
            this.uploadFile2OSS(inputStream, originalFilename);
            return originalFilename;
        } catch (Exception e) {
            throw new Exception("图片上传失败");
        }
    }

    /**
     * 上传到OSS服务器  如果同名文件会覆盖服务器上的
     *
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 出错返回"" ,唯一MD5数字签名
     */
    public String uploadFile2OSS(InputStream instream, String fileName) {
        String ret = "";
        try {
            //创建上传Object的Metadata(元信息)
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            PutObjectResult putResult = clientManager.getOssClient().putObject(bucketName, fileName, instream);
            ret = putResult.getETag();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 描述: 判断OSS服务文件上传时文件的contentType
     *
     * @param FilenameExtension 文件后缀
     * @return String
     */
    public static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase("jpeg") ||
                FilenameExtension.equalsIgnoreCase("jpg") ||
                FilenameExtension.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase("pptx") ||
                FilenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase("docx") ||
                FilenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = clientManager.getOssClient().generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
}
