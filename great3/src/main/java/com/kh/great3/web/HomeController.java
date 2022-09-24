package com.kh.great3.web;

import com.kh.great3.domain.Member;
import com.kh.great3.domain.svc.MemberSVC;
import com.kh.great3.web.form.Login;
import com.kh.great3.web.session.LoginMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    final MemberSVC memberSVC;

    @GetMapping
    public String home() {
        return "main";
    }

    //로그인 화면
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("login") Login login){

        return "login";
    }

    //로그인 처리
    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute("login") Login login,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(value = "requestURI", required = false, defaultValue = "/") String requestURI
    ){
        //기본 검증
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "login";
        }

        //회원유무
        Optional<Member> member = memberSVC.login(login.getMemId(), login.getMemPassword());
        log.info("member = {}", member);
        if(member.isEmpty()){
            bindingResult.reject(null,"회원정보가 없습니다.");
            return "login";
        }

        //회원인 경우
        Member findedMember = member.get();

        //세션에 회원정보 저장
        LoginMember loginMember = new LoginMember(findedMember.getMemNumber(), findedMember.getMemType(), findedMember.getMemId(), findedMember.getMemNickname(), findedMember.getMemStoreName());

        //세션 생성
        HttpSession session = request.getSession(true);
        session.setAttribute("LoginMember", loginMember);

        if(requestURI.equals("/")){
            return "mainMember";
        }

        return "redirect:" + requestURI;
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        //세션 조회
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/"; //메인
    }

}
