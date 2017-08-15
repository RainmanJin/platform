package com.icinfo.platform.quartz.service;


import com.icinfo.platform.quartz.dto.MyScheduleJobDto;
import com.icinfo.platform.quartz.model.MyScheduleJob;

import java.util.List;


/**
 * author : fengjing
 * createTime : 2016-08-04
 * description : 定时任务服务
 * version : 1.0
 */
public interface IMyScheduleJobService {

    /**
     * 初始化定时任务
     */
    void initScheduleJob() throws Exception;

    /**
     * 新增
     *
     * @param myScheduleJobDto
     * @return
     */
    int insert(MyScheduleJobDto myScheduleJobDto);

    /**
     * 直接修改 只能修改运行的时间，参数、同异步等无法修改
     *
     * @param myScheduleJobDto
     */
    void update(MyScheduleJobDto myScheduleJobDto);

    /**
     * 删除重新创建方式
     *
     * @param myScheduleJobDto
     */
    void delUpdate(MyScheduleJobDto myScheduleJobDto);

    /**
     * 删除
     *
     * @param scheduleJobId
     */
    void delete(Long scheduleJobId);

    /**
     * 运行一次任务
     *
     * @param scheduleJobId the schedule job id
     * @return
     */
    void runOnce(Long scheduleJobId);

    /**
     * 暂停任务
     *
     * @param scheduleJobId the schedule job id
     * @return
     */
    void pauseJob(Long scheduleJobId);

    /**
     * 恢复任务
     *
     * @param scheduleJobId the schedule job id
     * @return
     */
    void resumeJob(Long scheduleJobId);

    /**
     * 获取任务对象
     *
     * @param scheduleJobId
     * @return
     */
    MyScheduleJob get(Long scheduleJobId) throws Exception;

    /**
     * 查询任务列表
     *
     * @param myScheduleJobDto
     * @return
     */
    List<MyScheduleJobDto> queryList(MyScheduleJobDto myScheduleJobDto) throws Exception;

    /**
     * 获取运行中的任务列表
     *
     * @return
     */
    List<MyScheduleJobDto> queryExecutingJobList();

}
