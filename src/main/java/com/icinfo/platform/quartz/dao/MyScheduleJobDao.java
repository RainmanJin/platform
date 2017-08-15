package com.icinfo.platform.quartz.dao;

import com.icinfo.platform.quartz.dto.MyScheduleJobDto;
import com.icinfo.platform.quartz.mapper.MyScheduleJobMapper;

import java.util.List;

public interface MyScheduleJobDao extends MyScheduleJobMapper {
    List<MyScheduleJobDto> selectAll() throws Exception;
}