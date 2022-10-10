package com.example.demo.user.common.user.utils;

import com.example.demo.user.common.user.entity.TUserEntity;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpSession;

public class UserUtils {

    /**
     * 获取用户信息
     * @param session
     * @return
     */
    public static TUserEntity getUser(HttpSession session) {
        return null != session.getAttribute("currntUser") ? (TUserEntity) session.getAttribute("currntUser") : new TUserEntity();
    }

    /**
     * 获取用户名称
     * @param session
     * @return
     */
    public static String getUserName(HttpSession session) {
        return StringUtils.isNotBlank(getUser(session).getUsername()) ? getUser(session).getUsername() : "";
    }

    /**
     * 获取用户id
     * @param session
     * @return
     */
    public static String getUserid(HttpSession session) {
        return StringUtils.isNotBlank(getUser(session).getUserId()) ? getUser(session).getUserId() : "";
    }

    /**
     * 获取组织id
     * @param session
     * @return
     */
    public static String getOrgenid(HttpSession session) {
        return StringUtils.isNotBlank(getUser(session).getOrginId()) ? getUser(session).getOrginId() : "";
    }

    /**
     * 获取组织代码
     * @param session
     * @return
     */
    public static String getOrgenCode(HttpSession session) {
        return StringUtils.isNotBlank(getUser(session).getOrginCode()) ? getUser(session).getOrginCode() : "";
    }
}
