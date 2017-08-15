package com.icinfo.platform.business.service.impl;

import com.icinfo.platform.business.dao.BaseInfoAreaDao;
import com.icinfo.platform.business.model.BaseInfoArea;
import com.icinfo.platform.business.service.IBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础信息服务接口实现
 */
@Service
public class BaseInfoServiceImpl implements IBaseInfoService {
    /**
     * 地域信息dao注入
     */
    @Autowired
    private BaseInfoAreaDao baseInfoAreaDao;

    /**
     * 获取地域信息列表
     *
     * @return 获取地域信息列表
     * @throws Exception
     */
    @Override
    public List<BaseInfoArea> getAreaList() throws Exception {
        return baseInfoAreaDao.selectList();
    }

    @Override
    public List<String> getCodeList(String code) throws Exception {
        List<String> codeList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add(code);
        codeList.addAll(list);
        while (list.size() > 0) {
            list = baseInfoAreaDao.selectCodeList(list);
            codeList.addAll(list);
        }
        return codeList;
    }
}
