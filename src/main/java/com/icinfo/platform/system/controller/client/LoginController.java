package com.icinfo.platform.system.controller.client;

import com.icinfo.platform.common.bean.AjaxResponse;
import com.icinfo.platform.common.constant.Constant;
import com.icinfo.platform.system.model.User;
import com.icinfo.platform.system.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 登录控制器
 */
@Controller("clientLoginController")
@RequestMapping("/client")
@SessionAttributes(Constant.SESSION_USER_INFO)
public class LoginController {
    @Autowired
    private ILoginService loginService;

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping(value = "/tologin", method = RequestMethod.GET)
    public String toLogin() throws Exception {
        return "client/system/login";
    }

    /**
     * 登录
     *
     * @param user 用户信息
     * @return 登录结果
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> login(User user, Model model) throws Exception {
        User qUser = loginService.login(user);
        if (qUser != null) {
            model.addAttribute(Constant.SESSION_USER_INFO, qUser);
            return new AjaxResponse<>(true);
        }
        return new AjaxResponse<>(false);
    }

    /**
     * 进入注册页面
     *
     * @return 注册页面
     * @throws Exception
     */
    @RequestMapping(value = "toregister", method = RequestMethod.GET)
    public String toRegister() throws Exception {
        return "client/system/register";
    }

}
