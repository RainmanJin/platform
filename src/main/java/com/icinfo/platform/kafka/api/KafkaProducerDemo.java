package com.icinfo.platform.kafka.api;//package com.icinfo.platform.kafka.api;
//
//import kafka.javaapi.producer.Producer;
//import kafka.producer.KeyedMessage;
//import kafka.producer.ProducerConfig;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
///**
// * 多线程消费 批量发送消息
// */
//public class KafkaProducerDemo implements Runnable {
//
//    private Producer<String, String> producer = null;
//
//    private ProducerConfig config = null;
//
//
//    public KafkaProducerDemo() {
//        Properties props = new Properties();
//        //props.put("zookeeper.connect", "192.168.5.133:2181");
//
//        // 指定序列化处理类，默认为kafka.serializer.DefaultEncoder,即byte[]
//        props.put("serializer.class", "kafka.serializer.StringEncoder");
//
//        // 同步还是异步，默认2表同步，1表异步。异步可以提高发送吞吐量，但是也可能导致丢失未发送过去的消息
//        //props.put("producer.type", "sync");
//
//        // 是否压缩，默认0表示不压缩，1表示用gzip压缩，2表示用snappy压缩。压缩后消息中会有头来指明消息压缩类型，故在消费者端消息解压是透明的无需指定。
//        //props.put("compression.codec", "1");
//
//        // 指定kafka节点列表，用于获取metadata(元数据)，不必全部指定
//        props.put("metadata.broker.list", "192.168.5.133:9040");
//
//        config = new ProducerConfig(props);
//    }
//
//    @Override
//    public void run() {
//        producer = new Producer<>(config);
//        for (int i = 1; i <= 4; i++) { //往6个分区发数据
//            List<KeyedMessage<String, String>> messageList = new ArrayList<>();
//            for (int j = 0; j < 2; j++) { //每个分区j条讯息
//                messageList.add(new KeyedMessage<>
//                        //String topic, String partition, String message
//                        ("test003", "partition[" + i + "]", "message[The " + i + " message]"));
//            }
//            producer.send(messageList);
//        }
//
//    }
//
//    public static void main(String[] args) {
//        Thread t = new Thread(new KafkaProducerDemo());
//        t.start();
//    }
//}
