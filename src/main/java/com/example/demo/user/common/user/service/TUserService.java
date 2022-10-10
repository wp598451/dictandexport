package com.example.demo.user.common.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.user.common.utils.PageUtils;
import com.example.demo.user.common.utils.R;
import com.example.demo.user.common.user.entity.TUserEntity;

import java.util.Map;

/**
 * 
 *
 * @author wp
 * @email ping.wang@nuggetdata.com
 * @date 2022-08-09 10:39:44
 */
public interface TUserService extends IService<TUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R login(String username, String password);

    R register(String username);
}

