package com.kh.demo.web;

import com.kh.demo.svc.ProductSVC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j  //log.info()
@Controller
@RequiredArgsConstructor    //생성자 생성
public class ApiProductController {

    @Autowired
    private final ProductSVC productSVC;

    //생성자 주입
//    @Autowired
//    public ApiProductController(ProductSVC productSVC) {
//        this.productSVC = productSVC;
//    }

    //세터메소드 주입
//    @Autowired
//    public void setInstance (ProductSVC productSVC) {
//    this.productSVC = productSVC;
//    }

    //등록 POST /api/products
    @PostMapping("/products")
    public String add() {
        return "ok";
    }

    //조회 GET /api/products/{id}
    @GetMapping("/products/{id}")
    public String findById() {
        return "ok";
    }

    //수정 PATCH /api/products/{id}
    @PatchMapping("/products/{id}")
    public String edit() {
        return "ok";
    }

    //삭제 DELETE /api/products/{id}
    @DeleteMapping("/products/{id}")
    public String del() {
        return "ok";
    }

    //목록 GET /api/products
    @GetMapping("/products")
    public String findAll() {
        return "ok";
    }

}
