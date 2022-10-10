package com.example.demo.user.modules.student.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.user.common.utils.PageUtils;
import com.example.demo.user.modules.student.entity.TStudentEntity;

import java.util.Map;

/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-08-10 09:46:55
 */
public interface TStudentService extends IService<TStudentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

