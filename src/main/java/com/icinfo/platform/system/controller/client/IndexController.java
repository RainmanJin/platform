package com.icinfo.platform.system.controller.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icinfo.platform.common.bean.AjaxResult;
import com.icinfo.platform.common.util.JSONUtils;
import com.icinfo.platform.wechat.wxsdk.common.client.HttpClientExecutor;
import com.icinfo.platform.wechat.wxsdk.templete.bean.Template;
import com.icinfo.platform.wechat.wxsdk.templete.bean.TemplateDate;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页控制器
 */
@Controller("clientIndexController")
public class IndexController {
    /**
     * 跳转到未登录首页
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() throws Exception {
        return "index";
    }

    /**
     * 跳转到登陆后首页
     *
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() throws Exception {
        return "client/home";
    }

    @RequestMapping(value = "/testsend", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult testSend() throws Exception {
        StringEntity entity = new StringEntity(JSONUtils.parse(createTemplete()), "utf-8");
        entity.setContentType("application/json");
        HttpUriRequest request = RequestBuilder.post()
                .setUri("http://192.168.5.58:8082/template/send")
                .setEntity(entity)
                .addParameter("openId", "olnbTw-HrfZdQvj-qqvwnvib5wg4")
                .build();
        //HttpClient.executeJsonResult(request, null, AjaxResult.class);
        HttpClientExecutor.executeJsonResult(request, AjaxResult.class);
        return AjaxResult.success("success");
    }

    private Template createTemplete() throws Exception{
        Template template = new Template();
        template.setTouser("olnbTw-HrfZdQvj-qqvwnvib5wg4");
        template.setTemplate_id("nX5STATIZJTyg03D74EDtiLqoOWY67cq8nB3rbB3xqM");
        template.setUrl("http://4519.ngrok.cc/");

        TemplateDate title = new TemplateDate("衢州市年报培训通知", "#173177");
        TemplateDate createTime = new TemplateDate("2017-04-18 17：30", "#173177");
        TemplateDate companyName = new TemplateDate("• 浙江省嘉兴市建安广告策划有限公司", "#173177");
        TemplateDate summary = new TemplateDate("关于举办年报工作培训班的通知\n" +
                "各市、县（区）工商局（市场监管局），平潭综合实验区市场监管局 ......", "#173177");

        Map<String, TemplateDate> map = new HashMap<>();
        map.put("title", title);
        map.put("createTime", createTime);
        map.put("companyName", companyName);
        map.put("summary", summary);
        template.setData(map);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(template));
        return template;
    }
}
