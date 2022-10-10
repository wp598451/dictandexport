package com.example.demo.user.common.dict.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.user.common.dict.entity.TDictEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-08-12 15:28:23
 */
@Mapper
public interface TDictDao extends BaseMapper<TDictEntity> {

    /**
     * 查询字典下拉框
     * @param dicCode
     * @return
     */
    List<TDictEntity> getDictByDictCode(@Param("dicCode") String dicCode);

    /**
     * 查询具体字典
     * @param dicCode
     * @return
     */
    TDictEntity getDictByDictCodeDic(@Param("dicCode") String dicCode, @Param("dicValue") String dicValue);

}
