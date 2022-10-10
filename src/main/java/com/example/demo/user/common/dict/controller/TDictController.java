package com.example.demo.user.common.dict.controller;

import com.example.demo.user.common.dict.entity.TDictEntity;
import com.example.demo.user.common.dict.service.TDictService;
import com.example.demo.user.common.dict.vo.QueryVo;
import com.example.demo.user.common.utils.PageUtils;
import com.example.demo.user.common.utils.R;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-08-12 15:28:23
 */
@RestController
@RequestMapping("dict/tdict")
public class TDictController {
    @Autowired
    @Qualifier("tDictService")
    private TDictService tDictService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tDictService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{dicId}")
    public R info(@PathVariable("dicId") String dicId){
		TDictEntity tDict = tDictService.selectById(dicId);

        return R.ok().put("tDict", tDict);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TDictEntity tDict){
		tDictService.insert(tDict);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TDictEntity tDict){
		tDictService.updateById(tDict);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] dicIds){
		tDictService.deleteBatchIds(Arrays.asList(dicIds));

        return R.ok();
    }

    /**
     * 字典导入
     */
    @PostMapping("/import/dict")
    public R importExcel(@RequestParam("file") MultipartFile file, HttpSession session) throws Exception {
        tDictService.importExcel(file.getInputStream(), session);
        return R.ok().put("data", true);
    }

    /**
     * 导出模板/字典项
     */
    @GetMapping("/export/dict")
    public R exportExcel(QueryVo vo, HttpServletResponse response) throws Exception {
        tDictService.exportExcel(vo, response);
        return R.ok().put("data", true);
    }

    /**
     * 查询字典项
     */
    @GetMapping("/query/dict")
    public R queryDict(QueryVo vo) {
        List<TDictEntity> dictEntities = tDictService.queryDict(vo);
        return R.ok().put("data", dictEntities);
    }
}
