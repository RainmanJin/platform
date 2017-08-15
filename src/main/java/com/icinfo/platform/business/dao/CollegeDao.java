package com.icinfo.platform.business.dao;

import com.icinfo.platform.business.mapper.CollegeMapper;
import com.icinfo.platform.business.model.College;

import java.util.List;
import java.util.Map;

/**
 * 高校dao
 */
public interface CollegeDao extends CollegeMapper {
    /**
     * 查询高校list
     *
     * @param codeList 地域编码list
     * @return 高校list
     * @throws Exception
     */
    List<College> selectList(List<String> codeList) throws Exception;
}
