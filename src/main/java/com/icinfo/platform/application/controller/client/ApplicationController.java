package com.icinfo.platform.application.controller.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icinfo.platform.application.model.Application;
import com.icinfo.platform.common.annotation.IgnoreSecurity;
import com.icinfo.platform.common.bean.AjaxResult;
import com.icinfo.platform.common.util.HttpUtils;
import com.icinfo.platform.common.util.JSONUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 */
@Controller("clientApplicationController")
@RequestMapping("/client/application/")
public class ApplicationController {
    /**
     * 进入应用列表展示页面
     *
     * @param model 模型
     * @return 应用列表展示页面
     * @throws Exception
     */
    @RequestMapping(value = "toshowlist", method = RequestMethod.GET)
    @IgnoreSecurity
    public String toShowList(Model model) throws Exception {
        String result = HttpUtils.sendGet("http://192.168.5.50:8082/client/application/readlist", "identification=LLC_APP_01");
        ObjectMapper mapper = new ObjectMapper();
        Object data = JSONUtils.parse(result, AjaxResult.class).getData().toString();
        model.addAttribute("list", mapper.readValue(result, Map.class).get("data"));
        return "admin/application/showlist";
    }
}
