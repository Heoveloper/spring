package com.kh.great3.domain.svc;

import com.kh.great3.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberSVC {
    /**
     * 회원가입
     * @param member 가입정보
     * @return 회원아이디
     */
    Member join(Member member);

    /**
     * 아이디 찾기
     *
     * @param memName  이름
     * @param memEmail 이메일
     * @return 회원아이디
     */
//    Member findId(String memName, String memEmail);

    /**
     * 로그인
     * @param memId 아이디
     * @param memPassword 패스워드
     * @return 회원
     */
    Optional<Member> login(String memId, String memPassword);

    /**
     * 조회 by 회원아이디
     * @param memNumber 회원아이디
     * @return 회원정보
     */
    Member findByMemNumber(Long memNumber);

    Member findByMemNameAndMemEmail(String memName, String memEmail);

    /**
     * 수정
     * @param memNumber 회원아이디
     * @param member  수정할 정보
     * @return 수정건수
     */
    Long update(Long memNumber, Member member);

    /**
     * 탈퇴
     * @param memNumber 회원아이디
     * @return 삭제건수
     */
    Long delete(Long memNumber);

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
