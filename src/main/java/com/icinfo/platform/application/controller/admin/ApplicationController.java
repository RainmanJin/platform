package com.icinfo.platform.application.controller.admin;

import com.icinfo.platform.application.model.Application;
import com.icinfo.platform.application.service.IApplicationService;
import com.icinfo.platform.oss.api.OssApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 应用管理控制器
 */
@Controller("adminApplicationController")
@RequestMapping("/admin/application/")
public class ApplicationController {
    /**
     * 应用管理服务注入
     */
    @Autowired
    private IApplicationService applicationService;

    @Autowired
    private OssApi ossApi;

    /**
     * 进入应用管理列表页面
     *
     * @param model 模型
     * @return 应用管理列表页面
     * @throws Exception
     */
    @RequestMapping(value = "tolist", method = RequestMethod.GET)
    public String toList(Model model) throws Exception {
        model.addAttribute("list", applicationService.selectList());
        return "admin/application/list";
    }

    /**
     * 进入应用新增页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "toadd", method = RequestMethod.GET)
    public String toAdd() throws Exception {
        return "admin/application/edit";
    }

    /**
     * 新增应用
     *
     * @param application 应用
     * @param iconFile 图片
     * @return 列表页面
     * @throws Exception
     */
    @RequestMapping(value = "addoredit", method = RequestMethod.POST)
    public String addOrEdit(Application application, @RequestParam(value = "iconUrl", required = true) CommonsMultipartFile iconFile) throws Exception {

        applicationService.insertApp(application, iconFile);
        return "redirect:tolist";
    }
}
