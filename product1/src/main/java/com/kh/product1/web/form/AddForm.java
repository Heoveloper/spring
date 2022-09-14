package com.kh.product1.web.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddForm {
    private String pname;
    private Long quantity;
    private Long price;
}
