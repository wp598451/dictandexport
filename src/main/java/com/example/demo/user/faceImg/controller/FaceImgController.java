package com.example.demo.user.faceImg.controller;

import java.util.Arrays;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.user.faceImg.entity.FaceImgEntity;
import com.example.demo.user.faceImg.service.FaceImgService;
import com.example.demo.user.common.utils.PageUtils;
import com.example.demo.user.common.utils.R;



/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-10-08 10:42:26
 */
@RestController
@RequestMapping("faceImg/faceimg")
public class FaceImgController {
    @Autowired
    private FaceImgService faceImgService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = faceImgService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{key}")
    public R info(@PathVariable("key") String key){
		FaceImgEntity faceImg = faceImgService.selectById(key);

        return R.ok().put("faceImg", faceImg);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FaceImgEntity faceImg){
		faceImgService.insert(faceImg);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody FaceImgEntity faceImg){
		faceImgService.updateById(faceImg);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] keys){
		faceImgService.deleteBatchIds(Arrays.asList(keys));

        return R.ok();
    }

}
