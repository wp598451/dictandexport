package com.example.demo.user.modules.student.dao;

import com.example.demo.user.modules.student.entity.TStudentEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-08-10 09:46:55
 */
@Mapper
public interface TStudentDao extends BaseMapper<TStudentEntity> {

    List<TStudentEntity> queryList(Map<String, Object> params);
}
