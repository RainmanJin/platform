package com.icinfo.platform.kafka.api;//package com.icinfo.platform.kafka.api;
//
//import kafka.consumer.ConsumerConfig;
//import kafka.consumer.KafkaStream;
//import kafka.javaapi.consumer.ConsumerConnector;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * 多线程消费
// */
//public class KafkaConsumerDemo implements Runnable {
//
//    private ConsumerConfig consumerConfig;
//    private static String topic = "test003";
//    Properties props;
//    final int a_numThreads = 2;
//
//    public KafkaConsumerDemo() {
//        props = new Properties();
//        props.put("zookeeper.connect", "192.168.5.133:2181");
//        props.put("group.id", "zhang001");
//        props.put("zookeeper.session.timeout.ms", "400");
//        props.put("zookeeper.sync.time.ms", "200");
//        props.put("auto.commit.interval.ms", "1000");
//        props.put("auto.offset.reset", "smallest");
//        consumerConfig = new ConsumerConfig(props);
//    }
//
//
//    @Override
//    public void run() {
//
//        Map<String, Integer> topicCountMap = new HashMap<>();
//        topicCountMap.put(topic, new Integer(a_numThreads));
//        ConsumerConfig consumerConfig = new ConsumerConfig(props);
//        ConsumerConnector consumer = kafka.consumer.Consumer.createJavaConsumerConnector(consumerConfig);
//        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
//        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
//        ExecutorService executor = Executors.newFixedThreadPool(a_numThreads);
//        for (final KafkaStream stream : streams) {
//            executor.submit(new KafkaConsumerThread(stream));
//        }
//
//    }
//
//
//    public static void main(String[] args) {
//        System.out.println(topic);
//        Thread t = new Thread(new KafkaConsumerDemo());
//        t.start();
//    }
//}