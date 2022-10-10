package com.example.demo.user.common.note.adepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component("sessionInterceptor")
public class SessionInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("=============开始请求地址拦截=============");
        HttpSession session = request.getSession(false);

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String name = method.getName();
        logger.info("=============拦截到了方法: {}, 在该方法执行前执行=============", name);

        if (session != null && session.getAttribute("currntUser") != null) {
            return true;
        } else {
            PrintWriter printWriter = response.getWriter();
            printWriter.write("{code:1,message:\"log out!Please log in!\"}");
            logger.info("=============登录失效，请重新登录！=============");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("返回视图或String之前的处理");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("返回视图或String之前的处理");
    }
}