package com.kh.demo.web.api.product;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class EditReq {
    @NotBlank
    private String pname;   //상품명
    @NotNull
    @Positive(message = "수량은 1개 이상이어야 합니다.")
    private Long quantity;  //수량
    @NotNull
    @Positive(message = "단가는 1원 이상이어야 합니다.")
    private Long price;     //단가
}
