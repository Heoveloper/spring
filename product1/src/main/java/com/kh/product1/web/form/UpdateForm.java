package com.kh.product1.web.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateForm {
    private Long productId;
    private String pname;
    private Long quantity;
    private Long price;
}
