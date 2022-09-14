package com.kh.product1.domain.svc;

import com.kh.product1.domain.Product;
import com.kh.product1.domain.dao.ProductDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC {
    private final ProductDAO productDAO;

    /**
     * 상품등록
     *
     * @param product
     * @return
     */
    @Override
    public Product add(Product product) {
        Long generatePid = productDAO.generatePid();
        product.setProductId(generatePid);
        productDAO.add(product);
        return productDAO.findById(generatePid);
    }

    /**
     * 상품조회
     *
     * @param productId
     * @return
     */
    @Override
    public Product findById(Long productId) {
        return productDAO.findById(productId);
    }

    /**
     * 상품정보수정
     *
     * @param productId
     * @param product
     */
    @Override
    public int update(Long productId, Product product) {
        int cnt = productDAO.update(productId, product);
        return cnt;
    }

    /**
     * 상품삭제
     *
     * @param productId
     */
    @Override
    public int delete(Long productId) {
        int cnt = productDAO.delete(productId);
        return cnt;
    }

    /**
     * 상품전체목록
     *
     * @return
     */
    @Override
    public List<Product> products() {
        return productDAO.products();
    }

    /**
     * 상품전체삭제
     */
    @Override
    public void deleteAll() {
        productDAO.deleteAll();
    }
}
