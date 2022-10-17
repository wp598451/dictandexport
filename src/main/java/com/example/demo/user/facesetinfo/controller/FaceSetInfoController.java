package com.example.demo.user.facesetinfo.controller;

import java.util.Arrays;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.user.facesetinfo.entity.FaceSetInfoEntity;
import com.example.demo.user.facesetinfo.service.FaceSetInfoService;
import com.example.demo.user.common.utils.PageUtils;
import com.example.demo.user.common.utils.R;



/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-10-12 17:54:23
 */
@RestController
@RequestMapping("facesetinfo/facesetinfo")
public class FaceSetInfoController {
    @Autowired
    private FaceSetInfoService faceSetInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = faceSetInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{faceSetId}")
    public R info(@PathVariable("faceSetId") Long faceSetId){
		FaceSetInfoEntity faceSetInfo = faceSetInfoService.selectById(faceSetId);

        return R.ok().put("faceSetInfo", faceSetInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FaceSetInfoEntity faceSetInfo){
		faceSetInfoService.insert(faceSetInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody FaceSetInfoEntity faceSetInfo){
		faceSetInfoService.updateById(faceSetInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] faceSetIds){
		faceSetInfoService.deleteBatchIds(Arrays.asList(faceSetIds));

        return R.ok();
    }

    @PostMapping("/insert/faceSetInfo")
    public R insertFaceSetInfo(FaceSetInfoEntity faceSetInfoEntity) {
        Long aLong = faceSetInfoService.insertFaceSetInfo(faceSetInfoEntity);
        return R.ok().put("faceSetId", aLong);
    }

    @GetMapping("/query/faceSetInfo")
    public R queryFaceSetInfo(Long faceSetId) {
        FaceSetInfoEntity faceSetInfoEntity = faceSetInfoService.queryFaceSetInfo(faceSetId);
        return R.ok().put("data", faceSetInfoEntity);
    }

    @DeleteMapping("/delete/faceSetInfo")
    public R deleteFaceSetInfo(Long... faceSetIds) {
        faceSetInfoService.deleteFaceSetInfo(faceSetIds);

        return R.ok();
    }
}
