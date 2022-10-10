package com.example.demo.user.common.output;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * 抽象数据处理
 * @param <T> 实体泛型
 */
public interface IDispose<T, V> {

    /**
     * 获取索引K下的的对象
     * @param list
     * @param k
     * @return
     */
    V getType(List<T> list, int k);

    /**
     * 对表数据处理并写入到单元格,每张表必须实现
     * @param row
     * @param sheet
     * @param cellNameList
     * @param valueList
     * @param cellStyle
     * @param width
     * @param cells
     */
    void dataDispose(Row row, Sheet sheet, List<String> cellNameList,
                            List<String> valueList, CellStyle cellStyle, Integer width, int cells);
}
