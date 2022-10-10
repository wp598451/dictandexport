package com.example.demo.user.faceImg.dao;

import com.cnsugar.ai.face.bean.FaceIndex;
import com.example.demo.user.faceImg.entity.FaceImgEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-10-08 10:42:26
 */
@Mapper
public interface FaceImgDao extends BaseMapper<FaceImgEntity> {

    boolean clearImg();

    List<FaceImgEntity> findFaceImgs();
}
