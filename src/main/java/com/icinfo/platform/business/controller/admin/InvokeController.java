package com.icinfo.platform.business.controller.admin;

import com.icinfo.platform.business.service.IInvokeService;
import com.icinfo.platform.common.bean.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/30.
 */
@Controller
@RequestMapping("/admin/invoke")
public class InvokeController {
    @Autowired
    private IInvokeService invokeService;

    /**
     * 反射算数
     * @param map 参数
     *            type 计算方式（add,sub）
     *            paramA 参数1
     *            paramB 参数2
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/process", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public AjaxResponse process(@RequestParam Map<String, Object> map) throws Exception {

        return new AjaxResponse(invokeService.doProcess(map));
    }
}
