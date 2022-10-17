package com.example.demo.user.facesetinfo.service.impl;

import com.example.demo.user.common.utils.SnowflakeIdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.user.common.utils.PageUtils;
// import com.example.demo.user.common.utils.Query;

import com.example.demo.user.facesetinfo.dao.FaceSetInfoDao;
import com.example.demo.user.facesetinfo.entity.FaceSetInfoEntity;
import com.example.demo.user.facesetinfo.service.FaceSetInfoService;


@Service("faceSetInfoService")
public class FaceSetInfoServiceImpl extends ServiceImpl<FaceSetInfoDao, FaceSetInfoEntity> implements FaceSetInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {


        return null;
    }

    @Override
    public Long insertFaceSetInfo(FaceSetInfoEntity faceSetInfoEntity) {
        if (StringUtils.isNotBlank(String.valueOf(faceSetInfoEntity.getFaceSetId()))) {
            faceSetInfoEntity.setFaceSetId(SnowflakeIdWorker.getInstant().nextId());
            this.insert(faceSetInfoEntity);
        }
        return faceSetInfoEntity.getFaceSetId();
    }

    @Override
    public FaceSetInfoEntity queryFaceSetInfo(Long faceSetId) {
        FaceSetInfoEntity faceSetInfoEntity = this.selectById(faceSetId);
        return faceSetInfoEntity;
    }

    @Override
    public boolean deleteFaceSetInfo(Long... faceSetIds) {

        for (Long faceSetId : faceSetIds) {
            this.deleteById(faceSetId);
        }

        return true;
    }

}
