package com.example.demo.user.modules.student.controller;

import java.util.Arrays;
import java.util.Map;

import com.example.demo.user.common.user.entity.TUserEntity;
import com.example.demo.user.common.user.utils.UserUtils;
import com.example.demo.user.common.utils.GlobalVariable;
import com.example.demo.user.common.utils.IdUtils;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.example.demo.user.modules.student.entity.TStudentEntity;
import com.example.demo.user.modules.student.service.TStudentService;
import com.example.demo.user.common.utils.PageUtils;
import com.example.demo.user.common.utils.R;

import javax.servlet.http.HttpSession;


/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-08-10 09:46:55
 */
@RestController
@RequestMapping("student/tstudent")
public class TStudentController {
    @Autowired
    @Qualifier("tStudentService")
    private TStudentService tStudentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params){
        PageUtils page = tStudentService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{sid}")
    public R info(@PathVariable("sid") String sid){
		TStudentEntity tStudent = tStudentService.selectById(sid);

        return R.ok().put("tStudent", tStudent);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody TStudentEntity tStudent, HttpSession session){
        tStudent.setSid(IdUtils.fastSimpleUUID()).setYxbz(GlobalVariable.YXBZ_YX).setCreateBy(UserUtils.getUserName(session));
		tStudentService.insert(tStudent);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TStudentEntity tStudent){
		tStudentService.updateById(tStudent);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] sids){
		tStudentService.deleteBatchIds(Arrays.asList(sids));

        return R.ok();
    }

}
