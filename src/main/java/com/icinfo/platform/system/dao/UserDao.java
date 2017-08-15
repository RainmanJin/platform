package com.icinfo.platform.system.dao;

import com.icinfo.platform.system.mapper.UserMapper;
import com.icinfo.platform.system.model.User;

import java.util.Map;

/**
 * 用户dao
 */
public interface UserDao extends UserMapper{
    User getUser(Map<String, Object> map) throws Exception;
}
