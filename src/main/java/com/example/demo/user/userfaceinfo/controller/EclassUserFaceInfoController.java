package com.example.demo.user.userfaceinfo.controller;

import java.util.Arrays;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.user.userfaceinfo.entity.EclassUserFaceInfoEntity;
import com.example.demo.user.userfaceinfo.service.EclassUserFaceInfoService;
import com.example.demo.user.common.utils.PageUtils;
import com.example.demo.user.common.utils.R;



/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-10-08 17:28:47
 */
@RestController
@RequestMapping("userfaceinfo/eclassuserfaceinfo")
public class EclassUserFaceInfoController {
    @Autowired
    private EclassUserFaceInfoService eclassUserFaceInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = eclassUserFaceInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{faceId}")
    public R info(@PathVariable("faceId") Long faceId){
		EclassUserFaceInfoEntity eclassUserFaceInfo = eclassUserFaceInfoService.selectById(faceId);

        return R.ok().put("eclassUserFaceInfo", eclassUserFaceInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody EclassUserFaceInfoEntity eclassUserFaceInfo){
		eclassUserFaceInfoService.insert(eclassUserFaceInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody EclassUserFaceInfoEntity eclassUserFaceInfo){
		eclassUserFaceInfoService.updateById(eclassUserFaceInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] faceIds){
		eclassUserFaceInfoService.deleteBatchIds(Arrays.asList(faceIds));

        return R.ok();
    }

}
