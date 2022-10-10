package com.example.demo.user.common.dict.entity;

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
 * @date 2022-08-12 15:28:23
 */
@Data
@Accessors(chain = true)
@TableName("t_dict")
public class TDictEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 字典id
	 */
	@TableId
	private String dicId;
	/**
	 * 字典项
	 */
	private String dicCode;
	/**
	 * 字典代码
	 */
	private String dicValue;
	/**
	 * 字典名称
	 */
	private String dicName;
	/**
	 * 有效标识 1. 有效 0. 失效
	 */
	private String yxbz;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新人
	 */
	private String updateBy;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
