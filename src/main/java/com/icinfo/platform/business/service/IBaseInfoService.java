package com.icinfo.platform.business.service;

import com.icinfo.platform.business.model.BaseInfoArea;

import java.util.List;

/**
 * 基础信息服务接口
 */
public interface IBaseInfoService {
    /**
     * 获取地域信息列表
     *
     * @return 获取地域信息列表
     * @throws Exception
     */
    List<BaseInfoArea> getAreaList() throws Exception;

    List<String> getCodeList(String code) throws Exception;
}
