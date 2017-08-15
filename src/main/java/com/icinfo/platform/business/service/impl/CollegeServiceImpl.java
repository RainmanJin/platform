package com.icinfo.platform.business.service.impl;

import com.icinfo.platform.business.dao.BaseInfoAreaDao;
import com.icinfo.platform.business.dao.CollegeDao;
import com.icinfo.platform.business.model.College;
import com.icinfo.platform.business.service.ICollegeService;
import com.sun.xml.internal.xsom.impl.ListSimpleTypeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 高校服务接口实现
 */
@Service
public class CollegeServiceImpl implements ICollegeService {
    /**
     * 高校dao注入
     */
    @Autowired
    private CollegeDao collegeDao;

    /**
     * 获取高校list
     *
     * @param list 查询条件
     * @return 高校list
     * @throws Exception
     */
    @Override
    public List<College> getList(List<String> list) throws Exception {
        return collegeDao.selectList(list);
    }
}
