package com.kh.great2.web;

import com.kh.great2.domain.Member;
import com.kh.great2.domain.svc.MemberSVC;
import com.kh.great2.web.form.Join;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberSVC memberSVC;

    //회원가입 화면
    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("join", new Join());
        return "join3";    //회원가입 화면
    }

    //회원가입 처리
    @PostMapping("/join")
    public String join(
            @Valid @ModelAttribute("join") Join join,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        //검증
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "join3";
        }


        //field error
        if(join.getMemId().length() < 8 || join.getMemId().length() > 15) {

            bindingResult.rejectValue("memId", "memIdError", "아이디 길이가 맞지 않습니다.");
            return "join3";
        }


        Member member = new Member();
        member.setMemType(join.getMemType());
        member.setMemId(join.getMemId());
        member.setMemPassword(join.getMemPassword());
        member.setMemName(join.getMemName());
        member.setMemNickname(join.getMemNickname());
        member.setMemEmail(join.getMemEmail());
        Member joinedMember = memberSVC.join(member);

        int id = joinedMember.getMemNumber();
        redirectAttributes.addAttribute("id", id);
        return "redirect:/member/{id}";  //가입완료화면
    }
}
