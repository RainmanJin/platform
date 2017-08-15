package com.icinfo.platform.quartz.factory;

import com.icinfo.platform.quartz.dto.MyScheduleJobDto;
import com.icinfo.platform.quartz.model.MyScheduleJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * author : fengjing
 * createTime : 2016-08-04
 * description : 异步任务工厂
 * version : 1.0
 */
public class MyAsyncJobFactory extends QuartzJobBean {

    /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(MyAsyncJobFactory.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        MyScheduleJob myScheduleJob = (MyScheduleJob) mergedJobDataMap.get(MyScheduleJobDto.JOB_PARAM_KEY);
        try {
            SchedulerContext schCtx = context.getScheduler().getContext();
            ApplicationContext appCtx = (ApplicationContext) schCtx.get("applicationContextKey");
            //Object bean = appCtx.getBean(Class.forName(myScheduleJob.getClassFullPath()));

            System.out.println("异步参数=====================" + myScheduleJob.getParams());

            if(myScheduleJob.getParams().equals("{\"a\":\"1\",\"b\":\"2\",\"c\":\"3\",\"d\":\"4\"}")){
                JobKey jobKey = JobKey.jobKey(myScheduleJob.getJobName(), myScheduleJob.getJobGroup());
                JobDetail jobDetail = context.getScheduler().getJobDetail(jobKey);
                myScheduleJob.setParams("{\"a\":\"1\",\"b\":\"2\",\"c\":\"3\",\"d\":\"4\",\"e\":\"5\"}");
                //放入参数，运行时的方法可以获取
                jobDetail.getJobDataMap().put(MyScheduleJobDto.JOB_PARAM_KEY, myScheduleJob);
                context.getScheduler().resumeJob(jobKey);
            }

            //Class clazz = Class.forName(myScheduleJob.getClassFullPath());
            //Method method = clazz.getDeclaredMethod(myScheduleJob.getMethodName(), new Class[]{String.class});
            //if (method == null) {
            //    return;
            //}
            //method.invoke(clazz.newInstance(), myScheduleJob.getParams());


            //MethodInvoker methodInvoker = new MethodInvoker();
            //methodInvoker.setTargetObject(bean);
            //methodInvoker.setTargetMethod(myScheduleJob.getMethodName());
            //System.out.println(myScheduleJob.getParams());
            //methodInvoker.prepare();
            //methodInvoker.invoke();
        } catch (Exception e) {
            System.out.println("------------------------:执行错误~");
            e.printStackTrace();
        }
    }
}
