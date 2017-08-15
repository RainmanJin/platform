package com.icinfo.platform.system.service.impl;

import com.icinfo.platform.common.util.EncryptUtils;
import com.icinfo.platform.system.dao.UserDao;
import com.icinfo.platform.system.mapper.UserMapper;
import com.icinfo.platform.system.model.User;
import com.icinfo.platform.system.service.ILoginService;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录接口实现
 */
@Service
public class LoginServiceImpl implements ILoginService {
    /**
     * 日志记录器
     */
    public Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDao userDao;
    /**
     * 登录
     *
     * @throws Exception
     */
    @Override
    public User login(User user) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("loginName", user.getLoginName());
        User qUser = userDao.getUser(map);
        if(qUser!=null){
            String password = EncryptUtils.encryptPassword(user.getPassword(), qUser.getEncryptSalt());
            if(qUser.getPassword().equals(password)){
                return qUser;
            }
            return null;
        }
        return null;
    }
}
