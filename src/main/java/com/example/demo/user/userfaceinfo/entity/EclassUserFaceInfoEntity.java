package com.example.demo.user.userfaceinfo.entity;

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
 * @date 2022-10-08 17:28:47
 */
@Data
@Accessors(chain = true)
@TableName("eclass_user_face_info")
public class EclassUserFaceInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 人脸id
	 */
	@TableId
	private Long faceId;
	/**
	 * 人脸特征
	 */
	private String faceFeature;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 学校ID
	 */
	private Integer schoolId;
	/**
	 * 分组ID(备用分组标识)
	 */
	private String groupId;
	/**
	 * 类型1:学生0:访客
	 */
	private Integer type;
	/**
	 * 录入时间
	 */
	private Date createDate;
	/**
	 * 来源节点
	 */
	private String from;
	/**
	 * 身份证号码
	 */
	private String idNum;
	/**
	 * 人脸图片地址
	 */
	private String imagePath;
	/**
	 * 逻辑删除 1. 已删除; 0. 未删除
	 */
	private Integer enabled;
	/**
	 * 修改时间
	 */
	private Date updateDate;

}
