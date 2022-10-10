package com.example.demo.user.common.dict.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.user.common.dict.dao.TDictDao;
import com.example.demo.user.common.dict.dispose.DictImport;
import com.example.demo.user.common.dict.entity.TDictEntity;
import com.example.demo.user.common.dict.service.DictExport;
import com.example.demo.user.common.dict.service.TDictService;
import com.example.demo.user.common.dict.utill.VoUtils;
import com.example.demo.user.common.dict.vo.QueryVo;
import com.example.demo.user.common.dict.vo.TDictExportVo;
import com.example.demo.user.common.input.IImportFactory;
import com.example.demo.user.common.output.ExcelDispose;
import com.example.demo.user.common.output.IDispose;
import com.example.demo.user.common.utils.PageUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("tDictService")
public class TDictServiceImpl extends ServiceImpl<TDictDao, TDictEntity> implements TDictService {

    @Autowired
    private TDictDao dictDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {


        return null;
    }

    @Override
    public List<TDictEntity> getDictByDictCode(String dicCode) {
        return dictDao.getDictByDictCode(dicCode);
    }

    @Override
    public TDictEntity getDictByDictCodeDic(String dicCode, String dicValue) {
        return dictDao.getDictByDictCodeDic(dicCode, dicValue);
    }

    @Override
    public void importExcel(InputStream inputStream, HttpSession session) throws Exception {
        // 表头数量
        int j = 0;
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        Sheet s = wb.getSheetAt(0);
        int allNums = s.getPhysicalNumberOfRows();
        IImportFactory<TDictEntity> check = new DictImport(dictDao);
        List<TDictEntity> dictEntities = new ArrayList<>();
        //校验
        check.checkResult(wb, s, allNums, inputStream, dictEntities, j);
        check.createData(dictEntities, session);
    }

    @Override
    public void exportExcel(QueryVo vo, HttpServletResponse response) throws Exception {
        // 新行的索引
        int i = 2;
        // 表头索引
        int j = 1;
        List<String> cellNameList = new ArrayList<>();
        Integer width = null;
        IDispose<TDictEntity, TDictExportVo> dispose = new DictExport();
        ExcelDispose<TDictEntity, TDictExportVo> dictEntityTDictExportVoExcelDispose = new DictExport();
        // 导出excel模板根据传入的参数置空list,1:模板导出；2：全量导出
        List<TDictEntity> xhryyjcglResultVos = vo.getMb().equals("1") ? new ArrayList<>() : queryDict(vo);
        // 待更新导出excel的模板
        String path = "static/dict.xlsx";
        dictEntityTDictExportVoExcelDispose.excelPathDispose(path, xhryyjcglResultVos, cellNameList, i, j, response, width, dispose);
    }

    @Override
    public List<TDictEntity> queryDict(QueryVo vo) {
        return dictDao.selectList(null);
    }

}
