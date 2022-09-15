package com.kh.demo.web;

import com.kh.demo.dao.Product;
import com.kh.demo.svc.ProductSVC;
import com.kh.demo.web.api.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j //log.info()
@Controller
@RequiredArgsConstructor //생성자 생성
@RequestMapping("/api")
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
    @ResponseBody //View를 찾지 않고 Body를 반환
    @PostMapping(
            value = "/products"
            //,produces = "application/json"
    )
    public ApiResponse<List<Person>> add(@RequestBody String reqMsg) {
        log.info("reqMsg={}", reqMsg);

        List<Person> persons = new ArrayList<>();

        Person p1 = new Person("홍길동", 30);
        Person p2 = new Person("홍길순", 20);

        persons.add(p1);
        persons.add(p2);

        ApiResponse.Header header = new ApiResponse.Header("00", "성공!");
        ApiResponse<List<Person>> response = new ApiResponse<>(header, persons);

        return response;

        //Set<Person> persons = new HashSet<>();
        //Map<String, Person> persons = new HashMap<>();
        //List<Person> persons = new ArrayList<>();

        //Person p1 = new Person("홍길동", 30);
        //Set: persons.add(p1);
        //Map: persons.put("1", p1);
        //List: persons.add(p1);

        //Person p2 = new Person("홍길순", 20);
        //Set: persons.add(p2);
        //Map: persons.put("2", p2);
        //List: persons.add(p2);
    }

    @Data
    @AllArgsConstructor
    static class Person {
        private String name;
        private int age;
    }

    //제너릭 타입을 사용해서 사용시점에 타입을 결정
//    @Data
//    @AllArgsConstructor
//    static class Response<T> {
//        private Header header;
//        private T data;
//
//        @Data
//        @AllArgsConstructor
//        static class Header {
//            private String rtcd;
//            private String rtmsg;
//        }
//    }

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
    @ResponseBody
    @GetMapping("/products")
    public ApiResponse<List<Product>> findAll() {

        List<Product> list = productSVC.findAll();

        //api 응답메세지
        ApiResponse.Header header = new ApiResponse.Header("00", "성공!");
        ApiResponse<List<Product>> res = new ApiResponse<>(header, list);

        return res;
    }

}
