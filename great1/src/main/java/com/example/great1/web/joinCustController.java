package com.example.great1.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/join")
@RequiredArgsConstructor
public class joinCustController {

    @GetMapping("/cust")
    public String joinForm() {

        return "joinCust";    //고객회원가입 화면
    }
}
