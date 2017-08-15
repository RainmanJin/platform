package com.icinfo.platform.image.service;

import com.icinfo.platform.image.model.Image;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片集服务接口
 */
public interface IImageService {
    /**
     * 新增图片信息
     *
     * @param image 图片信息
     * @return 新增结果 成功：true，失败：false
     * @throws Exception
     */
    boolean insert(Image image) throws Exception;

    /**
     * 查询图片集
     *
     * @return 图片集
     * @throws Exception
     */
    List<Image> selectList() throws Exception;

    /**
     * 删除
     *
     * @param id 唯一标识
     * @return 删除结果 成功：true，失败：false
     * @throws Exception
     */
    boolean delete(String id) throws Exception;

    /**
     * 根据id查找图片信息
     *
     * @param id 唯一标识
     * @return 图片信息
     * @throws Exception
     */
    Image getById(String id) throws Exception;

    Map<String, Object> selectOne(String id) throws Exception;
}
