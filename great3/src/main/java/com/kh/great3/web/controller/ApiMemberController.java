package com.kh.great3.web.controller;

import com.kh.great3.domain.Member;
import com.kh.great3.domain.svc.MemberSVC;
import com.kh.great3.web.api.ApiResponse;
import com.kh.great3.web.api.member.FindId;
import com.kh.great3.web.form.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class ApiMemberController {

    private final MemberSVC memberSVC;
//    private final EmailSVC emailSVC;

    //아이디 찾기
    @PostMapping("/findId")
    public ApiResponse<Object> findId(@RequestBody FindId findId) {
        ApiResponse<Object> response = null;

        Member findedMember = memberSVC.findByMemNameAndMemEmail(findId.getMemName(), findId.getMemEmail());
        String id = findedMember.getMemId();
        LocalDateTime regtime = findedMember.getMemRegtime();
        ArrayList IdAndRegtime = new ArrayList();
        IdAndRegtime.add(id);
        IdAndRegtime.add(regtime);

        log.info("id={}", id);

        //응답메세지
        if(!StringUtils.isEmpty(id)){
            response =  ApiResponse.createApiResMsg("00", "성공", IdAndRegtime);
        }else{
            response =  ApiResponse.createApiResMsg("99", "부합하는 아이디가 없습니다.", null);
        }
        return response;
    }

    //회원탈퇴
    @DeleteMapping("/exit")
    public ApiResponse<Member> exit(@RequestBody Info info) {

        Member findedMember = memberSVC.findByMemNumber(info.getMemNumber());
        if(findedMember == null){
            return ApiResponse.createApiResMsg("99","탈퇴하고자 하는 회원이 존재하지 않습니다.", null);
        }

        memberSVC.exit(findedMember.getMemNumber());

        return ApiResponse.createApiResMsg("00","성공", findedMember);


    }
}
