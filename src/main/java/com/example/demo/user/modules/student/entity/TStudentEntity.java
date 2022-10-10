package com.example.demo.user.modules.student.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

import com.example.demo.user.common.note.annocation.Dict;
import com.example.demo.user.common.utils.DictKey;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-08-10 09:46:55
 */
@Data
@Accessors(chain = true)
@TableName("t_student")
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TStudentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private String sid;
	/**
	 * 姓名
	 */
	private String sname;
	/**
	 * 年龄
	 */
	private Integer sage;
	/**
	 * 性别
	 */
	@Dict(dicDataSource = DictKey.SSEX)
	private String ssex;
	/**
	 * 手机号码
	 */
	private String sphone;
	/**
	 * 有效标识 1. 有效 0. 失效
	 */
	private String yxbz;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 创建日期
	 */
	private Date createTime;
	/**
	 * 修改人
	 */
	private String updateBy;
	/**
	 * 修改时间
	 */
	private Date updateTime;

}
