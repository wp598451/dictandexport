package com.example.demo.user.common.dict.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TDictExportVo implements Serializable {
    private static final long serialVersionUID = -5609260436241557953L;
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
