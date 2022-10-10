package com.example.demo.user.faceImg.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.user.common.utils.PageUtils;
import com.example.demo.user.faceImg.entity.FaceImgEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-10-08 10:42:26
 */
public interface FaceImgService extends IService<FaceImgEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean clearImg();

    List<FaceImgEntity> findFaceImgs();
}

