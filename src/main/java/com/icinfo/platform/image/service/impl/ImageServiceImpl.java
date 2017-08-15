package com.icinfo.platform.image.service.impl;

import com.icinfo.platform.image.dao.ImageDao;
import com.icinfo.platform.image.mapper.ImageMapper;
import com.icinfo.platform.image.model.Image;
import com.icinfo.platform.image.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.sun.tools.doclint.Entity.image;

/**
 * 图片集服务接口实现
 */
@Service
public class ImageServiceImpl implements IImageService {
    /**
     * 图片集mapper注入
     */
    @Autowired
    private ImageMapper imageMapper;

    /**
     * 图片集dao注入
     */
    @Autowired
    private ImageDao imageDao;

    /**
     * 新增图片信息
     *
     * @param image 图片信息
     * @return 新增结果 成功：true，失败：false
     * @throws Exception
     */
    @Override
    public boolean insert(Image image) throws Exception {
        return imageMapper.insert(image) > 0 ? true : false;
    }

    /**
     * 查询图片集
     *
     * @return 图片集
     * @throws Exception
     */
    @Override
    public List<Image> selectList() throws Exception {
        return imageDao.selectList();
    }

    /**
     * 删除
     *
     * @param id 唯一标识
     * @return 删除结果 成功：true，失败：false
     * @throws Exception
     */
    @Override
    public boolean delete(String id) throws Exception {
        return imageMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    /**
     * 根据id查找图片信息
     *
     * @param id 唯一标识
     * @return 图片信息
     * @throws Exception
     */
    @Override
    public Image getById(String id) throws Exception {
        return imageMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> selectOne(String id) throws Exception {
        return imageDao.selectOne(id);
    }
}
