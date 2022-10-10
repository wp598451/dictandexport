package com.example.demo.user.userfaceinfo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.user.common.utils.PageUtils;
import com.example.demo.user.userfaceinfo.entity.EclassUserFaceInfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-10-08 17:28:47
 */
public interface EclassUserFaceInfoService extends IService<EclassUserFaceInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

