package com.icinfo.platform.quartz.service.impl;

import com.dexcoder.commons.bean.BeanConverter;
import com.icinfo.platform.quartz.dao.ScheduleJobDao;
import com.icinfo.platform.quartz.dto.ScheduleJobDto;
import com.icinfo.platform.quartz.listener.MyJobListener;
import com.icinfo.platform.quartz.model.ScheduleJob;
import com.icinfo.platform.quartz.service.IScheduleJobService;
import com.icinfo.platform.quartz.utils.ScheduleUtils;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.KeyMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fengjing
 * createTime : 2016-08-04
 * description : 定时任务服务实现
 * version : 1.0
 */
@Service
public class ScheduleJobServiceImpl implements IScheduleJobService {

    /**
     * 调度工厂Bean
     */
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleJobDao scheduleJobDao;
    
    public void initScheduleJob() throws Exception {
        List<ScheduleJobDto> scheduleJobList = scheduleJobDao.selectAll();
        if (CollectionUtils.isEmpty(scheduleJobList)) {
            return;
        }
        for (ScheduleJob scheduleJob : scheduleJobList) {

            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());

            //不存在，创建一个
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                //已存在，那么更新相应的定时设置
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    public int insert(ScheduleJobDto scheduleJobDto) {
        ScheduleUtils.createScheduleJob(scheduler, scheduleJobDto);
        return scheduleJobDao.insert(scheduleJobDto);
    }

    public void update(ScheduleJobDto scheduleJobDto) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJobDto);
        scheduleJobDao.updateByPrimaryKeySelective(scheduleJobDto);
    }

    public void delUpdate(ScheduleJobDto scheduleJobDto) {
        //先删除
        ScheduleUtils.deleteScheduleJob(scheduler, scheduleJobDto.getJobName(), scheduleJobDto.getJobGroup());
        //再创建
        ScheduleUtils.createScheduleJob(scheduler, scheduleJobDto);
        //数据库直接更新即可
        scheduleJobDao.updateByPrimaryKeySelective(scheduleJobDto);
    }

    public void delete(Long scheduleJobId) {
        ScheduleJob scheduleJob = scheduleJobDao.selectByPrimaryKey(scheduleJobId);
        //删除运行的任务
        ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
        //删除数据
        scheduleJobDao.deleteByPrimaryKey(scheduleJobId);
    }

    public void runOnce(Long scheduleJobId) {
        ScheduleJob scheduleJob = scheduleJobDao.selectByPrimaryKey(scheduleJobId);
        ScheduleUtils.runOnce(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
    }

    public void pauseJob(Long scheduleJobId) {
        ScheduleJob scheduleJob = scheduleJobDao.selectByPrimaryKey(scheduleJobId);
        ScheduleUtils.pauseJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
        //演示数据库就不更新了
    }

    public void resumeJob(Long scheduleJobId) {
        ScheduleJob scheduleJob = scheduleJobDao.selectByPrimaryKey(scheduleJobId);
        ScheduleUtils.resumeJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
        //演示数据库就不更新了
    }

    public ScheduleJob get(Long scheduleJobId) throws Exception {
        ScheduleJob scheduleJob = scheduleJobDao.selectByPrimaryKey(scheduleJobId);
        //ScheduleJobDto scheduleJobDto = new ScheduleJobDto();
        //BeanUtils.copyProperties(scheduleJobDto, scheduleJob);
        return scheduleJob;
    }

    public List<ScheduleJobDto> queryList(ScheduleJobDto scheduleJobDto) throws Exception {

        List<ScheduleJobDto> scheduleJobDtoList = scheduleJobDao.selectAll();

        try {
            for (ScheduleJobDto dto : scheduleJobDtoList) {

                JobKey jobKey = ScheduleUtils.getJobKey(dto.getJobName(), dto.getJobGroup());
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                if (CollectionUtils.isEmpty(triggers)) {
                    continue;
                }

                //这里一个任务可以有多个触发器， 但是我们一个任务对应一个触发器，所以只取第一个即可，清晰明了
                Trigger trigger = triggers.iterator().next();
                dto.setJobTrigger(trigger.getKey().getName());

                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                dto.setStatus(triggerState.name());

                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    dto.setCronExpression(cronExpression);
                }
            }
        } catch (SchedulerException e) {
            //演示用，就不处理了
        }
        return scheduleJobDtoList;
    }

    /**
     * 获取运行中的job列表
     *
     * @return
     */
    public List<ScheduleJobDto> queryExecutingJobList() {
        try {
            // 存放结果集
            List<ScheduleJobDto> jobList = new ArrayList<>();

            MyJobListener myJobListener = new MyJobListener();
            List<Matcher<JobKey>> matcherList = new ArrayList<>();

            // 获取scheduler中的JobGroupName
            for (String group : scheduler.getJobGroupNames()) {
                if(!group.equals("scheduler")){
                    continue;
                }
                // 获取JobKey 循环遍历JobKey
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(group))) {
                    JobDetail jobDetail = scheduler.getJobDetail(jobKey);

                    //根据name和group 匹配一个job实例
                    matcherList.add(KeyMatcher.keyEquals(jobDetail.getKey()));

                    JobDataMap jobDataMap = jobDetail.getJobDataMap();
                    ScheduleJob scheduleJob = (ScheduleJob) jobDataMap.get(ScheduleJobDto.JOB_PARAM_KEY);
                    ScheduleJobDto scheduleJobDto = new ScheduleJobDto();
                    BeanConverter.convert(scheduleJobDto, scheduleJob);
                    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                    Trigger trigger = triggers.iterator().next();
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    scheduleJobDto.setJobTrigger(trigger.getKey().getName());
                    scheduleJobDto.setStatus(triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        scheduleJobDto.setCronExpression(cronExpression);
                    }
                    // 获取正常运行的任务列表
                    if (triggerState.name().equals("NORMAL")) {
                        jobList.add(scheduleJobDto);
                    }
                }
            }

            //为job添加监听器
            scheduler.getListenerManager().addJobListener(myJobListener, matcherList);

            /** 非集群环境获取正在执行的任务列表 */
            /**
             List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
             List<ScheduleJobDto> jobList = new ArrayList<ScheduleJobDto>(executingJobs.size());
             for (JobExecutionContext executingJob : executingJobs) {
             ScheduleJobDto job = new ScheduleJobDto();
             JobDetail jobDetail = executingJob.getJobDetail();
             JobKey jobKey = jobDetail.getKey();
             Trigger trigger = executingJob.getTrigger();
             job.setJobName(jobKey.getName());
             job.setJobGroup(jobKey.getGroup());
             job.setJobTrigger(trigger.getKey().getName());
             Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
             job.setStatus(triggerState.name());
             if (trigger instanceof CronTrigger) {
             CronTrigger cronTrigger = (CronTrigger) trigger;
             String cronExpression = cronTrigger.getCronExpression();
             job.setCronExpression(cronExpression);
             }
             jobList.add(job);
             }*/

            return jobList;
        } catch (SchedulerException e) {
            return null;
        }
    }
}
