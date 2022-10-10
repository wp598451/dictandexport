package com.example.demo.user.common.dict.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class DictQueryVo implements Serializable {
    private static final long serialVersionUID = -8254341170371827295L;

    /**
     * 字典id
     */
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
}
