package com.kh.great3.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("인증체크 = {}", requestURI);

        //세션 조회
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginMember") == null) {
            log.info("미인증 사용자의 요청");

            //로그인 화면으로 redirect
            response.sendRedirect("/login?requestURI=" + requestURI);
            return false;
        }

        return true;
    }
}
