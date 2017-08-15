package com.icinfo.platform.quartz.controller;

import com.icinfo.platform.quartz.dto.ScheduleJobDto;
import com.icinfo.platform.quartz.model.ScheduleJob;
import com.icinfo.platform.quartz.service.IScheduleJobService;
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
@RequestMapping("/schedulejob/")
public class ScheduleJobController {

    /**
     * job service
     */
    @Autowired
    private IScheduleJobService scheduleJobService;

    /**
     * 任务页面
     *
     * @return
     */
    @RequestMapping(value = "input-schedule-job", method = RequestMethod.GET)
    public String inputScheduleJob(ScheduleJobDto scheduleJobDto, ModelMap modelMap) throws Exception {

        if (scheduleJobDto.getScheduleJobId() != null) {
            ScheduleJob scheduleJob = scheduleJobService.get(scheduleJobDto.getScheduleJobId());
            //scheduleJob.setKeywords(scheduleJobDto.getKeywords());
            modelMap.put("scheduleJobDto", scheduleJob);
        }

        return "quartz/input-schedule-job";
    }

    /**
     * 删除任务
     *
     * @return
     */
    @RequestMapping(value = "delete-schedule-job", method = RequestMethod.GET)
    public String deleteScheduleJob(Long scheduleJobId) {

        scheduleJobService.delete(scheduleJobId);

        return "redirect:list-schedule-job";
    }

    /**
     * 运行一次
     *
     * @return
     */
    @RequestMapping(value = "run-once-schedule-job", method = RequestMethod.GET)
    public String runOnceScheduleJob(Long scheduleJobId) {

        scheduleJobService.runOnce(scheduleJobId);

        return "redirect:list-schedule-job";
    }

    /**
     * 暂停
     *
     * @return
     */
    @RequestMapping(value = "pause-schedule-job", method = RequestMethod.GET)
    public String pauseScheduleJob(Long scheduleJobId) {
        scheduleJobService.pauseJob(scheduleJobId);
        return "redirect:list-schedule-job";
    }

    /**
     * 恢复
     *
     * @return
     */
    @RequestMapping(value = "resume-schedule-job", method = RequestMethod.GET)
    public String resumeScheduleJob(Long scheduleJobId) {
        scheduleJobService.resumeJob(scheduleJobId);
        return "redirect:list-schedule-job";
    }

    /**
     * 保存任务
     *
     * @param scheduleJobDto
     * @return
     */
    @RequestMapping(value = "save-schedule-job", method = RequestMethod.POST)
    public String saveScheduleJob(ScheduleJobDto scheduleJobDto) {

        //测试用随便设个状态
        scheduleJobDto.setStatus("1");

        if (scheduleJobDto.getScheduleJobId() == null) {
            scheduleJobService.insert(scheduleJobDto);
        } else {
            scheduleJobService.update(scheduleJobDto);
        }
        //else if (StringUtils.equalsIgnoreCase(scheduleJobDto.getKeywords(),"delUpdate")){
        //    //直接拿keywords存一下，就不另外重新弄了
        //    scheduleJobService.delUpdate(scheduleJobDto);
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
        ScheduleJobDto scheduleJobDto = new ScheduleJobDto();
        List<ScheduleJobDto> scheduleJobDtoList = scheduleJobService.queryList(scheduleJobDto);
        modelMap.put("scheduleJobDtoList", scheduleJobDtoList);

        List<ScheduleJobDto> executingJobList = scheduleJobService.queryExecutingJobList();
        modelMap.put("executingJobList", executingJobList);

        return "quartz/list-schedule-job";
    }

}
