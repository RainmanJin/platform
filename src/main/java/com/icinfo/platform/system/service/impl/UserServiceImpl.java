package com.icinfo.platform.system.service.impl;

import com.icinfo.platform.common.util.EncryptUtils;
import com.icinfo.platform.system.dao.UserDao;
import com.icinfo.platform.system.mapper.UserMapper;
import com.icinfo.platform.system.model.User;
import com.icinfo.platform.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户服务接口实现
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDao userDao;
    /**
     * 保存用户
     *
     * @param user 用户信息
     * @throws Exception
     */
    @Override
    public void save(User user) throws Exception {
        EncryptUtils.encryptPassword(user);
        user.setCreateTime(new Date());
        user.setOperateTime(new Date());
        user.setStatus("0");
        user.setType("1");
        userMapper.insert(user);
    }

    @Override
    public User getUser(String loginName) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("loginName", loginName);
        return userDao.getUser(map);
    }
}
