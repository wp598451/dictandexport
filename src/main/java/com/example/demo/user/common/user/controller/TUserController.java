package com.example.demo.user.common.user.controller;

import java.util.Arrays;
import java.util.Map;

import com.example.demo.user.common.user.utils.UserUtils;
import com.example.demo.user.common.utils.GlobalVariable;
import com.example.demo.user.common.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.user.common.user.entity.TUserEntity;
import com.example.demo.user.common.user.service.TUserService;
import com.example.demo.user.common.utils.PageUtils;
import com.example.demo.user.common.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-08-09 10:39:44
 */
@RestController
@RequestMapping("admission/tuser")
public class TUserController {
    @Autowired
    private TUserService tUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    public R info(@PathVariable("userId") String userId){
		TUserEntity tUser = tUserService.selectById(userId);

        return R.ok().put("tUser", tUser);
    }

    /**
     * 注册
     */
    @RequestMapping("/login/register")
    @Transactional(readOnly = false)
    public R save(@RequestBody TUserEntity tUser, HttpSession session){

        tUserService.register(tUser.getUsername());

        tUser.setUserId(IdUtils.fastSimpleUUID())
                .setYxbz(GlobalVariable.YXBZ_YX).setCreateBy(UserUtils.getUserName(session));
		tUserService.insert(tUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TUserEntity tUser){
		tUserService.updateById(tUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] userIds){
		tUserService.deleteBatchIds(Arrays.asList(userIds));

        return R.ok();
    }

    /**
     * 登录
     */
    @GetMapping("/login/do")
    public R login(@Validated String username, @Validated String password, HttpServletRequest request){
        R r = tUserService.login(username, password);

        if (!r.get("code").equals("1")) {

            request.getSession().setAttribute("currntUser", r.get("user"));
        }
        return r;
    }
}
