package com.example.demo.user.facesetinfo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.user.common.utils.PageUtils;
import com.example.demo.user.facesetinfo.entity.FaceSetInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-10-12 17:54:23
 */
public interface FaceSetInfoService extends IService<FaceSetInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Long insertFaceSetInfo(FaceSetInfoEntity faceSetInfoEntity);

    FaceSetInfoEntity queryFaceSetInfo(Long faceSetId);

    boolean deleteFaceSetInfo(Long... faceSetIds);
}

