package com.kh.great3.web;

import com.kh.great3.domain.Member;
import com.kh.great3.domain.svc.MemberSVC;
import com.kh.great3.web.form.Info;
import com.kh.great3.web.form.InfoChk;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

        return "infoChk";
    }

    //개인정보 조회 및 수정 본인확인 처리
    @PostMapping("/infoChk")
    public String infoChk(
            @Valid @ModelAttribute("infoChk") InfoChk infoChk,
            BindingResult bindingResult,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
//            HttpSession session
    ) {
        HttpSession session = request.getSession(false);
        Object memNum = session.getAttribute("memNumber");
        log.info("cccccccccc={}", memNum);

        redirectAttributes.addAttribute("memNum", memNum);

        return "redirect:/member/cust/{memNum}";
    }

    //고객회원정보 조회 및 수정 화면
    @GetMapping("/cust/{memNumber}")
    public String infoCust(
            @PathVariable("memNumber") Long memNumber,
            Model model
    ){
        Member findedMember = memberSVC.findByMemNumber(memNumber);

        Info info = new Info();
        info.setMemNumber(findedMember.getMemNumber());
        info.setMemType(findedMember.getMemType());
        info.setMemId(findedMember.getMemId());
        info.setMemPassword(findedMember.getMemPassword());
        info.setMemName(findedMember.getMemName());
        info.setMemNickname(findedMember.getMemNickname());
        info.setMemEmail(findedMember.getMemEmail());

        model.addAttribute("info", info);
        return "infoCust"; //회원 수정화면
    }

    //점주회원정보 조회 및 수정 화면
    @GetMapping("/own/{memNumber}")
    public String infoOwn(
            @PathVariable("memNumber") Long memNumber,
            Model model
    ){
        Member findedMember = memberSVC.findByMemNumber(memNumber);

        Info info = new Info();
        info.setMemNumber(findedMember.getMemNumber());
        info.setMemType(findedMember.getMemType());
        info.setMemId(findedMember.getMemId());
        info.setMemPassword(findedMember.getMemPassword());
        info.setMemName(findedMember.getMemName());
        info.setMemNickname(findedMember.getMemNickname());
        info.setMemEmail(findedMember.getMemEmail());
        info.setMemBusinessnumber(findedMember.getMemBusinessnumber());
        info.setMemStoreName(findedMember.getMemStoreName());
        info.setMemStorePhonenumber(findedMember.getMemStorePhonenumber());
        info.setMemStoreLocation(findedMember.getMemStoreLocation());
        info.setMemStoreIntroduce(findedMember.getMemStoreIntroduce());
        info.setMemStoreSns(findedMember.getMemStoreSns());


        model.addAttribute("info", findedMember);
        return "infoOwn"; //회원 수정화면
    }

    //고객회원정보 수정 처리
    @PostMapping("/cust/{memNumber}")
    public String editCust(
            @PathVariable("memNumber") Long memNumber,
            @Valid @ModelAttribute("info") Info info,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        Member member = new Member();
        member.setMemPassword(info.getMemPassword());
        member.setMemName(info.getMemName());
        member.setMemNickname(info.getMemNickname());
        member.setMemEmail(info.getMemEmail());

        Long updatedRow = memberSVC.update(memNumber, member);
        if (updatedRow == 0) {
            return "infoCust";
        }

        redirectAttributes.addAttribute("memNumber", member.getMemNumber());

        return "redirect:/member/cust/{memNumber}";
    }

    //점주회원정보 수정 처리
    @PostMapping("/own/{memNumber}")
    public String editOwn(
            @PathVariable("memNumber") Long memNumber,
            @Valid @ModelAttribute("info") Info info,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        Member member = new Member();
        member.setMemPassword(info.getMemPassword());
        member.setMemName(info.getMemName());
        member.setMemNickname(info.getMemNickname());
        member.setMemEmail(info.getMemEmail());
        member.setMemBusinessnumber(info.getMemBusinessnumber());
        member.setMemStoreName(info.getMemStoreName());
        member.setMemStorePhonenumber(info.getMemStorePhonenumber());
        member.setMemStoreLocation(info.getMemStoreLocation());
        member.setMemStoreIntroduce(info.getMemStoreIntroduce());
        member.setMemStoreSns(info.getMemStoreSns());

        Long updatedRow = memberSVC.update(memNumber, member);
        if (updatedRow == 0) {
            return "infoOwn";
        }

        redirectAttributes.addAttribute("memNumber", member.getMemNumber());

        return "redirect:/member/own/{memNumber}";
    }
}
