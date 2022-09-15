package com.kh.demo.web;

import com.kh.demo.svc.ProductSVC;
import lombok.extern.slf4j.Slf4j;

@Slf4j  //log.info()
public class ApiProductController {

    private final ProductSVC productSVC;

    //생성자 주입
    public ApiProductController(ProductSVC productSVC) {
        this.productSVC = productSVC;
    }
}
