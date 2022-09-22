package com.kh.demo.domain.common.file;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtils {
    @Value("${attach.root_dir}")
    private String attachRoot;  //첨부파일 root 경로

    //MultipartFile -> UploadFile
    public UploadFile multipartFileToUploadFile(MultipartFile file, AttachCode code, Long rid) {
        UploadFile uploadFile = new UploadFile();

        uploadFile.setCode(code.name()); //상품관리
        uploadFile.setRid(rid);
        uploadFile.setUploadFilename(file.getOriginalFilename());

        String storeFileName = storeFileName(file.getOriginalFilename());
        uploadFile.setStoreFilename(storeFileName);
        uploadFile.setFsize(String.valueOf(file.getSize()));
        uploadFile.setFtype(file.getContentType());

        //스토리지에 파일 저장
        storageFile(file, code, storeFileName);

        return uploadFile;
    }

    //List<MultipartFile> -> List<UploadFile>
    public List<UploadFile> multipartFileToUploadFiles(List<MultipartFile> files, AttachCode code, Long rid) {
        List<UploadFile> uploadFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            UploadFile uploadFile = multipartFileToUploadFile(file, code, rid);
            uploadFiles.add(uploadFile);
        }

        return uploadFiles;
    }

    //랜덤파일 생성
    private String storeFileName(String originalFileName) {
        //확장자 추출
        int dotPosition = originalFileName.indexOf(".");
        String ext = originalFileName.substring(dotPosition + 1);

        //랜덤파일명
        String storedFName = UUID.randomUUID().toString();
        StringBuffer fileName = new StringBuffer();
        String storedFileName = fileName.append(storedFName)
                .append(".")
                .append(ext)
                .toString();

        return storedFileName;
    }

    //스토리지에 파일 저장
    private void storageFile(MultipartFile file, AttachCode code, String storeFileName) {
        try {
            File f = new File(getPath(code, storeFileName));
            f.mkdirs();  //경로가 존재하지 않으면 생성
            file.transferTo(f);
        } catch (IOException e) {
            throw new RuntimeException("첨부파일 스토리지 저장시 오류 발생!");
        }
    }

    //첨부파일 경로
    private String getPath(AttachCode code, String storeFileName) {
        return this.attachRoot + code.name() + "/" + storeFileName;
    }
}
