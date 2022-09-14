package com.example.great1.web;

import com.example.great1.domain.Member;
import com.example.great1.domain.svc.MemberSVC;
import com.example.great1.web.form.JoinCust;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberSVC memberSVC;

    //고객회원가입 화면
    @GetMapping("/join/cust")
    public String joinCust(Model model) {
        model.addAttribute("joinCust", new JoinCust());
        return "joinCust";    //고객회원가입 화면
    }

    //고객회원가입 처리
    @PostMapping("/join/cust")
    public String join(
            @Valid @ModelAttribute("joinCust") JoinCust joinCust,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        Member member = new Member();
        member.setMemId(joinCust.getMemId());
        member.setMemPassword(joinCust.getMemPassword());
        member.setMemName(joinCust.getMemName());
        member.setMemNickname(joinCust.getMemNickname());
        member.setMemEmail(joinCust.getMemEmail());
        Member joinedMember = memberSVC.joinCust(member);
        
        String id = joinedMember.getMemNumber();
        redirectAttributes.addAttribute("id", id);
        return "redirect:/member/{id}";  //가입완료화면
    }
}
