package com.icinfo.platform.application.dao;

import com.icinfo.platform.application.mapper.ApplicationMapper;
import com.icinfo.platform.application.model.Application;

import java.util.List;

/**
 * 应用管理dao
 */
public interface ApplicationDao extends ApplicationMapper {
    List<Application> selectList() throws Exception;
}
