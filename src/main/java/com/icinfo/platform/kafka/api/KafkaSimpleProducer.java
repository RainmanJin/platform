package com.icinfo.platform.kafka.api;//package com.icinfo.platform.kafka.api;
//
//import com.icinfo.platform.system.model.User;
//import kafka.javaapi.producer.Producer;
//import kafka.producer.KeyedMessage;
//import kafka.producer.ProducerConfig;
//
//import java.util.Properties;
//
///**
// * 生产者demo
// */
//public class KafkaSimpleProducer {
//    private static Producer<Integer, User> producer;
//    private final Properties props = new Properties();
//
//    public KafkaSimpleProducer() {
//        //定义连接的broker list
//        props.put("metadata.broker.list", "192.168.1.66:9092");
//        //定义序列化类（Java对象传输前要序列化）
//        props.put("serializer.class", "com.icinfo.platform.kafka.encoder.UserEncoder");
//        producer = new Producer<>(new ProducerConfig(props));
//    }
//
//    public static void main(String[] args) {
//        KafkaSimpleProducer sp = new KafkaSimpleProducer();
//        //定义topic
//        String topic = "topic001";
//        //定义要发送给topic的消息
//        String messageStr = "send a message 9 to broker ";
//        //构建消息对象
//        User user = new User();
//        user.setRealName("zmw");
//        user.setId("3");
//        user.setType("admin");
//        KeyedMessage<Integer, User> data = new KeyedMessage<>(topic, user);
//        //推送消息到broker
//        producer.send(data);
//        producer.close();
//    }
//}
