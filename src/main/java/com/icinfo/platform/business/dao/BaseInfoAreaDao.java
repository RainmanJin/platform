package com.icinfo.platform.business.dao;

import com.icinfo.platform.business.mapper.BaseInfoAreaMapper;
import com.icinfo.platform.business.model.BaseInfoArea;

import java.util.List;

/**
 * 地域Dao
 */
public interface BaseInfoAreaDao extends BaseInfoAreaMapper {
    /**
     * 查询所有地域
     *
     * @return 地域list
     * @throws Exception
     */
    List<BaseInfoArea> selectList() throws Exception;

    List<String> selectCodeList(List<String> list) throws Exception;
}
