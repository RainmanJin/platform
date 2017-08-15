package com.icinfo.platform.application.service;

import com.icinfo.platform.application.model.Application;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * 应用管理接口
 */
public interface IApplicationService {
    /**
     * 获取应用列表
     *
     * @return 应用列表
     * @throws Exception
     */
    List<Application> selectList() throws Exception;

    /**
     * 新增应用
     *
     * @param application 应用信息
     * @param iconFile 图片文件
     * @return 新增结果
     * @throws Exception
     */
    int insertApp(Application application, CommonsMultipartFile iconFile) throws Exception;
}
