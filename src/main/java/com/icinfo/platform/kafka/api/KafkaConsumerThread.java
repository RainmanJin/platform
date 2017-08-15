package com.icinfo.platform.kafka.api;//package com.icinfo.platform.kafka.api;
//
//import kafka.consumer.ConsumerIterator;
//import kafka.consumer.KafkaStream;
//import kafka.message.MessageAndMetadata;
//
///**
// * TODO
// */
//public class KafkaConsumerThread implements Runnable {
//
//    private KafkaStream<byte[], byte[]> stream;
//
//    public KafkaConsumerThread(KafkaStream<byte[], byte[]> stream) {
//        this.stream = stream;
//    }
//
//    @Override
//    public void run() {
//        ConsumerIterator<byte[], byte[]> it = stream.iterator();
//        while (it.hasNext()) {
//            MessageAndMetadata<byte[], byte[]> mam = it.next();
//            System.out.println(Thread.currentThread().getName() + ": partition[" + mam.partition() + "],"
//                    + "offset[" + mam.offset() + "], " + new String(mam.message()));
//
//        }
//    }
//
//}