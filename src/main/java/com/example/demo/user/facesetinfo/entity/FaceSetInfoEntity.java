package com.example.demo.user.facesetinfo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-10-12 17:54:23
 */
@Data
@Accessors(chain = true)
@TableName("face_set_info")
public class FaceSetInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 人脸库id
	 */
	@TableId
	private Long faceSetId;
	/**
	 * 人脸库名称
	 */
	private String faceSetName;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 人脸库最大容量
	 */
	private Integer faceSetCapacity;

}
