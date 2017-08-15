package com.icinfo.platform.system.service;

import com.icinfo.platform.system.model.User;

/**
 * 用户服务接口
 */
public interface IUserService {
    /**
     * 保存用户
     *
     * @param user 用户信息
     * @throws Exception
     */
    void save(User user) throws Exception;

    User getUser(String loginName) throws Exception;
}
