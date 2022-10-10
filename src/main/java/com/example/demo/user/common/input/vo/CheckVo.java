package com.example.demo.user.common.input.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class CheckVo implements Serializable {
    private static final long serialVersionUID = -1494924043115338235L;

    /** 错误信息 */
    private StringBuilder errmsg;

    /** 行数 */
    private Row row;

    /** 对应单元格 */
    private Cell cell;

    /** 对应单元格数据 */
    private String s;

    /** 单元格样式 */
    private XSSFCellStyle cellStyle;

    /** 字典名称 */
    private List<String> dicMc;

    /** 重复校验集合 */
    private List<String> dataList;

}
