package com.example.demo.user.faceImg.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-10-08 10:42:26
 */
@Data
@Accessors(chain = true)
@TableName("face_img")
public class FaceImgEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 图片特征数据
	 */
	private byte[] imgData;
	/**
	 * 宽度
	 */
	private Integer width;
	/**
	 * 高度
	 */
	private Integer height;
	/**
	 * 方式
	 */
	private Integer channel;

}
