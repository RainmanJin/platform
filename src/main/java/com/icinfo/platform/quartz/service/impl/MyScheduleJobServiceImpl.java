package com.icinfo.platform.quartz.service.impl;

import com.dexcoder.commons.bean.BeanConverter;
import com.icinfo.platform.quartz.dao.MyScheduleJobDao;
import com.icinfo.platform.quartz.dto.MyScheduleJobDto;
import com.icinfo.platform.quartz.listener.MyJobListener;
import com.icinfo.platform.quartz.model.MyScheduleJob;
import com.icinfo.platform.quartz.service.IMyScheduleJobService;
import com.icinfo.platform.quartz.utils.MyScheduleUtils;
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
public class MyScheduleJobServiceImpl implements IMyScheduleJobService {

    /**
     * 调度工厂Bean
     */
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private MyScheduleJobDao myScheduleJobDao;

    public void initScheduleJob() throws Exception {
        List<MyScheduleJobDto> myScheduleJobDtoList = myScheduleJobDao.selectAll();
        if (CollectionUtils.isEmpty(myScheduleJobDtoList)) {
            return;
        }
        for (MyScheduleJobDto myScheduleJobDto : myScheduleJobDtoList) {

            CronTrigger cronTrigger = MyScheduleUtils.getCronTrigger(scheduler, myScheduleJobDto.getJobName(), myScheduleJobDto.getJobGroup());

            //不存在，创建一个
            if (cronTrigger == null) {
                MyScheduleUtils.createScheduleJob(scheduler, myScheduleJobDto);
            } else {
                //已存在，那么更新相应的定时设置
                MyScheduleUtils.updateScheduleJob(scheduler, myScheduleJobDto);
            }
        }
    }

    public int insert(MyScheduleJobDto myScheduleJobDto) {
        MyScheduleUtils.createScheduleJob(scheduler, myScheduleJobDto);
        return myScheduleJobDao.insert(myScheduleJobDto);
    }

    public void update(MyScheduleJobDto myScheduleJobDto) {
        MyScheduleUtils.updateScheduleJob(scheduler, myScheduleJobDto);
        myScheduleJobDao.updateByPrimaryKeySelective(myScheduleJobDto);
    }

    public void delUpdate(MyScheduleJobDto myScheduleJobDto) {
        //先删除
        MyScheduleUtils.deleteScheduleJob(scheduler, myScheduleJobDto.getJobName(), myScheduleJobDto.getJobGroup());
        //再创建
        MyScheduleUtils.createScheduleJob(scheduler, myScheduleJobDto);
        //数据库直接更新即可
        myScheduleJobDao.updateByPrimaryKeySelective(myScheduleJobDto);
    }

    public void delete(Long scheduleJobId) {
        MyScheduleJob myScheduleJob = myScheduleJobDao.selectByPrimaryKey(scheduleJobId);
        //删除运行的任务
        MyScheduleUtils.deleteScheduleJob(scheduler, myScheduleJob.getJobName(), myScheduleJob.getJobGroup());
        //删除数据
        myScheduleJobDao.deleteByPrimaryKey(scheduleJobId);
    }

    public void runOnce(Long scheduleJobId) {
        MyScheduleJob myScheduleJob = myScheduleJobDao.selectByPrimaryKey(scheduleJobId);
        MyScheduleUtils.runOnce(scheduler, myScheduleJob.getJobName(), myScheduleJob.getJobGroup());
    }

    public void pauseJob(Long scheduleJobId) {
        MyScheduleJob myScheduleJob = myScheduleJobDao.selectByPrimaryKey(scheduleJobId);
        MyScheduleUtils.pauseJob(scheduler, myScheduleJob.getJobName(), myScheduleJob.getJobGroup());
        //演示数据库就不更新了
    }

    public void resumeJob(Long scheduleJobId) {
        MyScheduleJob myScheduleJob = myScheduleJobDao.selectByPrimaryKey(scheduleJobId);
        MyScheduleUtils.resumeJob(scheduler, myScheduleJob.getJobName(), myScheduleJob.getJobGroup());
        //演示数据库就不更新了
    }

    public MyScheduleJob get(Long scheduleJobId) throws Exception {
        MyScheduleJob myScheduleJob = myScheduleJobDao.selectByPrimaryKey(scheduleJobId);
        //MyScheduleJobDto myScheduleJobDto = new MyScheduleJobDto();
        //BeanUtils.copyProperties(myScheduleJobDto, myScheduleJob);
        return myScheduleJob;
    }

    public List<MyScheduleJobDto> queryList(MyScheduleJobDto myScheduleJobDto) throws Exception {

        List<MyScheduleJobDto> myScheduleJobDtoList = myScheduleJobDao.selectAll();

        try {
            for (MyScheduleJobDto dto : myScheduleJobDtoList) {

                JobKey jobKey = MyScheduleUtils.getJobKey(dto.getJobName(), dto.getJobGroup());
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
        return myScheduleJobDtoList;
    }

    /**
     * 获取运行中的job列表
     *
     * @return
     */
    public List<MyScheduleJobDto> queryExecutingJobList() {
        try {
            // 存放结果集
            List<MyScheduleJobDto> jobList = new ArrayList<>();

            MyJobListener myJobListener = new MyJobListener();
            List<Matcher<JobKey>> matcherList = new ArrayList<>();

            // 获取scheduler中的JobGroupName
            for (String group : scheduler.getJobGroupNames()) {
                if(!group.equals("myscheduler")){
                    continue;
                }

                // 获取JobKey 循环遍历JobKey
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(group))) {
                    JobDetail jobDetail = scheduler.getJobDetail(jobKey);

                    //根据name和group 匹配一个job实例
                    matcherList.add(KeyMatcher.keyEquals(jobDetail.getKey()));

                    JobDataMap jobDataMap = jobDetail.getJobDataMap();
                    MyScheduleJob myScheduleJob = (MyScheduleJob) jobDataMap.get(MyScheduleJobDto.JOB_PARAM_KEY);
                    MyScheduleJobDto myScheduleJobDto = new MyScheduleJobDto();
                    BeanConverter.convert(myScheduleJobDto, myScheduleJob);
                    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                    Trigger trigger = triggers.iterator().next();
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    myScheduleJobDto.setJobTrigger(trigger.getKey().getName());
                    myScheduleJobDto.setStatus(triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        myScheduleJobDto.setCronExpression(cronExpression);
                    }
                    // 获取正常运行的任务列表
                    if (triggerState.name().equals("NORMAL")) {
                        jobList.add(myScheduleJobDto);
                    }
                }
            }

            //为job添加监听器
            scheduler.getListenerManager().addJobListener(myJobListener, matcherList);

            /** 非集群环境获取正在执行的任务列表 */
            /**
             List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
             List<MyScheduleJobDto> jobList = new ArrayList<MyScheduleJobDto>(executingJobs.size());
             for (JobExecutionContext executingJob : executingJobs) {
             MyScheduleJobDto job = new MyScheduleJobDto();
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
