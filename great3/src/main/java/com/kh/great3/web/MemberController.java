package com.kh.great3.web;

import com.kh.great3.domain.svc.MemberSVC;
import com.kh.great3.web.form.InfoChk;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberSVC memberSVC;


    //개인정보 조회 및 수정 본인확인 화면
    @GetMapping("/infoChk")
    public String infoChk(Model model) {
        model.addAttribute("infoChk", new InfoChk());

        return "infoChk";    //회원가입 화면
    }
}
