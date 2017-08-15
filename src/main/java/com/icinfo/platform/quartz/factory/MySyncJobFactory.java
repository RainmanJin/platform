package com.icinfo.platform.quartz.factory;

import com.icinfo.platform.quartz.dto.MyScheduleJobDto;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * author : fengjing
 * createTime : 2016-08-04
 * description : 同步任务工厂
 * version : 1.0
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class MySyncJobFactory extends QuartzJobBean {

    /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(MySyncJobFactory.class);

    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        LOG.info("SyncJobFactory execute");
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        MyScheduleJobDto myScheduleJobDto = (MyScheduleJobDto) mergedJobDataMap.get(MyScheduleJobDto.JOB_PARAM_KEY);
        System.out.println("jobName:" + myScheduleJobDto.getJobName() + "  " + myScheduleJobDto);
        System.out.println("同步参数=====================" + myScheduleJobDto.getParams());
        try {
            if (myScheduleJobDto.getParams().equals("{\"a\":\"1\",\"b\":\"2\",\"c\":\"3\",\"d\":\"4\"}")) {
                JobKey jobKey = JobKey.jobKey(myScheduleJobDto.getJobName(), myScheduleJobDto.getJobGroup());
                JobDetail jobDetail = context.getScheduler().getJobDetail(jobKey);
                myScheduleJobDto.setParams("{\"a\":\"1\",\"b\":\"2\",\"c\":\"3\",\"d\":\"4\",\"e\":\"5\"}");
                //放入参数，运行时的方法可以获取
                jobDetail.getJobDataMap().put(MyScheduleJobDto.JOB_PARAM_KEY, myScheduleJobDto);
                context.getScheduler().resumeJob(jobKey);
            }
        } catch (Exception e) {
            System.out.println("------------------------:执行错误~");
            e.printStackTrace();
        }

        //String url = scheduleJob.getUrl();
        //CloseableHttpClient httpclient = HttpClients.createDefault();
        //HttpGet httpGet = new HttpGet(url);
        //CloseableHttpResponse response;
        //try {
        //    response = httpclient.execute(httpGet);
        //    System.out.println(response.getStatusLine());
        //    HttpEntity entity = response.getEntity();
        //    EntityUtils.consume(entity);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
    }
}
