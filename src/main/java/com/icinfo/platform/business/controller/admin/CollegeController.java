package com.icinfo.platform.business.controller.admin;

import com.icinfo.platform.business.model.College;
import com.icinfo.platform.business.service.IBaseInfoService;
import com.icinfo.platform.business.service.ICollegeService;
import com.icinfo.platform.common.bean.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 高校控制器
 */
@Controller("adminCollegeController")
@RequestMapping("/admin/college/")
public class CollegeController {
    @Autowired
    private ICollegeService collegeService;

    @Autowired
    private IBaseInfoService baseInfoService;

    /**
     * 进入列表页
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "tolist", method = RequestMethod.GET)
    public String toList() throws Exception{
        return "admin/business/collegeList";
    }

    /**
     * 进入新增/编辑页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "toaddoredit", method = RequestMethod.GET)
    public String toAddOrEdit() throws Exception{
        return "admin/business/collegeEdit";
    }

    /**
     * 根据地域内的所有高校
     * @param code 地域code
     * @return 地域内的所有高校
     * @throws Exception
     */
    @RequestMapping(value = "readlistbycode", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse<List<College>> readListByCode(@RequestParam(value = "code", required = true) String code) throws Exception{
        List<String> codeList = baseInfoService.getCodeList(code);
        return new AjaxResponse<>(collegeService.getList(codeList));
    }
}
