package com.example.demo.user.faceIndex.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.user.common.utils.PageUtils;
import com.example.demo.user.faceIndex.entity.FaceIndexEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-10-08 10:45:35
 */
public interface FaceIndexService extends IService<FaceIndexEntity> {

    PageUtils queryPage(Map<String, Object> params);

    String findKeyByIndex(Integer index);

    List<Long> findIndexes(String... keys);

    boolean cleanIndex();
}

