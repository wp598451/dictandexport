package com.example.demo.user.modules.student.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.user.common.dict.utill.DicDataUtils;
import com.example.demo.user.common.utils.DictKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.user.common.utils.PageUtils;

import com.example.demo.user.modules.student.dao.TStudentDao;
import com.example.demo.user.modules.student.entity.TStudentEntity;
import com.example.demo.user.modules.student.service.TStudentService;


@Service("tStudentService")
public class TStudentServiceImpl extends ServiceImpl<TStudentDao, TStudentEntity> implements TStudentService {

    /** 需要配置字典项的字段后缀 */
    private static final String SUFFIX = "Name";

    /**  */
//    private static final JSONObject jsonObject = new JSONObject();

    @Autowired
    private TStudentDao studentDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<TStudentEntity> list = studentDao.queryList(params);
        List<JSONObject> resultList = new ArrayList<>();
        for (TStudentEntity tStudentEntity : list) {
            // 实体对象转换为jsonObject
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(tStudentEntity).toString());
            // jsonObject转换为实体对象
//            TStudentEntity tStudentEntity1 = JSONObject.toJavaObject(jsonObject, TStudentEntity.class);

//            jsonObject.put("ssex" + SUFFIX, DicDataUtils.getDictName(DictKey.SEX, tStudentEntity.getSsex()));
            resultList.add(jsonObject);
        }
        return new PageUtils(resultList, resultList.size(), resultList.size(), 10);
    }

}
