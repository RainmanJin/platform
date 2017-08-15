package com.icinfo.platform.kafka.encoder;//package com.icinfo.platform.kafka.encoder;
//
//import com.icinfo.platform.common.util.ObjectAndByte;
//import kafka.serializer.Encoder;
//import kafka.utils.VerifiableProperties;
//
///**
// * 自定义序列化
// */
//public class UserEncoder implements Encoder<Object> {
//    private final String encoding;
//
//    public String encoding() {
//        return this.encoding;
//    }
//
//    @Override
//    public byte[] toBytes(Object obj) {
//        return ObjectAndByte.toByteArray(obj);
//    }
//
//    public UserEncoder(VerifiableProperties props) {
//        this.encoding = props == null ? "UTF8" : props.getString("serializer.encoding", "UTF8");
//    }
//}
