package com.example.demo.user.common.output.util;

import cn.hutool.core.io.resource.ClassPathResource;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 导出工具类
 * @auther WP
 * @date 2021年11月15日
 */
public class DataExportUtils {

    public static void sxssfWriteAndClose(HttpServletResponse response, InputStream inputStream, XSSFWorkbook workbook, SXSSFWorkbook sworkbook) {
        ServletOutputStream out = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            sworkbook.write(os);
            byte[] bytes = os.toByteArray();
            response.addHeader("Content-Length", String.valueOf(bytes.length));
            out = changeOutputStream(os, response);

        } catch (IOException exception) {
            exception.printStackTrace();
        }finally {
            try {
                inputStream.close();
                workbook.close();
                out.close();
                sworkbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void xssfWriteAndClose(HttpServletResponse response, InputStream inputStream, XSSFWorkbook workbook) {
        ServletOutputStream out = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            workbook.write(os);
            byte[] bytes = os.toByteArray();
            response.addHeader("Content-Length", String.valueOf(bytes.length));
            out = changeOutputStream(os, response);

        } catch (IOException exception) {
            exception.printStackTrace();
        }finally {
            try {
                inputStream.close();
                workbook.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 转换输出流
     * @param os
     * @param response
     * @return
     * @throws IOException
     */
    public static ServletOutputStream changeOutputStream(ByteArrayOutputStream os, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        os.writeTo(outputStream);
        os.close();
        return outputStream;
    }

    /**
     * 导出excel配置样式
     * @param swb
     * @return
     */
    public static CellStyle getCellStyle(SXSSFWorkbook swb) {
        CellStyle cellStyle;
        //创建单元格，并设置值表头 设置表头居中
        cellStyle = swb.createCellStyle();
        //水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 横向居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        return cellStyle;
    }

    /**
     * 校验最大宽度是否超出
     * @param num
     * @return
     */
    public static Integer checkNum(Integer num){
        Integer maxNum = null;
        if (num * 600 > 15000){
            maxNum = 14999;
            return maxNum;
        }
        maxNum = num * 600;
        return maxNum;
    }

    /**
     * 获取序列化
     */
    public static InputStream getStream(String path){
        ClassPathResource resource = new ClassPathResource(path);
        return resource.getStream();
    }

    /**
     * 获取xssfworkbook
     */
    public static XSSFWorkbook getwb(InputStream inputStream){
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    /**
     * 获取sxssfworkbook
     */
    public static SXSSFWorkbook getswb(XSSFWorkbook wb){
        return new SXSSFWorkbook(wb);
    }

    /**
     * 获取sheet
     */
    public static Sheet getSheet(SXSSFWorkbook swb, int i){
//        SXSSFSheet sheet = swb.getSheetAt(i);
        Sheet sheet;
        if (swb instanceof SXSSFWorkbook) {
            sheet = swb.getXSSFWorkbook().getSheetAt(i);
        } else {
            sheet = swb.getSheetAt(i);
        }
        return sheet;
    }

    /**
     *
     * @param sheet
     * @param i 对应文件的表头索引
     * @return
     */
    public static Row getRow(Sheet sheet, int i){
        return sheet.getRow(i);
    }
}
