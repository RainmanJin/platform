package com.icinfo.platform.image.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icinfo.platform.business.dto.CollegeDto;
import com.icinfo.platform.common.test.SpringTxTestCase;
import com.icinfo.platform.image.dao.ImageDao;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 图片服务测试
 */
public class ImageServiceTest extends SpringTxTestCase {
    @Autowired
    private IImageService imageService;

    @Test
    @Ignore
    public void testSelectOne() throws Exception {
        String id = "25249699f80411e699b300ff2f66a4f5";
        Map<String, Object> map = imageService.selectOne(id);
        System.out.println(map.get("I_URL"));

        Map<String, Object> map1 = imageService.selectOne(id);
        System.out.println(map.get("I_TYPE"));
    }
}
