package com.example.great1.domain.svc;

import com.example.great1.domain.Member;
import com.example.great1.domain.dao.MemberDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberSVCImpl implements MemberSVC {
    private final MemberDAO memberDAO;

    /**
     * 고객회원가입
     *
     * @param member 가입정보
     * @return 회원아이디
     */
    @Override
    public Member joinCust(Member member) {

        //회원 아이디 생성
        String generateCustMemberId = memberDAO.generateCustMemberId();
        member.setMemNumber(generateCustMemberId);
        memberDAO.joinCust(member);
        return memberDAO.findById(generateCustMemberId);
    }

    /**
     * 점주회원가입
     *
     * @param member 가입정보
     * @return 회원아이디
     */
    @Override
    public Member joinOwn(Member member) {

        //회원 아이디 생성
        String generateOwnMemberId = memberDAO.generateOwnMemberId();
        member.setMemNumber(generateOwnMemberId);
        memberDAO.joinOwn(member);
        return memberDAO.findById(generateOwnMemberId);
    }

    /**
     * 조회 by 회원아이디
     *
     * @param memberId 회원아이디
     * @return 회원정보
     */
    @Override
    public Member findById(String memberId) {
        return memberDAO.findById(memberId);
    }

    /**
     * 수정
     *
     * @param memberId 아이디
     * @param member   수정할 정보
     * @return 수정건수
     */
    @Override
    public int update(String memberId, Member member) {
        int cnt = memberDAO.update(memberId, member);
        log.info("수정건수={}", cnt);
        return cnt;
    }

    /**
     * 탈퇴
     *
     * @param memberId 아이디
     * @return 삭제건수
     */
    @Override
    public int del(String memberId) {
        int cnt = memberDAO.del(memberId);
        log.info("삭제건수={}", cnt);
        return cnt;
    }

    /**
     * 목록
     *
     * @return 회원전체
     */
    @Override
    public List<Member> all() {
        return memberDAO.all();
    }

    /**
     * 이메일 중복체크
     *
     * @param email 이메일
     * @return 존재하면 true
     */
    @Override
    public Boolean dupChkOfMemberEmail(String email) {

        return memberDAO.dupChkOfMemberEmail(email);
    }
}
