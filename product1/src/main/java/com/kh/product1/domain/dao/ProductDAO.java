package com.kh.product1.domain.dao;

import com.kh.product1.domain.Product;

import java.util.List;

public interface ProductDAO {
    /**
     * 상품등록
     * @param product
     * @return
     */
    int add(Product product);

    /**
     * 상품조회
     * @param productId
     * @return
     */
    Product findById(Long productId);

    /**
     * 상품정보수정
     * @param productId
     * @param product
     */
    int update(Long productId, Product product);

    /**
     * 상품삭제
     * @param productId
     */
    int delete(Long productId);

    /**
     * 상품전체목록
     * @return
     */
    List<Product> products();

    /**
     * 상품전체삭제
     */
    void deleteAll();

    /**
     * 상품번호생성
     * @return
     */
    Long generatePid();
}
