//package com.kh.great3.domain.svc;
//
//import com.kh.great3.domain.Member;
//import com.kh.great3.domain.dao.MemberDAO;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
////@ComponentScan(basePackages = {"com.kh.great3.domain.Member"})
////@SpringBootApplication(scanBasePackages = {"com.kh.great3.domain.Member"})
//public class EmailSVCImpl implements EmailSVC {
//
//    @Autowired
//    private final MemberDAO memberDAO;
//    @Autowired
//    private final Member member;
//    @Autowired
//    private final JavaMailSender mailSender;
//
//    String sender = "altruism_tap@naver.com";
//
//
//    @Override
//    public String sendForgotPassword(String memId, String memEmail) {
//
//        Member findedMember = memberDAO.findByMemIdAndMemEmail(memId, memEmail);
//
//
//        if(findedMember == null){
//            try {
//                throw new UserNotFoundException("User not found with email : " + findedMember.getMemEmail());
//            } catch (UserNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }else{
//            String tempPassword = getTempPassword();
//            member.setMemPassword(tempPassword);
//
//            //메세지를 생성하고 보낼 메일 설정 저장
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(memEmail);
//            message.setFrom(sender);
//            message.setSubject(member.getMemName() + "님, 임시비밀번호를 발급했습니다!");
//            message.setText("Hello" + member.getMemName() + "님! 임시비밀번호를 드립니다. 그러나 안전하지 않으니 속히 비밀번호를 변경하세요. 임시비밀번호: " + tempPassword);
//            mailSender.send(message);
//            return "Temporary password sent to your email.";
//        }
//    }
//
//    //임시 비밀번호 발급
//    @Override
//    public String getTempPassword(){
//        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
//                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
//
//        String str = "";
//
//        int idx = 0;
//        for (int i = 0; i < 10; i++) {
//            idx = (int) (charSet.length * Math.random());
//            str += charSet[idx];
//        }
//        return str;
//    }
//
//    private class UserNotFoundException extends Throwable {
//        public UserNotFoundException(String s) {
//        }
//    }
//}
