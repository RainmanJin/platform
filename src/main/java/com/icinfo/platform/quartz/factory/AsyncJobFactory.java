package com.icinfo.platform.quartz.factory;

import com.icinfo.platform.quartz.dto.ScheduleJobDto;
import com.icinfo.platform.quartz.model.ScheduleJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.MethodInvoker;

/**
 * author : fengjing
 * createTime : 2016-08-04
 * description : 异步任务工厂
 * version : 1.0
 */
public class AsyncJobFactory extends QuartzJobBean {

    /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(AsyncJobFactory.class);
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        ScheduleJob scheduleJob = (ScheduleJob) mergedJobDataMap.get(ScheduleJobDto.JOB_PARAM_KEY);
        try {
            SchedulerContext schCtx = context.getScheduler().getContext();
            ApplicationContext appCtx = (ApplicationContext) schCtx.get("applicationContextKey");
            Object bean = appCtx.getBean(Class.forName("com.icinfo.platform.quartz.service.impl.ScheduleJobServiceImpl"));
            MethodInvoker methodInvoker = new MethodInvoker();
            methodInvoker.setTargetObject(bean);
            methodInvoker.setTargetMethod("printTest");
            methodInvoker.prepare();
            methodInvoker.invoke();
        } catch (Exception e) {
            System.out.println("------------------------:执行错误~");
            e.printStackTrace();
        }
    }
}
