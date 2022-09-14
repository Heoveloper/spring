package com.kh.product1.domain.dao;

import com.kh.product1.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO {
    private final JdbcTemplate jt;

    /**
     * 상품등록
     *
     * @param product
     * @return
     */
    @Override
    public int add(Product product) {
        int add = 0;
        StringBuffer sql = new StringBuffer();
        sql.append("insert into product (product_id, pname, quantity, price) ");
        sql.append("values (?, ?, ?, ?) ");

        add = jt.update(sql.toString(), product.getProductId(), product.getPname(), product.getQuantity(), product.getPrice());

        return add;
    }

    /**
     * 상품조회
     *
     * @param productId
     * @return
     */
    @Override
    public Product findById(Long productId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select product_id, pname, quantity, price ");
        sql.append("from product ");
        sql.append("where product_id = ? ");

        Product product = null;
        product = jt.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(), productId);

        return product;
    }

    /**
     * 상품정보수정
     *
     * @param productId
     * @param product
     */
    @Override
    public int update(Long productId, Product product) {
        return 0;
    }

    /**
     * 상품삭제
     *
     * @param productId
     */
    @Override
    public int delete(Long productId) {
        return 0;
    }

    /**
     * 상품전체목록
     *
     * @return
     */
    @Override
    public List<Product> products() {
        return null;
    }

    /**
     * 상품전체삭제
     */
    @Override
    public void deleteAll() {

    }

    /**
     * 상품번호생성
     *
     * @return
     */
    @Override
    public Long generatePid() {
        String sql = "SELECT product_product_id_seq.nextval FROM dual";
        Long newProductId = jt.queryForObject(sql, Long.class); //단일레코드 단일컬럼
        return newProductId;
    }
}
