package com.kh.demo.web;

import com.kh.demo.dao.Product;
import com.kh.demo.svc.ProductSVC;
import com.kh.demo.web.api.ApiResponse;
import com.kh.demo.web.api.product.AddReq;
import com.kh.demo.web.api.product.EditReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j //log.info()
@RestController //@Controller + @ResponseBody
@RequiredArgsConstructor //생성자 생성
@RequestMapping("/api")
public class ApiProductControllerV3 {

    @Autowired
    private final ProductSVC productSVC;

    //등록 POST /api/products
    @PostMapping(value = "/products")
    public ApiResponse<Object> add(
            @Valid @RequestBody AddReq addReq,
            BindingResult bindingResult
    ) {
        log.info("reqMsg={}", addReq);

        //검증
        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            return ApiResponse.createApiResMsg("99", "실패", getErrMsg(bindingResult));
        }

        //비즈니스 규칙
        //필드검증
        if (addReq.getQuantity() > 100) {
            bindingResult.rejectValue("quantity", null, "상품수량은 1,000개를 초과할 수 없습니다.");
        }
        //오브젝트검증
        if (addReq.getQuantity() * addReq.getPrice() > 10_000_000) {
            bindingResult.reject(null, "총액은 1,000만원을 초과할 수 없습니다.");
        }
        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            return ApiResponse.createApiResMsg("99", "실패", getErrMsg(bindingResult));
        }

        //AddReq->Product 변환
        Product product = new Product();
        BeanUtils.copyProperties(addReq, product);

        //상품등록
        Long id = productSVC.save(product);

        //응답메세지
        return ApiResponse.createApiResMsg("00", "성공", id);
    }

    //검증 오류메세지
    private Map<String, String> getErrMsg(BindingResult bindingResult) {
        Map<String, String> errmsg = new HashMap<>();

        bindingResult.getAllErrors().stream().forEach(objectError -> {
            errmsg.put(objectError.getCodes()[0], objectError.getDefaultMessage());
        });
        return errmsg;
    }

    //조회 GET /api/products/{id}
    @GetMapping("/products/{id}")
    public ApiResponse<Product> findById(@PathVariable("id") Long id) {

        //상품조회
        Optional<Product> findedProduct = productSVC.findByProductId(id);

        //응답메세지
        ApiResponse<Product> response = null;
        if(findedProduct.isPresent()) {
            response = ApiResponse.createApiResMsg("00", "성공", findedProduct.get());
        } else {
            response = ApiResponse.createApiResMsg("99", "찾고자하는 상품이 없습니다.", null);
        }

        return response;
    }

    //수정 PATCH /api/products/{id}
//    @PatchMapping("/products/{id}")
//    public ApiResponse<Product> edit(@PathVariable("id") Long id, @RequestBody EditReq editReq) {
//
//        //검증
//        Optional<Product> findedProduct = productSVC.findByProductId(id);
//        if (findedProduct.isEmpty()) {
//            return ApiResponse.createApiResMsg("99", "수정하려는 상품이 없습니다.", null);
//        }
//
//        //EditReq->Product 변환
//        Product product = new Product();
//        BeanUtils.copyProperties(editReq, product);
//
//        //수정
//        productSVC.update(id, product);
//
//        //응답메세지
//        return ApiResponse.createApiResMsg("00", "성공", productSVC.findByProductId(id).get());
//    }

    //수정 PATCH /api/products/{id}
    @PatchMapping("/products/{id}")
    public ApiResponse<Object> edit(
            @PathVariable("id") Long id,
            @Valid @RequestBody EditReq editReq,
            BindingResult bindingResult
    ) {
        log.info("reqMsg={}", editReq);

        //검증
        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            return ApiResponse.createApiResMsg("99", "실패", getErrMsg(bindingResult));
        }

        //비즈니스 규칙
        //필드검증
        if (editReq.getQuantity() > 100) {
            bindingResult.rejectValue("quantity", null, "상품수량은 1,000개를 초과할 수 없습니다.");
        }
        //오브젝트검증
        if (editReq.getQuantity() * editReq.getPrice() > 10_000_000) {
            bindingResult.reject(null, "총액은 1,000만원을 초과할 수 없습니다.");
        }
        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            return ApiResponse.createApiResMsg("99", "실패", getErrMsg(bindingResult));
        }

        //AddReq->Product 변환
        Product product = new Product();
        BeanUtils.copyProperties(editReq, product);

        //상품등록
        productSVC.update(id, product);

        //응답메세지
        return ApiResponse.createApiResMsg("00", "성공", id);
    }

    //삭제 DELETE /api/products/{id}
    @DeleteMapping("/products/{id}")
    public ApiResponse<Product> del(@PathVariable("id") Long id) {

        //검증
        Optional<Product> findedProduct = productSVC.findByProductId(id);
        if (findedProduct.isEmpty()) {
            return ApiResponse.createApiResMsg("99", "삭제하려는 상품이 없습니다.", null);
        }

        //삭제
        productSVC.deleteByProductId(id);

        //응답메세지
        return ApiResponse.createApiResMsg("00", "성공", null);
    }

    //목록 GET /api/products
    @GetMapping("/products")
    public ApiResponse<List<Product>> findAll() {

        List<Product> list = productSVC.findAll();

        //api 응답메세지
        return ApiResponse.createApiResMsg("00", "성공", list);
    }

}
