package com.example.great1.domain.svc;

import com.example.great1.domain.Member;
import com.example.great1.domain.dao.MemberDAO;

import java.util.List;

public interface MemberSVC {
    /**
     * 고객회원가입
     * @param member 가입정보
     * @return 회원아이디
     */
    Member joinCust(Member member);

    /**
     * 점주회원가입
     * @param member 가입정보
     * @return 회원아이디
     */
    Member joinOwn(Member member);

    /**
     * 조회 by 회원아이디
     * @param memNumber 회원아이디
     * @return 회원정보
     */
    Member findById(String memNumber);

    /**
     * 수정
     * @param memNumber 회원아이디
     * @param member  수정할 정보
     * @return 수정건수
     */
    int update(String memNumber, Member member);

    /**
     * 탈퇴
     * @param memNumber 회원아이디
     * @return 삭제건수
     */
    int del(String memNumber);

    /**
     * 목록
     * @return 회원전체
     */
    List<Member> all();

    /**
     * 이메일 중복체크
     * @param email 이메일
     * @return 존재하면 true
     */
    Boolean dupChkOfMemberEmail(String email);
}
