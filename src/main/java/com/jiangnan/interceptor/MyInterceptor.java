package com.jiangnan.interceptor;

import com.jiangnan.common.LoginInfo;
import com.jiangnan.common.UserInfoHolder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");
        String token = request.getHeader("token");
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setToken(token);
        UserInfoHolder.setLoginInfo(loginInfo);
        MDC.put("traceId", request.getHeader("traceId"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserInfoHolder.remove();
        MDC.clear();
    }
}
