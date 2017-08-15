package com.icinfo.platform.quartz.controller;

import com.icinfo.platform.quartz.dto.MyScheduleJobDto;
import com.icinfo.platform.quartz.model.MyScheduleJob;
import com.icinfo.platform.quartz.service.IMyScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


/**
 * author : fengjing
 * createTime : 2016-08-04
 * description : 定时任务控制器
 * version : 1.0
 */
@Controller
@RequestMapping("/myschedulejob/")
public class MyScheduleJobController {

    /**
     * job service
     */
    @Autowired
    private IMyScheduleJobService myScheduleJobService;

    /**
     * 任务页面
     *
     * @return
     */
    @RequestMapping(value = "input-schedule-job", method = RequestMethod.GET)
    public String inputScheduleJob(MyScheduleJobDto myScheduleJobDto, ModelMap modelMap) throws Exception {

        if (myScheduleJobDto.getScheduleJobId() != null) {
            MyScheduleJob myScheduleJob = myScheduleJobService.get(myScheduleJobDto.getScheduleJobId());
            //scheduleJob.setKeywords(myScheduleJobDto.getKeywords());
            modelMap.put("myScheduleJobDto", myScheduleJob);
        }

        return "quartz/my-input-schedule-job";
    }

    /**
     * 删除任务
     *
     * @return
     */
    @RequestMapping(value = "delete-schedule-job", method = RequestMethod.GET)
    public String deleteScheduleJob(Long scheduleJobId) {

        myScheduleJobService.delete(scheduleJobId);

        return "redirect:list-schedule-job";
    }

    /**
     * 运行一次
     *
     * @return
     */
    @RequestMapping(value = "run-once-schedule-job", method = RequestMethod.GET)
    public String runOnceScheduleJob(Long scheduleJobId) {

        myScheduleJobService.runOnce(scheduleJobId);

        return "redirect:list-schedule-job";
    }

    /**
     * 暂停
     *
     * @return
     */
    @RequestMapping(value = "pause-schedule-job", method = RequestMethod.GET)
    public String pauseScheduleJob(Long scheduleJobId) {
        myScheduleJobService.pauseJob(scheduleJobId);
        return "redirect:list-schedule-job";
    }

    /**
     * 恢复
     *
     * @return
     */
    @RequestMapping(value = "resume-schedule-job", method = RequestMethod.GET)
    public String resumeScheduleJob(Long scheduleJobId) {
        myScheduleJobService.resumeJob(scheduleJobId);
        return "redirect:list-schedule-job";
    }

    /**
     * 保存任务
     *
     * @param myScheduleJobDto
     * @return
     */
    @RequestMapping(value = "save-schedule-job", method = RequestMethod.POST)
    public String saveScheduleJob(MyScheduleJobDto myScheduleJobDto) {

        //测试用随便设个状态
        myScheduleJobDto.setStatus("1");

        if (myScheduleJobDto.getScheduleJobId() == null) {
            myScheduleJobService.insert(myScheduleJobDto);
        } else {
            myScheduleJobService.update(myScheduleJobDto);
        }
        //else if (StringUtils.equalsIgnoreCase(myScheduleJobDto.getKeywords(),"delUpdate")){
        //    //直接拿keywords存一下，就不另外重新弄了
        //    scheduleJobService.delUpdate(myScheduleJobDto);
        //}
        return "redirect:list-schedule-job";
    }

    /**
     * 任务列表页
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "list-schedule-job", method = RequestMethod.GET)
    public String listScheduleJob(ModelMap modelMap) throws Exception {
        MyScheduleJobDto myScheduleJobDto = new MyScheduleJobDto();
        List<MyScheduleJobDto> myScheduleJobDtoList = myScheduleJobService.queryList(myScheduleJobDto);
        modelMap.put("myScheduleJobDtoList", myScheduleJobDtoList);

        List<MyScheduleJobDto> executingJobList = myScheduleJobService.queryExecutingJobList();
        modelMap.put("executingJobList", executingJobList);

        return "quartz/my-list-schedule-job";
    }

}
