package com.icinfo.platform.application.service.impl;

import com.icinfo.platform.application.dao.ApplicationDao;
import com.icinfo.platform.application.model.Application;
import com.icinfo.platform.application.service.IApplicationService;
import com.icinfo.platform.oss.api.OssApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * 应用管理接口实现
 */
@Service
public class ApplicationServiceImpl implements IApplicationService {
    /**
     * 应用dao注入
     */
    @Autowired
    private ApplicationDao applicationDao;

    /**
     * ossApi注入
     */
    @Autowired
    private OssApi ossApi;

    /**
     * 获取应用列表
     *
     * @return 应用列表
     * @throws Exception
     */
    @Override
    public List<Application> selectList() throws Exception {
        return applicationDao.selectList();
    }

    /**
     * 新增应用
     *
     * @param application 应用信息
     * @param iconFile 图片文件
     * @return 新增结果
     * @throws Exception
     */
    @Override
    public int insertApp(Application application, CommonsMultipartFile iconFile) throws Exception {
        //String name = ossApi.uploadFile(iconFile);
        String name = iconFile.getOriginalFilename();
        ossApi.uploadFile(name, iconFile.getInputStream());
        application.setAppIconUrl(ossApi.getUrl(name));

        return applicationDao.insert(application);
    }
}
