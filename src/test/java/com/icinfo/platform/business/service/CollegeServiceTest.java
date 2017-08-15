package com.icinfo.platform.business.service;

import com.icinfo.platform.common.test.SpringTxTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/6/15.
 */
public class CollegeServiceTest extends SpringTxTestCase{
    @Autowired
    private ICollegeService collegeService;

    @Test
    public void test() throws Exception{
        int rlt = collegeService.add(1, 1);
        System.out.println(rlt);
    }
}
