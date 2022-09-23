package com.kh.great3.web;

import com.kh.great3.domain.Member;
import com.kh.great3.domain.svc.MemberSVC;
import com.kh.great3.web.form.Join;
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

    //회원가입 화면
    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("join", new Join());
        return "join";    //회원가입 화면
    }

    //회원가입 처리
    @PostMapping("/join")
    public String join(
            @Valid @ModelAttribute("join") Join join,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        //검증로직
        //기본 검증
        //if (bindingResult.hasErrors()) {
        //    log.info("errors={}", bindingResult);
        //    return "join";
        //}

        //필드 검증(field error)
        //if (join.getMemId().length() > 15) {
        //    bindingResult.rejectValue("memId", null, "아이디 길이는 15자 이하까지 가능합니다.");
        //    return "join";
        //}

        //오브젝트 검증(object error)
        //비밀번호-비밀번호 확인 일치
        //if (join.getMemPassword() == join.getMemPasswordCheck()) {
        //    bindingResult.reject(null, "비밀번호 일치합니다");
        //    return "join";
        //}


        Member member = new Member();
        member.setMemType(join.getMemType());
        member.setMemId(join.getMemId());
        member.setMemPassword(join.getMemPassword());
        member.setMemName(join.getMemName());
        member.setMemNickname(join.getMemNickname());
        member.setMemEmail(join.getMemEmail());
        member.setMemBusinessnumber(join.getMemBusinessnumber());
        member.setMemStoreName(join.getMemStoreName());
        member.setMemStorePhonenumber(join.getMemStorePhonenumber());
        member.setMemStoreLocation(join.getMemStoreLocation());
        member.setMemStoreIntroduce(join.getMemStoreIntroduce());
        member.setMemStoreSns(join.getMemStoreSns());
        Member joinedMember = memberSVC.join(member);

        Long id = joinedMember.getMemNumber();
        redirectAttributes.addAttribute("id", id);
        return "redirect:/";  //가입완료화면
    }
}
