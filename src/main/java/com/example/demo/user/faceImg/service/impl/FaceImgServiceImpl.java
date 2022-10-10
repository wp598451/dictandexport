package com.example.demo.user.faceImg.service.impl;

import com.cnsugar.ai.face.bean.FaceIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.user.common.utils.PageUtils;
// import com.example.demo.user.common.utils.Query;

import com.example.demo.user.faceImg.dao.FaceImgDao;
import com.example.demo.user.faceImg.entity.FaceImgEntity;
import com.example.demo.user.faceImg.service.FaceImgService;


@Service("faceImgService")
public class FaceImgServiceImpl extends ServiceImpl<FaceImgDao, FaceImgEntity> implements FaceImgService {

    @Autowired
    private FaceImgDao faceImgDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {


        return null;
    }

    @Override
    public boolean clearImg() {
        return faceImgDao.clearImg();
    }

    @Override
    public List<FaceImgEntity> findFaceImgs() {
        return faceImgDao.findFaceImgs();
    }

}
