package com.icinfo.platform.kafka.api;//package com.icinfo.platform.kafka.api;
//
//import kafka.consumer.Consumer;
//import kafka.consumer.ConsumerConfig;
//import kafka.consumer.ConsumerIterator;
//import kafka.consumer.KafkaStream;
//import kafka.javaapi.consumer.ConsumerConnector;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * 消费者示例 high-level Consumer API
// */
//public class ConsumerDemo extends Thread {
//    // 消费者连接
//    private final ConsumerConnector connector;
//    // 要消费的话题
//    private final String topic;
//
//    /**
//     * 描述：创建消费连接
//     *
//     * @param topic 主题
//     */
//    public ConsumerDemo(String topic) throws IOException {
//        //1,.获取消费者配置文件配置项
//        Properties properties = new Properties();
//        properties.load(new FileInputStream(new File("src/main/resources/entry/kafka-consumer.properties")));
//        //2.将文件配置存入消费者配置类中，并创建一个消费者连接
//        connector = Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
//        this.topic = topic;
//    }
//
//    /**
//     * 运行线程
//     */
//    public void run() {
//        //1.设置话题
//        Map<String, Integer> topicMap = new HashMap<>();
//        topicMap.put(topic, new Integer(4));
//
//        //2.创建消息流
//        Map<String, List<KafkaStream<byte[], byte[]>>> streamMap = connector.createMessageStreams(topicMap);
//
//        //3.获取对应话题流
//        List<KafkaStream<byte[], byte[]>> streamList = streamMap.get(topic);
//
//        //4.流中获取消费者迭代器
//        for (KafkaStream stream : streamList) {
//            ConsumerIterator<byte[], byte[]> it = stream.iterator();
//            while (it.hasNext()) {
//                // 打印消费者接受的信息
//                System.out.println("消息消费 :: patition=" + it.next().partition() + "    Value = " + new String(it.next().message()));
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        //1.设置话题，并创建消费者的连接
//        ConsumerDemo consumerThread = new ConsumerDemo("test003");
//        //2.运行消费者线程
//        consumerThread.run();
//    }
//}
