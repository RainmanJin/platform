package com.icinfo.platform.wechat.CustomerService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icinfo.platform.common.test.SpringTxTestCase;
import com.icinfo.platform.wechat.wxsdk.customservice.CustomerServiceManager;
import com.icinfo.platform.wechat.wxsdk.customservice.bean.KfList;
import org.junit.Test;

/**
 * Created by Administrator on 2017/6/1.
 */
public class CustomerServiceManagerTest extends SpringTxTestCase {
    @Test
    public void testGetKfList() throws Exception {
        KfList kfList = CustomerServiceManager.getKfList();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValueAsString(kfList);
    }

}
