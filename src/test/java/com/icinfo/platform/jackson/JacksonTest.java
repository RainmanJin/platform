package com.icinfo.platform.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icinfo.platform.system.model.User;

import java.io.IOException;

/**
 * TODO
 */
public class JacksonTest {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"id\":\"1\",\"loginName\":\"zhangmw\",\"password\":\"123456\"," +
                "\"realName\":\"Mahesh Kumar\", \"sex\":\"女\", \"type\":\"admin\",\"status\": \"1\"}";
        JsonNode jsonNode = mapper.readTree(jsonString);
        System.out.println(jsonNode.size());

        User user = mapper.treeToValue(jsonNode, User.class);
        System.out.println(user.toString());

    }
}
