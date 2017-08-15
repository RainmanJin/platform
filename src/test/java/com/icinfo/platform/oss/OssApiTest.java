package com.icinfo.platform.oss;

import com.aliyun.oss.model.Bucket;
import com.icinfo.platform.common.test.SpringTxTestCase;
import com.icinfo.platform.oss.api.OssApi;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * oss api测试
 */
public class OssApiTest extends SpringTxTestCase {
    @Autowired
    private OssApi ossApi;

    @Test
    @Ignore
    public void testSelectBucketList() throws Exception {
        List<Bucket> bucketList = ossApi.selectBucketList();
        for (Bucket bucket : bucketList) {
            System.out.println(bucket.getName()+"-");
        }
    }

    @Test
    @Ignore
    public void testUploadLocalFile() throws Exception {
        ossApi.uploadLocalFile();
    }
}
