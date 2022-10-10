package com.example.demo.user.faceIndex.dao;

import com.example.demo.user.faceIndex.entity.FaceIndexEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.user.userfaceinfo.entity.EclassUserFaceInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-10-08 10:45:35
 */
@Mapper
public interface FaceIndexDao extends BaseMapper<FaceIndexEntity> {

    String findKeyByIndex(@Param("index") Integer index);

    List<Long> findIndexes(@Param("keys") String keys);

    boolean clearIndex();

    List<EclassUserFaceInfoEntity> getUserFaces();
}
