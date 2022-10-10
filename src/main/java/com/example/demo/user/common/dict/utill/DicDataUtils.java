package com.example.demo.user.common.dict.utill;

import cn.hutool.extra.spring.SpringUtil;
import com.example.demo.user.common.dict.entity.TDictEntity;
import com.example.demo.user.common.dict.service.TDictService;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DicDataUtils {

    private static final TDictService dictService = SpringUtil.getBean(TDictService.class);

    /**
     * 根据code获取所有字典项
     * @param dictCode
     * @return
     */
    public static List<TDictEntity> getDictMap(String dictCode) {

        return 0 != dictService.getDictByDictCode(dictCode).size()
                ? dictService.getDictByDictCode(dictCode) : new ArrayList<>();
    }

    /**
     * 根据code和value获取字典项
     * @param dictCode
     * @param dictValue
     * @return
     */
    public static TDictEntity getDict(String dictCode, String dictValue) {

        return null != dictService.getDictByDictCodeDic(dictCode, dictValue)
                ? dictService.getDictByDictCodeDic(dictCode, dictValue) : new TDictEntity();
    }

    /**
     * 根据code和value获取字典代码
     * @param dictCode
     * @param dictValue
     * @return
     */
    public static String getDictValue(String dictCode, String dictValue) {
        TDictEntity dictByDictCodeDic = getDict(dictCode, dictValue);

        return StringUtils.isNotBlank(dictByDictCodeDic.getDicValue()) ? dictByDictCodeDic.getDicValue() : "";
    }

    /**
     * 根据code和value获取字典名称
     * @param dictCode
     * @param dictValue
     * @return
     */
    public static String getDictName(String dictCode, String dictValue) {
        TDictEntity dictByDictCodeDic = getDict(dictCode, dictValue);

        return StringUtils.isNotBlank(dictByDictCodeDic.getDicName()) ? dictByDictCodeDic.getDicName() : "";
    }

}
