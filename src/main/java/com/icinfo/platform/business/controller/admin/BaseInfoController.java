package com.icinfo.platform.business.controller.admin;

import com.icinfo.platform.business.model.BaseInfoArea;
import com.icinfo.platform.business.service.IBaseInfoService;
import com.icinfo.platform.common.bean.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 基础信息控制器
 */
@Controller("adminBaseInfoController")
@RequestMapping("/admin/base/")
public class BaseInfoController {
    /**
     * 地域信息dao注入
     */
    @Autowired
    private IBaseInfoService baseInfoService;

    /**
     * 获取地域信息列表
     *
     * @return 地域信息列表
     * @throws Exception
     */
    @RequestMapping(value = "readarealist", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public AjaxResponse<List<BaseInfoArea>> readAreaList() throws Exception {
        List<BaseInfoArea> areaList = baseInfoService.getAreaList();
        return new AjaxResponse<>(areaList);
    }

    @RequestMapping(value = "verf", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public AjaxResponse<String> verf(@RequestParam(value = "id", required = true) String id) throws Exception {
        return new AjaxResponse<>(id);
    }

}
