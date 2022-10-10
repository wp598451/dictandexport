package com.example.demo.user.faceIndex.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.user.common.utils.PageUtils;
// import com.example.demo.user.common.utils.Query;

import com.example.demo.user.faceIndex.dao.FaceIndexDao;
import com.example.demo.user.faceIndex.entity.FaceIndexEntity;
import com.example.demo.user.faceIndex.service.FaceIndexService;


@Service("faceIndexService")
public class FaceIndexServiceImpl extends ServiceImpl<FaceIndexDao, FaceIndexEntity> implements FaceIndexService {

    @Autowired
    private FaceIndexDao faceIndexDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {


        return null;
    }

    @Override
    public String findKeyByIndex(Integer index) {
        return faceIndexDao.findKeyByIndex(index);
    }

    @Override
    public List<Long> findIndexes(String... keys) {

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < keys.length; ++i) {
            if (i > 0) {
                sb.append(",");
            }

            sb.append("'" + keys[i].replace("'", "''") + "'");
        }

        return faceIndexDao.findIndexes(sb.toString());
    }

    @Override
    public boolean cleanIndex() {
        return faceIndexDao.clearIndex();
    }

}
