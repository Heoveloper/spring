package com.kh.demo.web;

import com.kh.demo.svc.ProductSVC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Slf4j  //log.info()
@Controller
public class ApiProductController {

    @Autowired
    private ProductSVC productSVC;

    //생성자 주입
    @Autowired
    public ApiProductController(ProductSVC productSVC) {
        this.productSVC = productSVC;
    }

    //세터메소드 주입
    @Autowired
    public void setInstance (ProductSVC productSVC) {
    this.productSVC = productSVC;
    }
}
