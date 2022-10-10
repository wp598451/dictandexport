package com.example.demo.user.common.dict.service;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.user.common.dict.entity.TDictEntity;
import com.example.demo.user.common.dict.vo.TDictExportVo;
import com.example.demo.user.common.output.ExcelDispose;
import com.example.demo.user.common.output.IDispose;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * 人感染禽流感监测管理导出实例
 * @author wp
 */
public class DictExport extends ExcelDispose<TDictEntity, TDictExportVo> implements IDispose<TDictEntity, TDictExportVo> {

    @Override
    public void dataDispose(Row row, Sheet sheet, List<String> cellNameList,
                            List<String> valueList, CellStyle cellStyle, Integer width, int cells) {
        for (int j = 0; j < cells; j++) {
            Cell cell = row.createCell(j);
            for (int z = 0; z < valueList.size(); z++) {
                if (j == z) {
                    if (j == 0) {
                        // 插入序号，并按照表头对序号进行列宽设置
//                        width = DataExportUtils.checkNum(cellNameList.get(0).length());
//                        sheet.setColumnWidth(j, width);
                        cell.setCellValue(valueList.get(z));
                        cell.setCellStyle(cellStyle);
                        break;
                    }
                    cell.setCellValue(valueList.get(z));
                    cell.setCellStyle(cellStyle);
                    break;
                }
            }
        }
    }

    @Override
    public TDictExportVo getType(List<TDictEntity> list, int k) {
        TDictEntity dictEntity = list.get(k);
        TDictExportVo dictExportVo = BeanUtil.toBean(dictEntity, TDictExportVo.class);
        return dictExportVo;
    }

    @Override
    public void valueListDispose(List<String> valueList, TDictExportVo xhryyjcglExportVo) {
    }
}
