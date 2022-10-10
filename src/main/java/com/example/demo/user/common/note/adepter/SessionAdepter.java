package com.example.demo.user.common.note.adepter;

import com.example.demo.user.common.user.entity.TUserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * session拦截器
 * Deprecated
 */
@Deprecated
@Component("SessionAdepter")
public class SessionAdepter extends HandlerInterceptorAdapter {
    // 忽略的URL地址
    private static final String[] IGNORE_URI = {"/login/do"};

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception
    {
        TUserEntity session = (TUserEntity) request.getSession().getAttribute("session");

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        contextPath = contextPath+"/";

        for (int i = 0; i < IGNORE_URI.length; i++) {
            // 忽略的url地址
            if (requestURI.contains(IGNORE_URI[i]) || requestURI.equals(contextPath)) {
                return true;
            }
        }
        //如果session为空则提示未登录
        if(!requestURI.contains(IGNORE_URI[0])&& null == session) {
            String requestType = request.getHeader("X-Requested-With");
            if(!StringUtils.isBlank(requestType)){
                if("XMLHttpRequest".equals(requestType)) {
                    throw new Exception("session为空");
                }
            }
            request.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(request, response);
        }
        return true;
    }
}
