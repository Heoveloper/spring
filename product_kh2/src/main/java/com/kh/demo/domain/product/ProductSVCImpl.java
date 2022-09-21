package com.kh.demo.domain.product;

import com.kh.demo.domain.common.file.AttachCode;
import com.kh.demo.domain.common.file.FileUtils;
import com.kh.demo.domain.common.file.UploadFileDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
//@Transactional
public class ProductSVCImpl implements ProductSVC{

  private final ProductDAO productDAO;
  private final UploadFileDAO uploadFileDAO;
  private final FileUtils fileUtils;

  //등록
  @Override
  public Long save(Product product) {
    return productDAO.save(product);
  }

  @Override
  public Long save(Product product, MultipartFile file) {
    //1) 상품등록
    Long id = save(product);

    //2) 첨부파일(설명) 메타정보 등록 (uploadFile 테이블), 첨부파일 저장소에 저장 (xxx-xxx-xxx.ext)
    uploadFileDAO.addFile(fileUtils.multipartFileToUploadFile(file, AttachCode.P0101, id));

    return id;
  }

  @Override
  public Long save(Product product, List<MultipartFile> files) {
    //1) 상품등록
    Long id = save(product);

    //2) 첨부파일(이미지) 메타정보 등록 (uploadFile 테이블), 첨부파일 저장소에 저장 (xxx-xxx-xxx.ext)
    uploadFileDAO.addFile(fileUtils.multipartFileToUploadFiles(files, AttachCode.P0102, id));

    return id;
  }

  @Override
  public Long save(Product product, MultipartFile file, List<MultipartFile> files) {
    //1) 상품등록
    Long id = save(product);

    //2) 첨부파일(설명) 메타정보 등록 (uploadFile 테이블), 첨부파일 저장소에 저장 (xxx-xxx-xxx.ext)
    uploadFileDAO.addFile(fileUtils.multipartFileToUploadFile(file, AttachCode.P0101, id));

    //2) 첨부파일(이미지) 메타정보 등록 (uploadFile 테이블), 첨부파일 저장소에 저장 (xxx-xxx-xxx.ext)
    uploadFileDAO.addFile(fileUtils.multipartFileToUploadFiles(files, AttachCode.P0102, id));

    return id;
  }

  //목록
  @Override
  public List<Product> findAll() {
    return productDAO.findAll();
  }

  //조회
  @Override
  public Optional<Product> findByProductId(Long productId) {
    return productDAO.findByProductId(productId);
  }

  //수정
  @Override
  public int update(Long productId, Product product) {
    return productDAO.update(productId,product);
  }

  //삭제
  @Override
  public int deleteByProductId(Long productId) {
    return productDAO.deleteByProductId(productId);
  }

}