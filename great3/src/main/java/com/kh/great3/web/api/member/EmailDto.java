package com.kh.great3.web.api.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EmailDto {

    @NotEmpty(message = "이메일을 입력해주세요")
    public String email;
}