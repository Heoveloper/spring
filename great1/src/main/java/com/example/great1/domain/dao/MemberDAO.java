package com.example.great1.domain.dao;

import com.example.great1.domain.Member;

import java.util.List;

public interface MemberDAO {
    /**
     * 신규 회원아이디(고객회원) 생성
     * @return 회원아이디
     */
    String generateCustMemberId();

    /**
     * 신규 회원아이디(점주회원) 생성
     * @return 회원아이디
     */
    String generateOwnMemberId();

    /**
     * 고객회원가입
     * @param member 가입정보
     * @return 회원아이디
     */
    int joinCust(Member member);

    /**
     * 점주회원가입
     * @param member 가입정보
     * @return 회원아이디
     */
    int joinOwn(Member member);

    /**
     * 조회 by 회원아이디
     * @param memberId 회원아이디
     * @return 회원정보
     */
    Member findById(String memberId);

    /**
     * 수정
     * @param memberId 아이디
     * @param member  수정할 정보
     * @return 수정건수
     */
    int update(String memberId, Member member);

    /**
     * 탈퇴
     * @param memberId 아이디
     * @return 삭제건수
     */
    int del(String memberId);

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