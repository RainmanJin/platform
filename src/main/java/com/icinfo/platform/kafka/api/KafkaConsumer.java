package com.icinfo.platform.kafka.api;//package com.icinfo.platform.kafka.api;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.listener.MessageListener;
//
///**
// * 消息消费
// */
//public class KafkaConsumer implements MessageListener<Integer, String>{
//    /**
//     * 消费信息
//     *
//     * @param record
//     */
//    @Override
//    public void onMessage(ConsumerRecord<Integer, String> record) {
//        System.out.println(record);
//    }
//}
