package com.example.demo.user.common.input;

import com.example.demo.user.common.input.vo.CheckVo;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;

public interface IImportFactory<T>{

    /**
     * 校验
     * @param wb
     * @param s
     * @param allNums
     * @param inputStream
     * @param list
     * @param rowIndex (新加参数)表头行数
     * @return
     * @throws Exception
     */
    void checkResult(XSSFWorkbook wb, Sheet s, int allNums, InputStream inputStream, List<T> list, int rowIndex) throws Exception;

    /**
     * 导入数据
     * @param s
     * @param allNums
     * @param checkVo
     * @param dataList
     * @param flag
     * @param list
     * @param rowIndex (新加参数)表头行数
     * @return
     * @throws Exception
     */
    void checkData(Sheet s, int allNums, CheckVo checkVo, List<String> dataList, int flag, List<T> list, int rowIndex) throws Exception;

    /**
     * 导入数据处理
     * @param s
     * @param i
     */
    void create(Sheet s, int i, CheckVo checkVo, List<String> dataList, int flag, List<T> list) throws Exception;

    /**
     * 校验通过插入/更新数据
     * @param list
     */
    void createData(List<T> list, HttpSession session);
}
