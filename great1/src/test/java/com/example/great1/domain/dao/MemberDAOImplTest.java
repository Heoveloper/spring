package com.example.great1.domain.dao;

import com.example.great1.domain.Member;
import com.example.great1.domain.svc.MemberSVC;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MemberDAOImplTest {

    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private MemberSVC memberSVC;

    @Test
    @DisplayName("고객회원가입")
    void joinCust() {
        Member member = new Member();
        member.setMemId("test333");
        member.setMemPassword("test333@");
        member.setMemName("허준혁3");
        member.setMemNickname("허준3");
        member.setMemEmail("test333@test.com");
        Member joinedMember = memberSVC.joinCust(member);
        log.info("고객회원가입완료={}", joinedMember);
    }
}