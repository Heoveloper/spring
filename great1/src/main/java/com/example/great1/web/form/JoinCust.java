package com.example.great1.web.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JoinCust {
    private String memNumber;                   //varchar2(9)
    private String memType;                     //varchar2(15)
    private String memId;                       //varchar2(30)
    private String memPassword;                 //varchar2(18)
    private String memPasswordCheck;
    private String memName;                     //varchar2(18)
    private String memNickname;                 //varchar2(18)
    private String memEmail;                    //varchar2(30)
    private LocalDateTime memRegtime;           //date
    private LocalDateTime memLockExpiration;    //date
    private String memAdmin;                    //varchar2(3)
}
