package com.icinfo.platform.quartz.dao;

import com.icinfo.platform.quartz.dto.ScheduleJobDto;
import com.icinfo.platform.quartz.mapper.ScheduleJobMapper;

import java.util.List;

public interface ScheduleJobDao extends ScheduleJobMapper {
    List<ScheduleJobDto> selectAll() throws Exception;
}