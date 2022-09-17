package com.kh.great2.web.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class Join {
    private int memNumber;                      //varchar2(9)
    private String memType;                     //varchar2(15)
    @NotBlank
    private String memId;                       //varchar2(30)
    private String memPassword;                 //varchar2(18)
    private String memPasswordCheck;
    private String memName;                     //varchar2(18)
    private String memNickname;                 //varchar2(18)
    @Email(message = "이메일 형식이 아닙니다.")
    @NotBlank
    private String memEmail;                    //varchar2(30)
    private LocalDateTime memRegtime;           //date
    private LocalDateTime memLockExpiration;    //date
    private String memAdmin;                    //varchar2(3)
}
