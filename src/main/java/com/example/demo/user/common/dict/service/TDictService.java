package com.example.demo.user.common.dict.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.user.common.dict.entity.TDictEntity;
import com.example.demo.user.common.dict.vo.QueryVo;
import com.example.demo.user.common.utils.PageUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-08-12 15:28:23
 */
public interface TDictService extends IService<TDictEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询字典下拉框
     * @param dicCode
     * @return
     */
    List<TDictEntity> getDictByDictCode(String dicCode);

    /**
     * 查询具体字典
     * @param dicCode
     * @return
     */
    TDictEntity getDictByDictCodeDic(String dicCode, String dicValue);

    /**
     * 通用导入
     */
    void importExcel(InputStream inputStream, HttpSession session) throws Exception;

    /**
     *
     * @Author WP
     * @Description 通用导出/模板下载
     */
    void exportExcel(QueryVo vo, HttpServletResponse response) throws Exception;

    List<TDictEntity> queryDict(QueryVo vo);
}

