package com.example.demo.user.userfaceinfo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.user.common.utils.PageUtils;
// import com.example.demo.user.common.utils.Query;

import com.example.demo.user.userfaceinfo.dao.EclassUserFaceInfoDao;
import com.example.demo.user.userfaceinfo.entity.EclassUserFaceInfoEntity;
import com.example.demo.user.userfaceinfo.service.EclassUserFaceInfoService;


@Service("eclassUserFaceInfoService")
public class EclassUserFaceInfoServiceImpl extends ServiceImpl<EclassUserFaceInfoDao, EclassUserFaceInfoEntity> implements EclassUserFaceInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {


        return null;
    }

}
