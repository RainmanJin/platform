package com.icinfo.platform.kafka.api;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * kafka消息发布-订阅测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"classpath:entry/dev/spring-entry.xml"}
)
public class ApiTest {
    //@Autowired
    //private KafkaTemplate<Integer, String> kafkaTemplate;
    //
    ///**
    // * 发布消息测试.
    // */
    //@Test
    //@Ignore
    //public void testTemplateSend() {
    //    for (int i = 0; i < 6; i++) {
    //        kafkaTemplate.sendDefault(i, "Test_" + i);
    //    }
    //}

}
