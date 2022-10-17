package com.example.demo.user.common.dict.dispose;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.user.common.dict.dao.TDictDao;
import com.example.demo.user.common.dict.entity.TDictEntity;
import com.example.demo.user.common.input.IImportFactory;
import com.example.demo.user.common.input.vo.CheckVo;
import com.example.demo.user.common.user.utils.UserUtils;
import com.example.demo.user.common.utils.GlobalVariable;
import com.example.demo.user.common.utils.IdUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DictImport implements IImportFactory<TDictEntity> {

    private final DataFormatter DATAFORMATTER = new DataFormatter();

    //excel起始校验行下标
    private static final int START_INDEX = 2;

    private final TDictDao dictDao;

    public DictImport(TDictDao dictDao) {
        this.dictDao = dictDao;
    }

    @Override
    public void checkResult(XSSFWorkbook wb, Sheet s, int allNums, InputStream inputStream, List<TDictEntity> zhqSzkbjcblgls, int rowIndex) {
        // 标识为0，文件校验没有出现错误，执行插入；否则导出错误信息
        int flag = 0;
        //获取默认单元格样式（在第一行创建一个空单元格获取默认样式）
        XSSFCellStyle orgSty = (XSSFCellStyle) s.getRow(0).createCell(100).getCellStyle();
        //设置单元格为文本格式
        XSSFDataFormat format = wb.createDataFormat();
        orgSty.setDataFormat(format.getFormat("@"));
        //错误标红单元格样式
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        cellStyle.setDataFormat(format.getFormat("@"));
        // 重复数据缓存
        List<String> dataList = new ArrayList<>();
        List<String> dicMcList = new ArrayList<>();
        CheckVo checkVo = new CheckVo();
//        checkVo.setCellStyle(cellStyle).setDicMc(dicMcList);
        checkData(s, allNums, checkVo, dataList, flag, zhqSzkbjcblgls, rowIndex);
    }

    @Override
    public void checkData(Sheet s, int allNums, CheckVo checkVo, List<String> dataList, int flag, List<TDictEntity> list, int rowIndex) {
        for (int i = START_INDEX; i < allNums; i++) {
            Row r = s.getRow(i);
            if (r == null) {
                continue;
            }
            // 导入数据
            create(s, i, checkVo, dataList, flag, list);
        }
    }

    @Override
    public void create(Sheet s, int i, CheckVo checkVo, List<String> dataList, int flag, List<TDictEntity> list) {
        //定义错误信息msg
        StringBuilder errorMsg = new StringBuilder();
        Row r = s.getRow(i);
        TDictEntity dictEntity = new TDictEntity();

        // 字典代码
        String dicCode = DATAFORMATTER.formatCellValue(r.getCell(1));
        dictEntity.setDicCode(dicCode);

        // 字典数据
        String dicValue = DATAFORMATTER.formatCellValue(r.getCell(2));
        dictEntity.setDicValue(dicValue);

        // 字典名称
        String dicName = DATAFORMATTER.formatCellValue(r.getCell(3));
        dictEntity.setDicName(dicName);
        list.add(dictEntity);
    }

    @Override
    public void createData(List<TDictEntity> list, HttpSession session) {
        for (TDictEntity dictEntity : list) {
            dictEntity.setDicId(IdUtils.fastSimpleUUID());
            dictEntity.setCreateBy(UserUtils.getUserName(session));
            dictEntity.setCreateTime(new Date());
            dictEntity.setYxbz(GlobalVariable.YXBZ_YX);
            dictDao.insert(dictEntity);
        }
    }

}
