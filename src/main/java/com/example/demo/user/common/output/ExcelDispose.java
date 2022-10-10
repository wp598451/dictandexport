package com.example.demo.user.common.output;

import com.example.demo.user.common.output.util.DataExportUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板/数据导出抽象类
 * @param <T>   实体
 * @param <V>   Vo
 */
public abstract class ExcelDispose<T, V> {

    /**
     * 反射
     * @param valueList
     * @param v
     * @throws IllegalAccessException
     */
    public void resultDispose(List<String> valueList, V v) throws IllegalAccessException {
        // 将实体中的数据提取出来放到list容器中
        Class<?> aClass = v.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (int o = 1; o < declaredFields.length; o++) {
            Field declaredField = declaredFields[o];
            declaredField.setAccessible(true);
            Object value = declaredField.get(v);
            String s = null != value ? value.toString() : "";
            valueList.add(s);
            // 关闭除public外的访问权限
            declaredField.setAccessible(false);
        }
    }

    /**
     * 对表数据处理并写入到单元格,每张表必须实现,已经通过接口实现，留下为了兼容以前的代码
     * @param row
     * @param sheet
     * @param cellNameList
     * @param valueList
     * @param cellStyle
     * @param width
     * @param cells
     */
    public abstract void dataDispose(Row row, Sheet sheet, List<String> cellNameList,
                                     List<String> valueList, CellStyle cellStyle, Integer width, int cells);


    /**
     * 导出主逻辑
     * @param path excel文件路径
     * @param list 查询出来的数据
     * @param cellNameList 表头
     * @param i 对应文件的新行索引
     * @param j 对应文件的表头索引
     * @param response
     * @param width 列宽
     * @throws Exception
     */
    public void excelPathDispose(String path, List<T> list, List<String> cellNameList,
                                 int i, int j, HttpServletResponse response,
                                 Integer width, IDispose<T, V> dispose) throws Exception {
        InputStream inputStream = DataExportUtils.getStream(path);
        XSSFWorkbook wb = DataExportUtils.getwb(inputStream);
        SXSSFWorkbook swb = DataExportUtils.getswb(wb);
        Sheet sheet =  DataExportUtils.getSheet(swb, 0);
        Row row = DataExportUtils.getRow(sheet, j);
        CellStyle cellStyle = DataExportUtils.getCellStyle(swb);
        int cells = row.getPhysicalNumberOfCells();
        for (Cell cell : row) {
            String stringCellValue = cell.getStringCellValue();
            cellNameList.add(stringCellValue);
        }
        dataDispose(list, cellNameList, i, width, dispose, sheet, cellStyle, cells);
        DataExportUtils.sxssfWriteAndClose(response, inputStream, wb, swb);
    }

    /**
     * 对数据进行处理
     * @param list
     * @param cellNameList
     * @param i
     * @param width
     * @param dispose
     * @param sheet
     * @param cellStyle
     * @param cells
     * @throws IllegalAccessException
     */
    private void dataDispose(List<T> list, List<String> cellNameList, int i, Integer width, IDispose<T, V> dispose, Sheet sheet, CellStyle cellStyle, int cells) throws IllegalAccessException {
        Row row;
        for (int k = 0; k < list.size(); k++) {
            List<String> valueList = new ArrayList<>();
            // 创建新行
            V v = dispose.getType(list, k);
            resultDispose(valueList, v);
            row = sheet.createRow(i++);
            int xh = k + 1;
            valueList.add(0, Integer.toString(xh));
            // 做一些数据上的处理
            valueListDispose(valueList, v);
            dispose.dataDispose(row, sheet, cellNameList,valueList, cellStyle, width, cells);
        }
    }

    /**
     * 需加抽象方法，但是为了兼容之前的版本，做成了实例，子类必须重写,比如对应字段的编码改成文字
     * @param valueList
     * @param v
     */
    public void valueListDispose(List<String> valueList, V v) {
        // 做一些数据上的处理，如果有的话需要重写，没有则不管
    }
}
