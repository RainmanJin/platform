package com.icinfo.platform.kafka.api;//package com.icinfo.platform.kafka.api;
//
//
//import kafka.javaapi.producer.Producer;
//import kafka.producer.KeyedMessage;
//import kafka.producer.ProducerConfig;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
///**
// * 生产者示例
// */
//public class ProducerDemo {
//    public static void main(String[] args) throws IOException {
//        //1.读取配置文件中的配置，放入Properties对象中
//        Properties properties = new Properties();
//        properties.load(new FileInputStream(
//                new File("src/main/resources/entry/kafka-producer.properties")));
//
//        //2.配置文件的配置设置到生产配置类中
//        ProducerConfig producerConfig = new ProducerConfig(properties);
//
//        //3.创建producer
//        Producer<String, String> producer = new Producer<>(producerConfig);
//
//        // 发送消息
//        for (int i = 1; i <= 10; i++) {
//            String msg = "kafka producer  ===   " + i;
//            //将信息保存到消息密匙类中
//            KeyedMessage<String, String> data = new KeyedMessage<>("test003",String.valueOf(i), msg);
//            //发送消息
//            producer.send(data);
//            System.out.println(msg);
//        }
//        producer.close();
//    }
//}
