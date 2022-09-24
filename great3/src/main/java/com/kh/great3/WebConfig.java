package com.kh.great3;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    public void addInterceptors(InterceptorRegistry registry) {
//        //모든 요청에 대한 log
//        registry.addInterceptor(new LogInterceptor())
//                .order(1)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/error"); //세션체크가 필요없는 url
//
//        //로그인 인증 (세션체크)
//        List<String> whiteList = new ArrayList<>();
//        whiteList.add("/");
//        whiteList.add("/error");
//        whiteList.add("/login");
//        whiteList.add("/logout");
//        whiteList.add("/products/**");
//
//        registry.addInterceptor(new LoginInterceptor())
//                .order(2)
//                .addPathPatterns("/**")             //블랙리스트
//                .excludePathPatterns(whiteList);    //화이트리스트
//    }
}
