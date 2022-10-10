package com.example.demo.user.faceIndex.controller;

import java.util.Arrays;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.user.faceIndex.entity.FaceIndexEntity;
import com.example.demo.user.faceIndex.service.FaceIndexService;
import com.example.demo.user.common.utils.PageUtils;
import com.example.demo.user.common.utils.R;



/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-10-08 10:45:35
 */
@RestController
@RequestMapping("faceIndex/faceindex")
public class FaceIndexController {
    @Autowired
    private FaceIndexService faceIndexService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = faceIndexService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{index}")
    public R info(@PathVariable("index") Integer index){
		FaceIndexEntity faceIndex = faceIndexService.selectById(index);

        return R.ok().put("faceIndex", faceIndex);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FaceIndexEntity faceIndex){
		faceIndexService.insert(faceIndex);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody FaceIndexEntity faceIndex){
		faceIndexService.updateById(faceIndex);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] indexs){
		faceIndexService.deleteBatchIds(Arrays.asList(indexs));

        return R.ok();
    }

}
