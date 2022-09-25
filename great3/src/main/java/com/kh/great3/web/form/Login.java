package com.kh.great3.web.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class Login {
    @NotBlank
    private String memId;

    @NotBlank
    @Size(min = 8, max = 15)
    private String memPassword;
}