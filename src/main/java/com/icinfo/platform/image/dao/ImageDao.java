package com.icinfo.platform.image.dao;

import com.icinfo.platform.image.mapper.ImageMapper;
import com.icinfo.platform.image.model.Image;

import java.util.List;
import java.util.Map;

/**
 * 图片集dao
 */
public interface ImageDao extends ImageMapper {
    List<Image> selectList() throws Exception;

    Map<String, Object> selectOne(String id) throws Exception;
}
