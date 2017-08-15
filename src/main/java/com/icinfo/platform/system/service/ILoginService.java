package com.icinfo.platform.system.service;

import com.icinfo.platform.system.model.User;

/**
 * 登录接口
 */
public interface ILoginService {
    User login(User user) throws Exception;
}
