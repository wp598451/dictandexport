package com.example.demo.user.faceIndex.entity;

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
 * @date 2022-10-08 10:45:35
 */
@Data
@Accessors(chain = true)
@TableName("face_index")
public class FaceIndexEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 索引
	 */
	@TableId
	private String idIndex;
	/**
	 * 主键
	 */
	private String id;

}
