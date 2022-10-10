package com.example.demo.user.common.user.dao;

import com.example.demo.user.common.user.entity.TUserEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-08-09 10:39:44
 */
@Mapper
public interface TUserDao extends BaseMapper<TUserEntity> {

    List<Map<Object, String>> queryUser();

    int queryCount(String username);

    TUserEntity queryUserLogin(@Param("username") String username, @Param("password") String password);
}
