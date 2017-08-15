package com.icinfo.platform.system.controller.client;

import com.icinfo.platform.common.bean.AjaxResponse;
import com.icinfo.platform.system.model.User;
import com.icinfo.platform.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户控制器
 */
@Controller("clientUserController")
@RequestMapping("/client/user")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 注册新用户
     *
     * @param user 用户信息
     * @return 注册结果
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public AjaxResponse<Boolean> save(User user) throws Exception {
        if (userService.getUser(user.getLoginName()) == null) {
            userService.save(user);
            return new AjaxResponse<>(true);
        }
        return new AjaxResponse<>("1","用户名不允许重复");
    }
}
