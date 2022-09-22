package com.kh.demo.web;

import com.kh.demo.domain.common.file.UploadFile;
import com.kh.demo.domain.common.file.UploadFileDAO;
import com.kh.demo.web.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/attach")
@RequiredArgsConstructor
public class AttachFileController {

    @Value("${attach.root_dir}")
    private String attachRoot;  //첨부파일 root 경로

    private final UploadFileDAO uploadFileDAO;


    //이미지
    //@ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/img/{attachCode}/{storeFileName}")
    public Resource img(
            @PathVariable String attachCode,
            @PathVariable String storeFileName
    ) throws MalformedURLException {

        // http://서버:포트/경로...
        // file:///d:/tmp/P0101/xxx-xxx-xxx-xxx.png
        Resource resource = new UrlResource("file:///" + attachRoot + "/" + attachCode + "/" + storeFileName);

        return resource;
    }

    //다운로드
    @GetMapping("/file/{attachCode}/{fid}")
    //반환타입이 ResponseEntity<>인 경우 응답메세지 바디에 직접 쓰는 작업 시도
    //=> @ResponseBody 불필요
    public ResponseEntity<Resource> file (
            @PathVariable String attachCode,
            @PathVariable Long fid
    ) throws MalformedURLException {
        ResponseEntity<Resource> res = null;

        Optional<UploadFile> uploadFile = uploadFileDAO.findFileByUploadFileId(fid);
        if (uploadFile.isEmpty()) return res;

        UploadFile attachFile = uploadFile.get();
        String storeFileName = attachFile.getStoreFilename();
        String uploadFileName = attachFile.getUploadFilename();


        Resource resource = new UrlResource("file:" + attachRoot + "/" + attachCode + "/" + storeFileName);

        //첨부파일명의 한글깨짐 방지
        String encode = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);

        //클라이언트가 파일을 다운로드 가능하게 해주기 위해 응답메세지 헤더에 포함
        String contentDisposition = "attachment; filename = \""+encode+"\"";

        res = ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                            .body(resource);

        return res;
    }

    //첨부파일 삭제
    @ResponseBody
    @DeleteMapping("/{fid}")
    public ApiResponse<Object> deleteAttachFile(@PathVariable Long fid) {
        //1) 스토리지 파일을 삭제하기 위해 분류코드(code)와 저장파일명(storeFilename)을 가져온다.
        Optional<UploadFile> optional = uploadFileDAO.findFileByUploadFileId(fid);
        if (optional.isEmpty()) {
            return ApiResponse.createApiResMsg("98", "찾고자하는 파일이 없습니다.", null);
        }
        UploadFile uploadFile = optional.get();

        //2) 스토리지 파일을 삭제한다.


        //3) 첨부파일의 메타정보를 삭제한다.
        int affectedRow = uploadFileDAO.deleteFileByUploadFildId(fid);

        if (affectedRow != 0) {
            return ApiResponse.createApiResMsg("00", "성공", null);
        }
        return ApiResponse.createApiResMsg("99", "실패", null);
    }

}
