package com.icinfo.platform.image.controller;

import com.icinfo.platform.common.test.SpringTxTestCase;
import com.icinfo.platform.image.controller.admin.ImageController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by Administrator on 2017/6/1.
 */
public class ImageControllerTest extends SpringTxTestCase {
    @Autowired
    private ImageController imageController;

    @Test
    public void testDelete() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/admin/image/delete/123")).andDo(MockMvcResultHandlers.print());
    }
}
