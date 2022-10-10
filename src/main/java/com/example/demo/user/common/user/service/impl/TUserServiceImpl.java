package com.example.demo.user.common.user.service.impl;

import com.example.demo.user.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.user.common.utils.PageUtils;

import com.example.demo.user.common.user.dao.TUserDao;
import com.example.demo.user.common.user.entity.TUserEntity;
import com.example.demo.user.common.user.service.TUserService;


@Service("tUserService")
@Slf4j
public class TUserServiceImpl extends ServiceImpl<TUserDao, TUserEntity> implements TUserService {

    @Autowired
    private TUserDao userDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<Map<Object, String>> maps = userDao.queryUser();

        return new PageUtils(maps, maps.size(), maps.size(), 2);
    }

    @Override
    public R login(String username, String password) {

        int resultCount = userDao.queryCount(username);
        if (0 == resultCount) {
            return R.error(1, "用户不存在！");
        }

        TUserEntity tUserEntity = userDao.queryUserLogin(username, password);
        if (null == tUserEntity) {
            return R.error(1, "密码错误，请重新输入！");
        }
        R r = new R();
        // 把密码设置为空，把User返回给接口使用
        tUserEntity.setPassword(StringUtils.EMPTY);
        r.put("user", tUserEntity);
        return r;
    }

    @Override
    public R register(String username) {
        int resultCount = userDao.queryCount(username);
        if (0 != resultCount) {
            return R.error(1, "用户名已存在！");
        }

        return R.ok();
    }

}
